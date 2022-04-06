package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.domain.Booking;
import readside.domain.repository.BookingRepository;
import readside.domain.repository.RoomAvailabilityRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class QueryApplicationService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public List<Booking> getBookingsWithin(LocalDate start, LocalDate end) {
        return bookingRepository.bookingsWithin(start, end);
    }

    public int amountOfRoomsAvailable(LocalDate start, LocalDate end) {
        return roomAvailabilityRepository.freeRoomsWithin(start, end);
    }

}
