package com.chakray.CamelKafkaSender.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(KafkaSenderRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("New file found in xml, sending XML Content to kafka");

        from("file:file/xml")
                .log("${body}")
                .to("kafka:exchange-xml");
    }
}
