package readside.rest;

import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import readside.application.ApplicationService;
import writeside.domain.model.event.BookingCanceledEvent;
import writeside.domain.model.event.BookingCreatedEvent;

@RestController
public class EventProcessingRestController {

    //TODO maybe publis/subscribe mechanism

    @Autowired
    private ApplicationService applicationService;

    @PostMapping(value= "/processEvent/")
    public boolean process(@RequestBody Event event) {
        if (event instanceof BookingCreatedEvent) {
            return applicationService.createdBooking( (BookingCreatedEvent) event);
        } else if (event instanceof BookingCanceledEvent) {
            return applicationService.canceledBooking( (BookingCanceledEvent) event);
        }

        return false;
    }
}
