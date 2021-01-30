package event.warranty;

import service.proposal.ProposalService;

import java.time.LocalDateTime;

public class RemoveWarrantyEvent extends WarrantyEvent{

    public RemoveWarrantyEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String warrantyId) {
        super(id, schema, action, timeStamp, proposalId, warrantyId);
    }

    @Override
    public void process(ProposalService proposalService) {
        proposalService.removeWarranty(proposalId, warrantyId);
    }
}
