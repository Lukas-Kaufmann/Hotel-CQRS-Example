package readside.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import readside.application.QueryApplicationService;
import readside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private QueryApplicationService queryApplicationService;

    @GetMapping(value = "/bookings/{start}/{end}")
    public List<Booking> bookingsFromTo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return queryApplicationService.getBookingsWithin(start, end);
    }

    @GetMapping(value = "/availableRooms/{start}/{end}")
    public int availableRooms(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        return queryApplicationService.amountOfRoomsAvailable(start, end);
    }
}
