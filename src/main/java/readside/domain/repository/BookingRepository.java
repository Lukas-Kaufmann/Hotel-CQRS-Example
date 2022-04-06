package readside.domain.repository;

import readside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    void add(Booking booking);

    void remove(String bookingNumber);

    List<Booking> bookingsWithin(LocalDate start, LocalDate end);
}
