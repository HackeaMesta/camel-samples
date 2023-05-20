package com.chakray.CamelConsumer.routes;

import com.chakray.CamelConsumer.dao.CurrencyExchange;
import com.chakray.CamelConsumer.processors.CurrencyExchangeProcessor;
import com.chakray.CamelConsumer.transformers.CurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqExchangeRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessor processor;

    @Autowired
    private CurrencyExchangeTransformer transformetion;

    @Override
    public void configure() throws Exception {
        from("activemq:activemq-exchanges")
                // Transform Json object to currencyExchange Instance object
                .unmarshal()
                .json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(processor)
                .bean(transformetion)
                .to("log:received-exchange-from-active-mq");
    }
}
