package event;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

class EventTuple {
    private String schema;
    private String action;
    private String id;

    public EventTuple(String schema, String action, String id) {
        this.schema = schema;
        this.action = action;
        this.id = id;
    }
}

public class EventMap {

    private Map<String, Event> eventIdMap;
    private Map<EventTuple, Event> eventTupleMap;

    public EventMap() {
        this.eventIdMap = new LinkedHashMap<>();
        this.eventTupleMap = new LinkedHashMap<>();
    }

    public boolean containsKey(String eventId){
        return eventIdMap.containsKey(eventId);
    }

    public boolean containsKey(EventTuple tuple){
        return eventTupleMap.containsKey(tuple);
    }

    public Event get(String eventId){
        return eventIdMap.get(eventId);
    }

    public Event get(EventTuple tuple) {
        return eventTupleMap.get(tuple);
    }

    public void remove(EventTuple tuple){
        Event event = eventTupleMap.get(tuple);
        String eventId = event.getId();

        eventIdMap.remove(eventId);
        eventTupleMap.remove(tuple);
    }

    public void remove(String eventId){
        Event event = eventTupleMap.get(eventId);
        EventTuple tuple = new EventTuple(event.getSchema(), event.getAction(), event.getEventTypeId());

        eventIdMap.remove(eventId);
        eventTupleMap.remove(tuple);
    }

    public void put(Event event) {
        String eventId = event.getId();
        eventIdMap.put(eventId, event);

        EventTuple tuple = new EventTuple(event.getSchema(), event.getAction(), event.getEventTypeId());
        eventTupleMap.put(tuple, event);
    }

    public Collection<Event> values() {
        return eventIdMap.values();
    }
}
