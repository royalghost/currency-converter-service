/**
 * 
 */
package com.currencyconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

/**
 * Currency Converter Service.
 * 
 * @author prabinpaudel
 *
 */
@EnableEurekaServer
@SpringBootApplication
@RestController
public class CurrencyConverterApp {

	private static final Logger log = LoggerFactory.getLogger(CurrencyConverterApp.class);
	
	@Value("${currency_converter_url}")
	private String CURRENCY_CONVERTER_URL;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApp.class, args);
	}
	
	@RequestMapping(value="convert/{currencyFrom}/{currencyTo}", produces=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public CurrencyConversion convertCurrency(@PathVariable("currencyFrom") String currencyFrom, 
									@PathVariable("currencyTo") String currencyTo) {
		log.info("Reading conversion from url " + this.CURRENCY_CONVERTER_URL);
		CurrencyConversion currencyConversion = new RestTemplateBuilder().build().getForObject(CURRENCY_CONVERTER_URL.replace("CURRENCY_FROM", currencyFrom).replace("CURRENCY_TO", currencyTo), CurrencyConversion.class);
		return currencyConversion;
	}
}
