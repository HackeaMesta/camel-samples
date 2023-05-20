package com.chakray.CamelStarter.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderXmlRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(ActiveMqSenderXmlRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("New file found in path, sending XML content to ActiveMq");

        // Watch for files in ./files/xml path and send xml content to ActiveMq
        from("file:files/xml")
                .log("${body}")
                .to("activemq:activemq-xml-exchanges");
    }
}
