package writeside.domain.model.event;

import eventside.domain.Event;

import java.time.LocalDate;

public class BookingCanceledEvent extends Event {
    private String bookingNumber;
    private LocalDate start;
    private LocalDate end;

    private BookingCanceledEvent() {
    }

    public BookingCanceledEvent(String bookingNumber, LocalDate start, LocalDate end) {
        this.bookingNumber = bookingNumber;
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }
}
