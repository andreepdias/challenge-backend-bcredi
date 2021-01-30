package service.proposal.validation;

import domain.Proposal;

public class ValidationMainProponent implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        int count = 0;
        for(var proponent : proposal.getProponents().values()){
            if(proponent.getMain() == true){
                count++;
            }
        }
        return count == 1;
    }
}
