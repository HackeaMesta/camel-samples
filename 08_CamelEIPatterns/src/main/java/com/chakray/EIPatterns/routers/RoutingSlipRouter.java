package com.chakray.EIPatterns.routers;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RoutingSlipRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(RoutingSlipRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("Send message to router slip each 5 seconds");

        String routingSlip = "direct:endpoint1,direct:endpoint2";

        from("timer:routing-slip?period=5000")
                .transform().constant("Message to routing Slip")
                .routingSlip(simple(routingSlip));


        from("direct:endpoint1")
                .to("log:endpoint1");
        from("direct:endpoint2")
                .to("log:endpoint2");
        from("direct:endpoint3")
                .to("log:endpoint3");

    }
}
