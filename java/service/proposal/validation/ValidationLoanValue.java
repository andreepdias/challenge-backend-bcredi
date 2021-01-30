package service.proposal.validation;

import domain.Proposal;

import java.math.BigDecimal;

public class ValidationLoanValue implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        BigDecimal loanValue = proposal.getLoanValue();

        BigDecimal minimum = new BigDecimal("30000");
        BigDecimal maximum = new BigDecimal("3000000");

        boolean isGreaterOrEqualToMinimum = loanValue.compareTo(minimum) == 0 || loanValue.compareTo(minimum) == 1;
        boolean isLesserOrEqualToMaximum = loanValue.compareTo(maximum) == 0 || loanValue.compareTo(maximum) == -1;

        return isGreaterOrEqualToMinimum && isLesserOrEqualToMaximum;
    }
}
