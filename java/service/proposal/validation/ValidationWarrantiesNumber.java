package service.proposal.validation;

import domain.Proposal;

public class ValidationWarrantiesNumber implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        return proposal.getWarranties().size() >= 1;
    }
}
