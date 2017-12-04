package org.iproduct.demos.spring.reactivequotes.handlers;

import lombok.extern.slf4j.Slf4j;
import org.iproduct.demos.spring.reactivequotes.domain.Quote;
import org.iproduct.demos.spring.reactivequotes.services.QuotesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

@Component
@Slf4j
public class ReactiveQuotesHandler {

    @Autowired
    private QuotesGenerator generator;


    public Mono<ServerResponse> streamQuotes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(generator.getQuoteStream(Duration.ofMillis(250)), Quote.class);
    }

    public Mono<ServerResponse> streamQuotesSSE(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(generator.getQuoteStream(Duration.ofMillis(250)), Quote.class);
    }

}
