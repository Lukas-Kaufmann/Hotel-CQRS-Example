package writeside.domain.model;

import java.util.List;

public class Room {
    private String roomNumber;
    private int beds;
    private List<String> bookingNumbers;

    public Room(String roomNumber, int beds, List<String> bookingNumbers) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.bookingNumbers = bookingNumbers;
    }

    public List<String> getBookingNumbers() {
        return bookingNumbers;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }
}
