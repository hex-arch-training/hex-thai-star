package io.github.hexarchtraining.hts.quarkus.adapter.out;

import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SendBookingConfirmationDummyAdapter implements SendBookingConfirmationPort {
    @Override
    public void send(BookingConfimationEvent event) {
        // just to fulfil dependency, normally we would like to dispatch an event to notification component
    }
}
