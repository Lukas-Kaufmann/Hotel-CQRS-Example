package writeside.domain.model.event;

import eventside.domain.Event;

import java.time.LocalDate;

public class BookingCreatedEvent extends Event {
    private String bookingNumber;
    private String roomNumber;
    private String customerName;
    private LocalDate start;
    private LocalDate end;

    public BookingCreatedEvent() {
    }

    public BookingCreatedEvent(String bookingNumber, String roomNumber, String customerName, LocalDate start, LocalDate end) {
        super();
        this.bookingNumber = bookingNumber;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.start = start;
        this.end = end;
    }
    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
