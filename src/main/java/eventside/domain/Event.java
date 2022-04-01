package eventside.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import writeside.domain.model.event.BookingCanceledEvent;
import writeside.domain.model.event.BookingCreatedEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= BookingCanceledEvent.class, name = "bookingCanceled"),
        @JsonSubTypes.Type(value = BookingCreatedEvent.class, name = "bookingCreated"),
})
public class Event {
    protected long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public Event() {
        this.timestamp = System.currentTimeMillis();
    }
}
