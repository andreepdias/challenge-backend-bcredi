package service.proposal.validation;

import domain.Proposal;

public interface ValidationProposalRule {

    boolean isValid(Proposal proposal);

}
