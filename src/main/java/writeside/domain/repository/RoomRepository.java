package writeside.domain.repository;

import writeside.domain.model.Room;

import java.util.Optional;

public interface RoomRepository {

    void add(Room room);

    Optional<Room> roomByRoomNumber(String roomNumber);
}
