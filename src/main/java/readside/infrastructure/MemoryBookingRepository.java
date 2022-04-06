package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.domain.Booking;
import readside.domain.repository.BookingRepository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoryBookingRepository implements BookingRepository {

    private List<Booking> bookings;

    public MemoryBookingRepository() {
        this.bookings = new LinkedList<>();
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void remove(String bookingNumber) {
        bookings.removeIf(booking -> booking.getBookingNumber().equals(bookingNumber));
    }

    @Override
    public List<Booking> bookingsWithin(LocalDate start, LocalDate end) {
        return this.bookings.stream().filter(booking -> {
            if (start.isBefore(booking.getStart()) && end.isAfter(booking.getEnd())) {
                return true;
            }
            if (start.isAfter(booking.getStart() ) && start.isBefore(booking.getEnd())) {
                return true;
            }
            if (end.isAfter(booking.getStart()) && end.isBefore(booking.getEnd()) ) {
                return true;
            }
            if (booking.getStart().isBefore(start) && booking.getEnd().isAfter(end)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }
}
