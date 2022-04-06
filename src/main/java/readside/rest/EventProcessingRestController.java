package readside.rest;

import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import readside.application.EventProcessingApplicationService;
import writeside.domain.model.event.BookingCanceledEvent;
import writeside.domain.model.event.BookingCreatedEvent;

import javax.annotation.PostConstruct;

@RestController
public class EventProcessingRestController {

    //TODO maybe publis/subscribe mechanism

    @Autowired
    private EventProcessingApplicationService eventProcessingApplicationService;

    @GetMapping(value = "/subscribeHelper")
    void subscribeTo() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.post()
                .uri("/subscribe/localhost/8082/to/" + BookingCreatedEvent.class.toString())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        webClient.post()
                .uri("/subscribe/localhost/8082/to/" + BookingCanceledEvent.class.toString())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @PostMapping(value= "/processEvent/")
    public boolean process(@RequestBody Event event) {
        if (event instanceof BookingCreatedEvent) {
            return eventProcessingApplicationService.createdBooking( (BookingCreatedEvent) event);
        } else if (event instanceof BookingCanceledEvent) {
            return eventProcessingApplicationService.canceledBooking( (BookingCanceledEvent) event);
        }

        return false;
    }
}
