package service.proposal.validation;

import domain.Proposal;

public class ValidationWarrantyState implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        for(var warranty : proposal.getWarranties().values()){
            if(!warranty.isAccepted()){
                return false;
            }
        }
        return true;
    }
}
