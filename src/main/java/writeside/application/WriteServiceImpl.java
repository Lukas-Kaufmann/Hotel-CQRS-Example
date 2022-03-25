package writeside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writeside.domain.model.Booking;
import writeside.domain.model.Room;
import writeside.domain.repository.BookingRepository;
import writeside.domain.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WriteServiceImpl implements WriteService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public String book(String roomNumber, String customerName, LocalDate start, LocalDate end) {
        Room room = roomRepository.roomByRoomNumber(roomNumber).orElseThrow(IllegalStateException::new);

        List<Booking> bookingsOfRoom = room.getBookingNumbers().stream().map(
                number -> bookingRepository.bookingByNumber(number).orElseThrow(IllegalStateException::new))
                .collect(Collectors.toList());

        boolean isAvailable = true;

        for (Booking b : bookingsOfRoom) {
            if (b.isWithinPeriod(start, end)) {
                isAvailable = false;
                break;
            }
        }
        if (!isAvailable) {
            return "false";
        }

        String bookingNumber = bookingRepository.nextBookingNumber();
        Booking booking = new Booking(bookingNumber, customerName, start, end, roomNumber);
        bookingRepository.add(booking);

        //TODO event

        return bookingNumber;
    }

    @Override
    public boolean cancelBooking(String bookingNumber) {
        bookingRepository.removeBooking(bookingNumber);

        //TODO create event
        return true;
    }
}
