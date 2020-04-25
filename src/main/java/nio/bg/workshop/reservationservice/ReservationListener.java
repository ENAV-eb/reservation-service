package nio.bg.workshop.reservationservice;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@Log4j2
public class ReservationListener {

    private final ReservationRepository reservationRepository;

    public ReservationListener(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void go() throws Exception {
        this.reservationRepository
                .deleteAll()
                .thenMany(Flux.just("A", "B", "C", "D")
                    .map(name -> new Reservation(null,name))
                    .flatMap(this.reservationRepository::save))
                .thenMany(this.reservationRepository.findAll())
                .subscribe(log::info);

    }
}