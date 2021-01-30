package event;

import domain.Proposal;
import event.proponent.CreateProponentEvent;
import event.proponent.RemoveProponentEvent;
import event.proponent.UpdateProponentEvent;
import event.proposal.CreateProposalEvent;
import event.proposal.RemoveProposalEvent;
import event.proposal.UpdateProposalEvent;
import event.warranty.CreateWarrantyEvent;
import event.warranty.RemoveWarrantyEvent;
import event.warranty.UpdateWarrantyEvent;
import exception.SchemaNotFoundException;
import service.proposal.ProposalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventManager {

    private final EventMap eventMap;
    private final ProposalService proposalService;

    public EventManager(ProposalService proposalService) {
        this.proposalService = proposalService;
        this.eventMap = new EventMap();
    }

    public void add(String[] rawEvent){
        String id = rawEvent[0];
        String schema = rawEvent[1];
        String action = rawEvent[2];
        String timeStampRaw = rawEvent[3];
        String proposalId = rawEvent[4];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime timeStamp = LocalDateTime.parse(timeStampRaw, formatter);

        Event event = createEvent(rawEvent, id, schema, action, timeStamp, proposalId);

        add(event);
    }

    private Event createEvent(String[] rawEvent, String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        Event event;

        if(schema.equals("proposal")) {
            event = createProposalEvents(rawEvent, id, schema, action, timeStamp, proposalId);
        }else if(schema.equals("warranty")) {
            event = createWarrantyEvents(rawEvent, id, schema, action, timeStamp, proposalId);
        }else if(schema.equals("proponent")) {
            event = createProponentEvents(rawEvent, id, schema, action, timeStamp, proposalId);
        }else{
            throw new SchemaNotFoundException("Schema " + schema + " does not exists.");
        }
        return event;
    }

    private Event createProponentEvents(String[] rawEvent, String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        Event event;
        String proponentId = rawEvent[5];
        if (action.equals("added") || action.equals("updated")) {
            String proponentName = rawEvent[6];
            String proponentAge = rawEvent[7];
            String proponentMonthlyIncome = rawEvent[8];
            String proponentIsMain = rawEvent[9];
            if(action.equals("added")){
                event = new CreateProponentEvent(id, schema, action, timeStamp, proposalId, proponentId, proponentName, proponentAge, proponentMonthlyIncome, proponentIsMain);
            }else{
                event = new UpdateProponentEvent(id, schema, action, timeStamp, proposalId, proponentId, proponentName, proponentAge, proponentMonthlyIncome, proponentIsMain);
            }
        }else{
            event = new RemoveProponentEvent(id, schema, action, timeStamp, proposalId, proponentId);
        }
        return event;
    }

    private Event createWarrantyEvents(String[] rawEvent, String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        Event event;
        String warrantId = rawEvent[5];
        if (action.equals("added") || action.equals("updated")) {
            String warrantValue = rawEvent[6];
            String warrantyProvince = rawEvent[7];
            if(action.equals("added")){
                event = new CreateWarrantyEvent(id, schema, action, timeStamp, proposalId, warrantId, warrantValue, warrantyProvince);
            }else{
                event = new UpdateWarrantyEvent(id, schema, action, timeStamp, proposalId, warrantId, warrantValue, warrantyProvince);
            }
        }else{
            event = new RemoveWarrantyEvent(id, schema, action, timeStamp, proposalId, warrantId);
        }
        return event;
    }

    private Event createProposalEvents(String[] rawEvent, String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        Event event;
        if (action.equals("created") || action.equals("updated")) {
            String proposalLoanValue = rawEvent[5];
            String proposalNumberOfMonthlyInstallments = rawEvent[6];
            if(action.equals("created")){
                event = new CreateProposalEvent(id, schema, action, timeStamp, proposalId, proposalLoanValue, proposalNumberOfMonthlyInstallments);
            }else{
                event = new UpdateProposalEvent(id, schema, action, timeStamp, proposalId, proposalLoanValue, proposalNumberOfMonthlyInstallments);
            }
        }else {
            event = new RemoveProposalEvent(id, schema, action, timeStamp, proposalId);
        }
        return event;
    }

    private void add(Event event){
        if(eventMap.containsKey(event.getId())){
            return;
        }
        EventTuple tuple = new EventTuple(event.getSchema(), event.getAction(), event.getEventTypeId());
        if(eventMap.containsKey(tuple)){
            Event mapEvent = eventMap.get(tuple);
            if(event.getTimeStamp().isAfter(mapEvent.getTimeStamp())){
                eventMap.remove(tuple);
            }else{
                return;
            }
        }
        eventMap.put(event);
    }

    public void processAll(){
        for(Event event: eventMap.values()){
            event.process(proposalService);
        }
    }
}
