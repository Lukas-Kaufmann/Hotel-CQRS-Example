package writeside.domain.model;

import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writeside.EventPublisher;
import writeside.domain.model.event.BookingCreatedEvent;

import java.time.LocalDate;
import java.util.List;

public class Booking {

    private String bookingNumber;
    private String customerName;
    private LocalDate start;
    private LocalDate end;
    private String roomNumber;

    public Booking(String bookingNumber, String customerName, LocalDate start, LocalDate end, String roomNumber) {
        this.bookingNumber = bookingNumber;
        this.customerName = customerName;
        this.start = start;
        this.end = end;
        this.roomNumber = roomNumber;

        Event event = new BookingCreatedEvent(bookingNumber, roomNumber, customerName, start, end);
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.publishEvent(event);
    }

    public boolean isWithinPeriod(LocalDate periodStart, LocalDate periodEnd) {
        if (start.isBefore(periodStart) && end.isAfter(periodEnd)) {
            return true;
        }
        if (start.isAfter(periodStart ) && start.isBefore(periodEnd)) {
            return true;
        }
        return end.isAfter(periodStart) && end.isBefore(periodEnd);
    }


    public String getBookingNumber() {
        return bookingNumber;
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

    public String getRoomNumber() {
        return roomNumber;
    }
}
