package event.warranty;

import event.Event;

import java.time.LocalDateTime;

public abstract class WarrantyEvent  extends Event {

    protected String warrantyId;

    public WarrantyEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String warrantyId) {
        super(id, schema, action, timeStamp, proposalId);
        this.warrantyId = warrantyId;
    }

    @Override
    public String getEventTypeId() {
        return this.warrantyId;
    }
}
