/**
 * 
 */
package com.currencyconverter.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A POJO for currency conversion.
 * 
 * @author prabinpaudel
 *
 */
public class CurrencyConversion {

	private String source;
	private Integer timestamp;
	private Map<String, BigDecimal> quotes;
	
	public CurrencyConversion() {
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, BigDecimal> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, BigDecimal> quotes) {
		this.quotes = quotes;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n--------Conversions are as follows-----------");
		stringBuilder.append("\nSource is ").append(this.source).append(" and conversion rates are following: \n");
		for(Entry<String, BigDecimal> quote : quotes.entrySet()) {
			stringBuilder.append(quote.getKey()).append(" : ").append(quote.getValue()).append(" \n");
		}
		stringBuilder.append("TimeStamp: ").append(this.timestamp);
		return stringBuilder.toString();
	}

	
	

}
