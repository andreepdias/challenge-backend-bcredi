package service.proposal.validation;

import domain.Proposal;

public class ValidationMinimumProponents implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        return proposal.getProponents().size() >= 2;
    }
}
