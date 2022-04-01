package writeside.domain.model.event;

import eventside.domain.Event;

public class BookingCanceledEvent extends Event {
    private String bookingNumber;

    public BookingCanceledEvent() {
    }

    public BookingCanceledEvent(String bookingNumber) {
        super();
        this.bookingNumber = bookingNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }
}
