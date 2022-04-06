package eventside.rest;

import eventside.EventRepository;
import eventside.domain.Event;
import eventside.domain.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import writeside.domain.model.event.BookingCreatedEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        System.out.println("Event received: " + event.getClass().toString());
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value="/subscribe/{host}/{port}/to/{classname}")
    public boolean subscribe(@PathVariable String classname, @PathVariable String host, @PathVariable String port) {
        try {
            Listener listener = new Listener(host + ":" + port, Class.forName(classname.split(" ")[1]));
            repository.addListener(listener);
            System.out.println("added " + host + ":" + port + " to subcribers");

            repository.sendExistingEvents(listener);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO send state of events
        return false;
    }



}
