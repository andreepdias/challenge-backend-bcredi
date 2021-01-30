package event.warranty;

import domain.Warranty;
import service.proposal.ProposalService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateWarrantyEvent extends WarrantyEvent{

    private String value;
    private String province;

    public UpdateWarrantyEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String warrantyId, String value, String province) {
        super(id, schema, action, timeStamp, proposalId, warrantyId);
        this.value = value;
        this.province = province;
    }

    @Override
    public void process(ProposalService proposalService) {
        Warranty warranty = new Warranty(warrantyId, new BigDecimal(value), province);
        proposalService.updateWarranty(proposalId, warranty);
    }
}
