package com.chakray.EIPatterns.routers;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MulticastPatterRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(MulticastPatterRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("Multicast Pattern, send event to multiple endpoints");
        from("timer:multicast-timer?period=15000")
                .to("log:log-endpoint-1", "log:log-endpoint-2", "log:log-endpoint-3");
    }
}
