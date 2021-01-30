package event.proposal;

import domain.Proposal;
import service.proposal.ProposalService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public  class UpdateProposalEvent extends PropostalEvent {

    private String loanValue;
    private String numberOfMonthlyInstallments;

    public UpdateProposalEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String loanValue, String numberOfMonthlyInstallments) {
        super(id, schema, action, timeStamp, proposalId);
        this.loanValue = loanValue;
        this.numberOfMonthlyInstallments = numberOfMonthlyInstallments;
    }

    @Override
    public void process(ProposalService proposalService) {
        Proposal proposal = new Proposal(proposalId, new BigDecimal(loanValue), Integer.parseInt(numberOfMonthlyInstallments));
        proposalService.updateProposal(proposal);
    }
}
