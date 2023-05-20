package com.chakray.ComplexDeciders.routes;

import com.chakray.ComplexDeciders.deciders.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReusableEndpointRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(ReusableEndpointRouter.class);
    @Autowired
    DeciderBean deciderBean;

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
                //.when(simple("${body} contains 'USD'"))
                .when(method(deciderBean))
                .log("Not XML FIle, but contains USD")
                .otherwise()
                .log("Not XML File")
                .end()
                .to("direct://log-file-values")
                .to("file:files/output");

        // Reusable endpoint called log-file-values
        // https://camel.apache.org/components/3.18.x/index.html
        from("direct:log-file-values")
                .log("Message History: ${messageHistory}")
                .log("Absolute Path: ${file:absolute.path}")
                .log("File name: ${file:name}")
                .log("File name without extanesion: ${file:name.noext}")
                .log("File extension: ${file:name.ext}")
                .log("File Only Name: ${file:onlyname}")
                .log("File Absolute: ${file:absolute}")
                .log("File size: ${file:size}")
                .log("File modified: ${file:modified}")
                .log("Route Id: ${routeId}")
                .log("Camel Id: ${camelId}")
                .log("Body: ${body}");

    }
}
