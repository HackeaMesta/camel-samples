package com.chakray.EIPatterns.tools;

import com.chakray.EIPatterns.routers.DynamicRoutingRouter;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DinamycRouterBean {

    Logger log = LoggerFactory.getLogger(DinamycRouterBean.class);

    int invocations;

    public String decideNextEndpoint(
            @ExchangeProperties Map<String, String> exchangeProperties,
            @Headers Map<String, String> headers,
            @Body String body
    ) {
        log.info("Exchange Properties: {}", exchangeProperties);
        log.info("Headers: {}", headers);
        log.info("Body: {}", body);

        invocations++;

        if (invocations%3 == 0) {
            return "{{dynamic-endpoint-1}}";
        } else if (invocations%3 == 1){
            return "direct:dynamic-endpoint2";
        }

        return null;
    }
}
