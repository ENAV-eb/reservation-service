package nio.bg.workshop.reservationservice;


import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation,String> {

    @Query("select * from reservation where name =$ 1")
    Flux<Reservation> findByName(String name);
}