package io.github.hexarchtraining.hts.booking.usecase;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.domain.exception.IncompleteBookingException;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmBookingUseCase implements ConfirmBookingPort {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final SaveBookingPort saveBookingPort;

    private final SendBookingStatusUseCase sendBookingStatusUseCase;

    @Override
    public void confirm(ConfirmBookingCommand command) {
        final Booking booking = findBookingByTokenPort.find(command.getToken()).orElseThrow(() -> new BookingNotFoundException(command.getToken()));
        if (!booking.hasSufficientTables()) {
            throw new IncompleteBookingException(booking.getId(), "lack of table booking");
        }
        booking.confirm();
        saveBookingPort.save(booking);

        sendBookingStatusUseCase.sendBookingStatus(booking);
    }
}
