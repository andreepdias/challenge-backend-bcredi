package event.proponent;

import domain.Proponent;
import service.proposal.ProposalService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateProponentEvent extends ProponentEvent {

    private String name;
    private String age;
    private String monthlyIncome;
    private String isMain;

    public UpdateProponentEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String proponentId, String name, String age, String monthlyIncome, String isMain) {
        super(id, schema, action, timeStamp, proposalId, proponentId);
        this.name = name;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.isMain = isMain;
    }

    @Override
    public void process(ProposalService proposalService) {
        Proponent proponent = new Proponent(proponentId, name, Integer.parseInt(age), new BigDecimal(monthlyIncome), Boolean.parseBoolean(isMain));
        proposalService.updateProponent(proposalId, proponent);
    }
}
