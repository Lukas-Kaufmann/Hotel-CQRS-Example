package readside.domain;

import java.time.LocalDate;

public class Booking {
    private String bookingNumber;
    private String customerName;
    private LocalDate start;
    private LocalDate end;

    public Booking(String bookingNumber, String customerName, LocalDate start, LocalDate end) {
        this.bookingNumber = bookingNumber;
        this.customerName = customerName;
        this.start = start;
        this.end = end;
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
}
