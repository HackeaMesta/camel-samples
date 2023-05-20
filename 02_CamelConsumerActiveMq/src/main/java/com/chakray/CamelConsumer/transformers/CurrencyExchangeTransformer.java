package com.chakray.CamelConsumer.transformers;

import com.chakray.CamelConsumer.dao.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyExchangeTransformer {
    Logger log = LoggerFactory.getLogger(CurrencyExchangeTransformer.class);

    public void processMessage(CurrencyExchange currencyExchange) {
        log.info("Do some transformations with currencyExchange");

        currencyExchange.setConversionMultiple(
                currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN)
        );
    }
}
