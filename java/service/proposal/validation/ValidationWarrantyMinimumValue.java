package service.proposal.validation;

import domain.Proposal;

import java.math.BigDecimal;

public class ValidationWarrantyMinimumValue implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        BigDecimal sum = new BigDecimal("0");
        for(var warranty : proposal.getWarranties().values()){
            sum = sum.add(warranty.getValue());
        }
        BigDecimal doubleProposalLoanValue = proposal.getLoanValue().multiply(new BigDecimal("2"));
        return sum.compareTo(doubleProposalLoanValue) == 0 || sum.compareTo(doubleProposalLoanValue) == 1;
    }
}
