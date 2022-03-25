package writeside.application;

import java.time.LocalDate;

public interface WriteService {
    String book(String roomNumber, String customerName, LocalDate start, LocalDate end);

    boolean cancelBooking(String bookingNumber);
}
