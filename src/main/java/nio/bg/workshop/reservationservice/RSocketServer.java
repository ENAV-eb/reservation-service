package nio.bg.workshop.reservationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RSocketServer {

    private final ReservationRepository reservationRepository;
    private final TcpServerTransport tcpServerTransport;
    private final ObjectMapper objectMapper;

    public RSocketServer(ReservationRepository reservationRepository, ObjectMapper objectMapper) {
        this.reservationRepository = reservationRepository;
        this.objectMapper = objectMapper;
        this.tcpServerTransport = TcpServerTransport.create(7000);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void server() throws Exception {

        SocketAcceptor socketAcceptor = (setup, requestRS) -> {
            RSocket reply = new AbstractRSocket() {
                
                @Override
                public Flux<Payload> requestStream(Payload ignoreMe) {
                    return reservationRepository.findAll()
                                .map(RSocketServer.this::to)
                                .map(DefaultPayload::create);
                }
            };

            return Mono.just(reply);
        };

        RSocketFactory
            .receive()
            .acceptor(socketAcceptor)
            .transport(this.tcpServerTransport)
            .start()
            .subscribe();
    }

    private String to(Reservation r) {
        try {
            return this.objectMapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}