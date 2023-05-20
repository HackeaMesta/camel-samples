package com.chakray.EIPatterns.routers;

import com.chakray.EIPatterns.tools.DinamycRouterBean;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicRoutingRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(DynamicRoutingRouter.class);

    @Autowired
    DinamycRouterBean dinamycRouterBean;

    @Override
    public void configure() throws Exception {
        // Tracing (Wire logs) logs
        getContext().setTracing(true);
        // Send errors to qctivemq
        errorHandler(deadLetterChannel("activemq:dead-letter-channel"));

        // Take the period value from "period" property configurable in application.properties file
        from("timer:routing-dynamic?period={{period}}")
                .transform().constant("Message to routing Slip")
                .dynamicRouter(method(dinamycRouterBean));

        // Route endpoint configurable in application.properties file
        from("{{dynamic-endpoint-1}}")
                .to("log:dynamic-endpoint1");

        from("direct:dynamic-endpoint2")
                .wireTap("log:wire-tap")
                .to("{{log-dynamic-endpoint-2}}");
    }
}
