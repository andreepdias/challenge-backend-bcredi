package event.proponent;

import event.Event;

import java.time.LocalDateTime;

public abstract class ProponentEvent extends Event {

    protected String proponentId;

    public ProponentEvent(String id, String schema, String action, LocalDateTime timeStamp, String proposalId, String proponentId) {
        super(id, schema, action, timeStamp, proposalId);
        this.proponentId = proponentId;
    }

    @Override
    public String getEventTypeId() {
        return this.proponentId;
    }
}
