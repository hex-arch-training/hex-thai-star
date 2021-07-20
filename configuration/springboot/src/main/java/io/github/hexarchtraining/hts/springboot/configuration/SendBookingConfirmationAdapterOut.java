package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;
import org.springframework.stereotype.Service;

@Service
public class SendBookingConfirmationAdapterOut implements SendBookingConfirmationPort {
    @Override
    public void send(BookingConfimationEvent event) {
        // just to fulfil dependency, normally we would like to dispatch an event to notification component
    }
}
