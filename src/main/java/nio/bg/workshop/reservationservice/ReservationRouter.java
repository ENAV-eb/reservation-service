package nio.bg.workshop.reservationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Configuration
public class ReservationRouter {

    /*
    private final ReservationRepository reservationRepository;

    public ReservationRouter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/reservations"), 
                     r -> ok()
                          .body(reservationRepository.findAll(),
                                Reservation.class));
    }
    */
}