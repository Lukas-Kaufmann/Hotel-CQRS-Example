package eventside.rest;

import eventside.EventRepository;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import writeside.domain.model.event.BookingCreatedEvent;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        System.out.println("Event received: " + event);
        // TODO: process event in repository
        repository.processEvent(event);
        return true;
    }



}
