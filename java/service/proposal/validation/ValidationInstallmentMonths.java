package service.proposal.validation;

import domain.Proposal;

import java.math.BigDecimal;

public class ValidationInstallmentMonths implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        Integer minimumInstallmentMonths = 2 * 12;
        Integer maximumInstallmentMonths = 15 * 12;

        Integer numberOfMonthlyInstallments = proposal.getNumberOfMonthlyInstallments();

        return numberOfMonthlyInstallments >= minimumInstallmentMonths && numberOfMonthlyInstallments <= maximumInstallmentMonths;
    }
}
