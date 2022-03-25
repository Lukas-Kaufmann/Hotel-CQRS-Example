package writeside.domain.model;

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
    }

    public boolean isWithinPeriod(LocalDate periodStart, LocalDate periodEnd) {
        if (start.isBefore(periodStart) && end.isAfter(periodEnd)) {
            return true;
        }
        if (start.isAfter(periodStart ) && start.isBefore(periodEnd)) {
            return true;
        }
        if (end.isAfter(periodStart) && end.isBefore(periodEnd)) {
            return true;
        }

        return false;
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
