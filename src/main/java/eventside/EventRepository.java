package eventside;

import eventside.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import writeside.domain.model.event.BookingCreatedEvent;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private List<Event> events = new ArrayList<>();

    private final WebClient webClient = WebClient.create("http://localhost:8082");

    public void processEvent(Event event) {
        events.add(event);
        System.out.println(event.getClass().toString());
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
