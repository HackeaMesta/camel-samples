package com.chakray.ComplexDeciders.deciders;

import com.chakray.ComplexDeciders.routes.ReusableEndpointRouter;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeciderBean {
    Logger log = LoggerFactory.getLogger(DeciderBean.class);

    public boolean isThisConditionMet(
            @Body String body,
            @Headers Map<String, String> headers,
            @ExchangeProperties Map<String, String> exchangeProperties
            ) {

        log.info("DeciderBean body: {}", body);
        log.info("DeciderBean Headers: {}", headers);
        log.info("DeciderBean Exchange Properties: {}", exchangeProperties);

        return true;
    }
}
