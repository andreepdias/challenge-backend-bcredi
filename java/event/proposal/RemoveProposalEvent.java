package event.proposal;

import service.proposal.ProposalService;

import java.time.LocalDateTime;

public  class RemoveProposalEvent extends PropostalEvent {

    public RemoveProposalEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        super(id, schema, action, timeStamp, proposalId);
    }

    @Override
    public void process(ProposalService proposalService) {
        proposalService.removeProposal(proposalId);
    }
}
