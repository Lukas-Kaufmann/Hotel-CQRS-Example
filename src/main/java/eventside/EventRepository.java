package eventside;

import eventside.domain.Event;
import eventside.domain.Listener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import writeside.domain.model.event.BookingCreatedEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class EventRepository {

    private List<Event> events = new ArrayList<>();

    private List<Listener> listeners = new LinkedList<>();

    public void addListener(Listener listener) {
        this.listeners.add( listener);
    }

    public void processEvent(Event event) {
        events.add(event);
        for (Listener l : listeners) {
            if (l.getSubscribedTo().equals(event.getClass())) {
                WebClient webClient = WebClient.create("http://" + l.getHost_port());

                webClient.post()
                        .uri("/processEvent/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(event), Event.class)
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();
            }
        }
    }

    public void sendExistingEvents(Listener listener) {
        for (Event e : events) {
            if (e.getClass().equals(listener.getSubscribedTo())) {
                WebClient webClient = WebClient.create("http://" + listener.getHost_port());

                webClient.post()
                        .uri("/processEvent/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), Event.class)
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();
            }
        }
    }
}
