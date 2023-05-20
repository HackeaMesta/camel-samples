package com.chakray.EIPatterns.routers;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(ContentBasedRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("Listen new files in input directory and move it to output directory");
        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform()
                .body(String.class)
                .choice()
                .when(simple("${file:ext} == 'xml'"))
                .log("XML File")
                .when(simple("${file:ext} == 'json'"))
                .log("JSON File")
                .otherwise()
                .log("Not XML or JSON File")
                .end()
                .to("file:files/output");
    }
}
