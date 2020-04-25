package nio.bg.workshop.reservationservice;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DbClientListener {

    private final DatabaseClient databaseClient;

    public DbClientListener(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }
/*
    public DbClientListener(DatabaseClient.Builder databaseClientBuilder) {
        this.databaseClient = databaseClientBuilder.build();
    }
*/
    @EventListener(ApplicationReadyEvent.class)
    public void dbc() throws Exception {
        this.databaseClient
                .select().from("reservation")
                .as(Reservation.class).fetch().all()
                .subscribe(log::info);
    }


}