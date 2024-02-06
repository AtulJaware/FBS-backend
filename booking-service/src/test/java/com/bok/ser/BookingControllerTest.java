package com.bok.ser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bok.ser.controller.BookingController;
import com.bok.ser.entity.BookingFlight;
import com.bok.ser.entity.Flight;
import com.bok.ser.entity.User;
import com.bok.ser.service.BookingService;
import java.util.ArrayList;
import java.util.List;

public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBook() {
        // Test the "book" method as shown in the previous example
    }

    @Test
    public void testGetBookingForFlight() {
        // Test the "getBookingForFlight" method as shown in the previous example
    }

    @Test
    public void testGetBookingById() {
        long bookingId = 1L;
        BookingFlight expectedBooking = new BookingFlight();
        // Set expected booking properties

        when(bookingService.getBookingById(bookingId)).thenReturn(expectedBooking);

        Object result = bookingController.getBookindById(bookingId);

        assertEquals(expectedBooking, result);
    }

    @Test
    public void testGetFlightByBooking() {
        long bookingId = 1L;
        long flightId = 1001L;

        when(bookingService.getFlightIdByBooking(bookingId)).thenReturn(flightId);

        ResponseEntity<Object> response = bookingController.getFlightByBooking(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flightId, response.getBody());
    }

    @Test
    public void testCheckedIn() {
        long bookingId = 1L;
        BookingFlight expectedBooking = new BookingFlight();
        // Set expected booking properties

        when(bookingService.checkedIn(bookingId)).thenReturn(expectedBooking);

        ResponseEntity<Object> response = bookingController.checkedIn(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBooking, response.getBody());
    }

    @Test
    public void testGetBookingByFlightName() {
        String flightName = "Flight1";
        List<BookingFlight> bookingList = new ArrayList<>();
        // Add BookingFlight objects to the list

        when(bookingService.getAllBookingByFlightName(flightName)).thenReturn(bookingList);

        ResponseEntity<Object> response = bookingController.getBookingByFlight(flightName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingList, response.getBody());
    }
}
