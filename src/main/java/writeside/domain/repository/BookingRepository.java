package writeside.domain.repository;

import writeside.domain.model.Booking;

import java.util.Optional;

public interface BookingRepository {

    void add(Booking booking);

    String nextBookingNumber();

    Optional<Booking> bookingByNumber(String bookingNumber);

    void removeBooking(String bookingNumber);
}
