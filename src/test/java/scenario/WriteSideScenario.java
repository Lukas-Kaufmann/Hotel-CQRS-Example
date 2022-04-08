package scenario;

import eventside.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import readside.domain.Booking;

@SpringBootTest
@SpringBootConfiguration
public class WriteSideScenario {

    @Test
    void scenario() throws InterruptedException {
        System.out.println("Starting scenario");
        //simple scenario that creates 2 bookings
        // requires the application to be running

        WebClient writeClient = WebClient.create("http://localhost:8081");
        writeClient.post()
                .uri("/booking/room/L001/customer/max/2022-04-15/2022-04-20")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().toBodilessEntity().block();
        System.out.println("send create booking call for customer max");

        writeClient.post()
                .uri("/booking/room/L001/customer/tom/2022-04-10/2022-04-30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().toBodilessEntity().block();
        System.out.println("send create booking call for customer tom");

        WebClient readClient = WebClient.create("http://localhost:8082");
        System.out.println("Subscribing to the events from readside");
        readClient.get().uri("/subscribeHelper").retrieve().bodyToMono(Boolean.class).block();

        System.out.println("waiting to give the readside time to update");
        Thread.sleep(2000);


        Integer availableRooms = readClient.get()
                .uri("/availableRooms/2022-04-10/2022-04-30")
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        System.out.println(availableRooms + " available rooms for the period from april 10th to aprul 30th");

    }
}
