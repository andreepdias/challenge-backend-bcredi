package service.proposal.validation;

import domain.Proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProposalValidation {

    List<ValidationProposalRule> validations;

    public ProposalValidation() {
        this.validations = new ArrayList<>(Arrays.asList(
            new ValidationLoanValue(),
            new ValidationInstallmentMonths(),
            new ValidationMinimumProponents(),
            new ValidationMainProponent(),
            new ValidationProponentsAge(),
            new ValidationWarrantiesNumber(),
            new ValidationWarrantyMinimumValue(),
            new ValidationWarrantyState(),
            new ValidationProponentIncome()
        ));
    }

    public Boolean isValid(Proposal proposal){
        for (ValidationProposalRule validationRule : validations) {
            if(!validationRule.isValid(proposal)){
                return false;
            }
        }
        return true;
    }
}
