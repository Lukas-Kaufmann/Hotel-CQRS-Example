package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.domain.repository.RoomAvailabilityRepository;

import java.time.LocalDate;
import java.util.*;

@Component
public class MemoryRoomAvailabilityRepo implements RoomAvailabilityRepository {
    private static int MAX_DAYS = 100;
    private static int MAX_ROOMS = 10;
    private Map<LocalDate, Integer> freeRooms;

    public MemoryRoomAvailabilityRepo() {
        this.freeRooms = new HashMap<>();
        for (int i = -MAX_DAYS; i < MAX_DAYS; i+=1) {
            freeRooms.put(LocalDate.now().plusDays(i), MAX_ROOMS);
        }
    }

    @Override
    public void changeAvailableRoomsBookingCreated(LocalDate start, LocalDate end) {
        for (LocalDate day = start; day.isBefore(end) || day.isEqual(end); day = day.plusDays(1)) {
            this.freeRooms.put(day, this.freeRooms.get(day) - 1);
        }
    }

    @Override
    public void changeAvailableRoomsBookingCancelled(LocalDate start, LocalDate end) {
        for (LocalDate day = start; day.isBefore(end) || day.isEqual(end); day = day.plusDays(1)) {
            this.freeRooms.put(day, this.freeRooms.remove(day) + 1);
        }
    }

    @Override
    public int freeRoomsWithin(LocalDate start, LocalDate end) {
        int nrFreeRooms = MAX_ROOMS;
        for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1)) {
            if (this.freeRooms.get(date) < nrFreeRooms) {
                nrFreeRooms = this.freeRooms.get(date);
            }
        }
        return nrFreeRooms;
    }
}
