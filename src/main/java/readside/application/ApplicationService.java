package readside.application;

import org.springframework.stereotype.Component;
import writeside.domain.model.event.BookingCanceledEvent;
import writeside.domain.model.event.BookingCreatedEvent;

@Component
public class ApplicationService {

    public boolean createdBooking(BookingCreatedEvent event) {
        System.out.println("READ: created booking " + event.getBookingNumber() + " " + event.getCustomerName());

        return false;
    }


    public boolean canceledBooking(BookingCanceledEvent event) {
        System.out.println("READ: cancelled Booking " + event.getBookingNumber());
        return false;
    }
}
