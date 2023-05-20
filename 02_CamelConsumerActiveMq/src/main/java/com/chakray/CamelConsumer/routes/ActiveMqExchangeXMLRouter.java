package com.chakray.CamelConsumer.routes;

import com.chakray.CamelConsumer.dao.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqExchangeXMLRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:activemq-xml-exchanges")
                // Transform XML object to currencyExchange Instance object
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .to("log:received-exchange-from-active-mq");
    }
}
