package event.proposal;

import event.Event;

import java.time.LocalDateTime;

public abstract  class PropostalEvent extends Event {

    protected String proposalId;

    public PropostalEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId) {
        super(id, schema, action, timeStamp, proposalId);
        this.proposalId = proposalId;
    }

    @Override
    public String getEventTypeId() {
        return this.proposalId;
    }
}
