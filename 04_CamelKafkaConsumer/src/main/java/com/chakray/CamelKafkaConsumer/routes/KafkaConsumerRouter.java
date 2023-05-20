package com.chakray.CamelKafkaConsumer.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(KafkaConsumerRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("New file found in xml, sending XML Content to kafka");

        from("kafka:exchange-xml")
                .log("${body}")
                .to("log:receivde-message-from-kafka");
    }
}
