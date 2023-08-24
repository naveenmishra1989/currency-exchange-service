package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.bean.ExchangeValue;
import com.example.currencyexchangeservice.repo.ExchangeValueRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Log4j2
public class CurrencyExchangeController {

    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    private Environment environment;

    /**
     * http://localhost:8002/currency-exchange/from/USD/to/INR
     * http://localhost:8002/currency-exchange/from/EUR/to/INR
     * http://localhost:8002/currency-exchange/from/AUD/to/INR
     *
     * @param from
     * @param to
     * @return ExchangeValue
     */
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue =  repository.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
