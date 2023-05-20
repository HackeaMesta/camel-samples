package com.chakray.CamelRestAPIConsumer.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestApiConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost")
                        .port("8080");

        from("timer:consume-api-rest?period=5000")
                .setHeader("from", () -> "EUR")
                .setHeader("to", () -> "MXN")
                .log("${body}")
                .to("rest:get://currency/exchange/from/${from}/to/${to}")
                .log("${body}");
    }
}
