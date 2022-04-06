package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.domain.Booking;
import readside.infrastructure.MemoryRoomAvailabilityRepo;
import readside.domain.repository.BookingRepository;
import writeside.domain.model.event.BookingCanceledEvent;
import writeside.domain.model.event.BookingCreatedEvent;

@Component
public class EventProcessingApplicationService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemoryRoomAvailabilityRepo memoryRoomAvailabilityRepo;

    public boolean createdBooking(BookingCreatedEvent event) {
        System.out.println("READ: created booking " + event.getBookingNumber() + " " + event.getCustomerName());
        bookingRepository.add(new Booking(event.getBookingNumber(), event.getCustomerName(), event.getStart(), event.getEnd()));
        memoryRoomAvailabilityRepo.changeAvailableRoomsBookingCreated(event.getStart(), event.getEnd());
        return true;
    }


    public boolean canceledBooking(BookingCanceledEvent event) {
        System.out.println("READ: cancelled Booking " + event.getBookingNumber());
        bookingRepository.remove(event.getBookingNumber());
        memoryRoomAvailabilityRepo.changeAvailableRoomsBookingCancelled(event.getStart(), event.getEnd());
        return true;
    }
}
