package service.proposal;

import domain.Proponent;
import domain.Proposal;
import domain.Warranty;
import exception.ObjectDuplicatedException;
import exception.ObjectNotFoundException;
import service.proposal.validation.ProposalValidation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProposalService {

    private final Map<String, Proposal> proposals;
    private final ProposalValidation validation;

    public ProposalService() {
        this.proposals = new LinkedHashMap();
        this.validation = new ProposalValidation();
    }

    /* PROPOSAL METHODS */

    public Proposal findProposal(String proposalId){
        if(!proposals.containsKey(proposalId)){
            throw new ObjectNotFoundException("Proposal with id " + proposalId + " was not found.");
        }
        return proposals.get(proposalId);
    }

    public void addProposal(Proposal proposal) {
        boolean existsProposalById = existsProposalById(proposal.getId());
        if(existsProposalById){
            throw new ObjectDuplicatedException("Proposal with id " + proposal.getId() + " was already created.");
        }

        proposals.put(proposal.getId(), proposal);
    }

    public void removeProposal(String id) {
        boolean existsProposalById = existsProposalById(id);
        if(!existsProposalById){
            throw new ObjectNotFoundException("Proposal with id " + id + " was not found.");
        }
        proposals.remove(id);
    }

    public void updateProposal(Proposal proposal) {
        boolean existsProposalById = existsProposalById(proposal.getId());
        if(!existsProposalById){
            throw new ObjectNotFoundException("Proposal with id " + proposal.getId() + " was not found.");
        }
        proposals.put(proposal.getId(), proposal);
    }

    private boolean existsProposalById(String id) {
        return proposals.containsKey(id);
    }

    /* PROPONENT METHODS */

    public void addProponent(String proposalId, Proponent proponent) {
        Proposal proposal = findProposal(proposalId);

        boolean existsProponentById = existsProponentById(proposal, proponent.getId());
        if(existsProponentById){
            throw new ObjectDuplicatedException("Proponent with id " + proponent.getId() + " was already created.");
        }

        proposal.getProponents().put(proponent.getId(), proponent);
    }

    public void removeProponent(String proposalId, String id) {
        Proposal proposal = findProposal(proposalId);

        boolean existsProponentById = existsProponentById(proposal, id);
        if(!existsProponentById){
            throw new ObjectNotFoundException("Proponent with id " + id + " was not found.");
        }
        proposal.getProponents().remove(id);
    }

    public void updateProponent(String proposalId, Proponent proponent) {
        Proposal proposal = findProposal(proposalId);

        boolean existsProponentById = existsProponentById(proposal, proponent.getId());
        if(!existsProponentById){
            throw new ObjectNotFoundException("Proponent with id " + proponent.getId() + " was not found.");
        }
        proposal.getProponents().put(proponent.getId(), proponent);
    }

    private boolean existsProponentById(Proposal proposal, String id) {
        return proposal.getProponents().containsKey(id);
    }

    /* WARRANTY METHODS */

    public void addWarranty(String proposalId, Warranty warranty) {
        Proposal proposal = findProposal(proposalId);

        boolean existsWarrantyById = existsWarrantyById(proposal, warranty.getId());
        if(existsWarrantyById){
            throw new ObjectDuplicatedException("Warranty with id " + warranty.getId() + " was already created.");
        }

        proposal.getWarranties().put(warranty.getId(), warranty);
    }

    public void removeWarranty(String proposalId, String id) {
        Proposal proposal = findProposal(proposalId);

        boolean existsWarrantyById = existsWarrantyById(proposal, id);
        if(!existsWarrantyById){
            throw new ObjectNotFoundException("Warranty with id " + id + " was not found.");
        }
        proposal.getWarranties().remove(id);
    }

    public void updateWarranty(String proposalId, Warranty warranty) {
        Proposal proposal = findProposal(proposalId);

        boolean existsWarrantyById = existsWarrantyById(proposal, warranty.getId());
        if(!existsWarrantyById){
            throw new ObjectNotFoundException("Warranty with id " + warranty.getId() + " was not found.");
        }
        proposal.getWarranties().put(warranty.getId(), warranty);
    }

    private boolean existsWarrantyById(Proposal proposal, String id) {
        return proposal.getWarranties().containsKey(id);
    }

    public List<Proposal> getValidProposals() {
        List<Proposal> validProposals = new ArrayList<>();

        for(var proposal: proposals.values()){
            if(validation.isValid(proposal)){
                validProposals.add(proposal);
            }
        }
        return validProposals;
    }
}
