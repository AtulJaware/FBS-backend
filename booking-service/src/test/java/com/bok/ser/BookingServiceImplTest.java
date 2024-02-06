package com.bok.ser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.bok.ser.entity.BookingFlight;
import com.bok.ser.exception.FlightNotFoundException;
import com.bok.ser.repository.BookingRepository;
import com.bok.ser.service.BookingServiceImpl;
import com.bok.ser.service.SequenceGeneratorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private SequenceGeneratorService sequence;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBookFlight() {
        BookingFlight bookingFlight = new BookingFlight();
        bookingFlight.setId(1L);
        // Set other properties

        when(bookingRepo.findById(bookingFlight.getId())).thenReturn(Optional.empty());
        when(sequence.generateSequence(BookingFlight.SEQUENCE_NAME)).thenReturn(2L);
        when(bookingRepo.save(bookingFlight)).thenReturn(bookingFlight);

        BookingFlight result = bookingService.bookFlight(bookingFlight);

        assertEquals(bookingFlight, result);
    }


    @Test
    public void testGetBookingById() {
        long bookingId = 1L;
        BookingFlight bookingFlight = new BookingFlight();
        // Set bookingFlight properties

        when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(bookingFlight));

        BookingFlight result = bookingService.getBookingById(bookingId);

        assertEquals(bookingFlight, result);
    }


    @Test
    public void testCheckedIn() {
        long id = 1L;
        BookingFlight bookingFlight = new BookingFlight();
        // Set bookingFlight properties

        when(bookingRepo.findById(id)).thenReturn(Optional.of(bookingFlight));
        when(bookingRepo.save(bookingFlight)).thenReturn(bookingFlight);

        BookingFlight result = bookingService.checkedIn(id);

        assertEquals(true, result.isCheckedIn());
    }



    @Test
    public void testGetAllBookingForAFlight() {
        long flightId = 1L;
        List<BookingFlight> bookingList = new ArrayList<>();
        // Add BookingFlight objects to the list

        when(bookingRepo.findByFlightId(flightId)).thenReturn(bookingList);

        List<BookingFlight> result = bookingService.getAllBookingForAFlight(flightId);

        assertEquals(bookingList, result);
    }

    @Test
    public void testGetAllBookingForAFlightEmptyList() {
        long flightId = 1L;

        when(bookingRepo.findByFlightId(flightId)).thenReturn(new ArrayList<>());

        bookingService.getAllBookingForAFlight(flightId);
    }

    @Test
    public void testGetFlightIdByBooking() {
        long bookingId = 1L;
        BookingFlight bookingFlight = new BookingFlight();
        bookingFlight.setFlightId(1001L);

        when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(bookingFlight));

        long result = bookingService.getFlightIdByBooking(bookingId);

        assertEquals(bookingFlight.getFlightId(), result);
    }

    @Test
    public void testGetAllBookingByFlightName() {
        String flightName = "Flight1";
        List<BookingFlight> bookingList = new ArrayList<>();
        // Add BookingFlight objects to the list

        when(bookingRepo.findByFlightNo(flightName)).thenReturn(bookingList);

        List<BookingFlight> result = bookingService.getAllBookingByFlightName(flightName);

        assertEquals(bookingList, result);
    }

    @Test
    public void testGetAllBookingByFlightNameEmptyList() {
        String flightName = "Flight1";

        when(bookingRepo.findByFlightNo(flightName)).thenReturn(new ArrayList<>());

        bookingService.getAllBookingByFlightName(flightName);
    }
}
