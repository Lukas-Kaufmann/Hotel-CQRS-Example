package writeside.infrastructure;

import org.springframework.stereotype.Component;
import writeside.domain.model.Booking;
import writeside.domain.model.Room;
import writeside.domain.repository.BookingRepository;
import writeside.domain.repository.RoomRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryRepository implements BookingRepository, RoomRepository {
    private List<Room> rooms;
    private List<Booking> bookings;

    public MemoryRepository() {
        this.rooms = new LinkedList<>();
        this.bookings = new LinkedList<>();
        //TODO dummy data

        this.rooms.add(new Room("L001", 2, new LinkedList<>()));
        this.rooms.add(new Room("L002", 2, new LinkedList<>()));
        this.rooms.add(new Room("L003", 2, new LinkedList<>()));
        this.rooms.add(new Room("L004", 2, new LinkedList<>()));
        this.rooms.add(new Room("L005", 2, new LinkedList<>()));

    }

    private int largestBookingNumber = 1;

    @Override
    public void removeBooking(String bookingNumber) {
        bookings.removeIf(b -> b.getBookingNumber().equals(bookingNumber));

        for (Room room : rooms) {
            room.getBookingNumbers().removeIf(number -> number.equals(bookingNumber));
        }
    }

    @Override
    public String nextBookingNumber() {
        largestBookingNumber += 1;
        return String.format("%05d", largestBookingNumber);
    }

    @Override
    public void add(Booking booking) {
        this.bookings.add(booking);
    }

    @Override
    public Optional<Booking> bookingByNumber(String bookingNumber) {
        return this.bookings.stream().filter(b -> b.getBookingNumber().equals(bookingNumber)).findFirst();
    }

    @Override
    public void add(Room room) {
        this.rooms.add(room);
    }

    @Override
    public Optional<Room> roomByRoomNumber(String roomNumber) {
        return this.rooms.stream().filter(room -> room.getRoomNumber().equals(roomNumber)).findFirst();
    }
}
