package com.chakray.CamelStarter.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderJsonRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(ActiveMqSenderJsonRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("New file found in path, sending json content to ActiveMq");

        // Watch for files in ./files/json path and send json content to ActiveMq
        from("file:files/json")
                .log("${body}")
                .to("activemq:activemq-exchanges");
    }
}
