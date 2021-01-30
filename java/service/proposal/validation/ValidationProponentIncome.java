package service.proposal.validation;

import domain.Proponent;
import domain.Proposal;

import java.math.BigDecimal;
import java.math.MathContext;

public class ValidationProponentIncome implements ValidationProposalRule {

    @Override
    public boolean isValid(Proposal proposal) {
        for(Proponent proponent : proposal.getProponents().values()){
            if(proponent.getMain() == true){
                return isProponentIncomeValid(proponent.getAge(), proponent.getMonthlyIncome(), proposal.getLoanValue(), proposal.getNumberOfMonthlyInstallments());
            }
        }
        return false;
    }

    private boolean isProponentIncomeValid(Integer age, BigDecimal monthlyIncome, BigDecimal loanValue, Integer numberOfMonthlyInstallments) {
        if(numberOfMonthlyInstallments == 0) return false;

        BigDecimal installment = loanValue.divide(new BigDecimal(numberOfMonthlyInstallments), MathContext.DECIMAL128);
        if(age >= 18 & age < 24){
            BigDecimal fourTimesInstallment = installment.multiply(new BigDecimal("4"));
            return monthlyIncome.compareTo(fourTimesInstallment) == 0 || monthlyIncome.compareTo(fourTimesInstallment) == 1;
        }else if(age >= 24 && age < 50){
            BigDecimal threeTimesInstallment = installment.multiply(new BigDecimal("3"));
            return monthlyIncome.compareTo(threeTimesInstallment) == 0 || monthlyIncome.compareTo(threeTimesInstallment) == 1;
        }else if(age >= 50){
            BigDecimal twoTimesInstallment = installment.multiply(new BigDecimal("2"));
            return monthlyIncome.compareTo(twoTimesInstallment) == 0 || monthlyIncome.compareTo(twoTimesInstallment) == 1;
        }
        return false;
    }
}
