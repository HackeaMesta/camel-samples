package com.chakray.EIPatterns.routers;

import com.chakray.EIPatterns.tools.SplitterComponent;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitPatterRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(SplitPatterRouter.class);

    @Autowired
    SplitterComponent splitterComponent;

    @Override
    public void configure() throws Exception {
        log.info("Split CSV file and sent each line to ActiveMQ");

        /*
        // Sample deliimiter by new-line \n
        from("file:files/csv")
                .unmarshal().csv()
                .split(body())
                .to("activemq:csv-files");
         */

        /*
        // Spli CSV file by comma (,) character
        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(body(), ",")
                .to("activemq:csv-files");
         */
        /*
        // Use custom splitter
         */
        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(method(splitterComponent))
                .to("activemq:csv-files");
    }
}
