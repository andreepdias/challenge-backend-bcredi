package event;

import service.proposal.ProposalService;

import java.time.LocalDateTime;

public abstract class Event {

    protected String id;
    protected String schema;
    protected String action;
    protected LocalDateTime timeStamp;
    protected String proposalId;

    public Event(String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        this.id = id;
        this.schema = schema;
        this.action = action;
        this.timeStamp = timeStamp;
        this.proposalId = proposalId;
    }

    public String getId() {
        return id;
    }

    public String getSchema() {
        return schema;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public abstract String getEventTypeId();

    public abstract void process(ProposalService proposalService);
}
