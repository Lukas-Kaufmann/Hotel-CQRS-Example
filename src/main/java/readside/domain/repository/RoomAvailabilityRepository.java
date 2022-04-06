package readside.domain.repository;

import java.time.LocalDate;
import java.util.HashMap;

public interface RoomAvailabilityRepository {

    int freeRoomsWithin(LocalDate start, LocalDate end);

    void changeAvailableRoomsBookingCreated(LocalDate start, LocalDate end);

    void changeAvailableRoomsBookingCancelled(LocalDate start, LocalDate end);
}
