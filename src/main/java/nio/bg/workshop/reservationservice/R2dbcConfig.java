package nio.bg.workshop.reservationservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories 
public class R2dbcConfig  extends AbstractR2dbcConfiguration{

    
    private final PostgresqlConnectionFactory pgcf;

    public R2dbcConfig () {
        this.pgcf = new PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .username("foo")
                .password("foobar")
                .host("172.17.0.2")
                .database("orders")
                .build()
            );
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return this.pgcf;
    }
    
}