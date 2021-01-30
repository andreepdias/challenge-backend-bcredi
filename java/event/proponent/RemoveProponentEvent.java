package event.proponent;

import service.proposal.ProposalService;

import java.time.LocalDateTime;

public class RemoveProponentEvent extends ProponentEvent {

    public RemoveProponentEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String proponentId) {
        super(id, schema, action, timeStamp, proposalId, proponentId);
    }

    @Override
    public void process(ProposalService proposalService) {
        proposalService.removeProponent(proposalId, proponentId);

    }
}
