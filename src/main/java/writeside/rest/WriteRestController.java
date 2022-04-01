package writeside.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLTableCaptionElement;
import writeside.application.WriteService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
public class WriteRestController {
    @Autowired
    private WriteService writeService;

    @PostMapping(value = "/booking/room/{roomNumber}/customer/{customerName}/{start}/{end}")
    public String book(
        @PathVariable String roomNumber,
        @PathVariable String customerName,
        @PathVariable String start,
        @PathVariable String end,
        HttpServletResponse response
    ) {
        String result = writeService.book(roomNumber, customerName, LocalDate.parse(start), LocalDate.parse(end));
        if (result.equals("false")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        return result;
    }

    @PostMapping(value="/booking/cancel/{bookingNumber}")
    public String cancel(@PathVariable String bookingNumber) {
        return String.valueOf(writeService.cancelBooking(bookingNumber));
    }

}
