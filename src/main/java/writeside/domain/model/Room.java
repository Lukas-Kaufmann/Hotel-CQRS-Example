package writeside.domain.model;

import java.util.List;

public class Room {
    private String roomNumber;
    private List<String> bookingNumbers;

    public Room(String roomNumber, List<String> bookingNumbers) {
        this.roomNumber = roomNumber;
        this.bookingNumbers = bookingNumbers;
    }

    public List<String> getBookingNumbers() {
        return bookingNumbers;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
