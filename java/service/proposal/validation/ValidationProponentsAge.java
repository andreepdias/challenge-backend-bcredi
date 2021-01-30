package service.proposal.validation;

import domain.Proposal;

public class ValidationProponentsAge implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        for(var proponent : proposal.getProponents().values()){
            if(proponent.getAge() < 18){
                return false;
            }
        }
        return true;
    }
}
