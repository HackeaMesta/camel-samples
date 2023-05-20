package com.chakray.CamelStarter.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(ActiveMqSenderRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("Sending sample message to ActiveMq every 10 seconds");

        // Send Sample text to ActiveMq
        from("timer:active-mq-timer?period=10000")
                .transform().constant("My message for Active MQ")
                .log("${body}")
                .to("activemq:my-activemq-queue");
    }
}
