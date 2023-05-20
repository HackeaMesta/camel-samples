package com.chakray.EIPatterns.routers;

import com.chakray.EIPatterns.dao.CurrencyExchange;
import com.chakray.EIPatterns.tools.ArrayListAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AggregateRouter extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(AggregateRouter.class);

    @Override
    public void configure() throws Exception {
        log.info("Agregate to Json");

        from("file:files/aggregate")
                .unmarshal()
                .json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                .to("log:aggregate-json");
    }
}
