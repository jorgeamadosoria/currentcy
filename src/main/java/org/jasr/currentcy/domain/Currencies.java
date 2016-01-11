package org.jasr.currentcy.domain;

import java.util.Currency;
import java.util.Locale;

import com.wordnik.swagger.annotations.ApiModel;
/**
 * Supported currencies for the system
 */
@ApiModel(value="Currency",description="Supported currencies for the system")
public enum Currencies {
	USD(0, Locale.US), EUR(1, Locale.GERMANY);

	public final int order;
	public final String code;
	public String symbol;
	public Locale locale;

	Currencies(int order, Locale locale) {
		this.order = order;
		this.locale = locale;
		this.code = Currency.getInstance(locale).getCurrencyCode();
		this.symbol = Currency.getInstance(locale).getSymbol(locale);
	}

	private boolean isCode(String currencyCode) {
		return this.code.equalsIgnoreCase(currencyCode);
	}

	/**
	 * Gets an enum Currencies object from the corresponding currencyCode
	 * @param currencyCode a 3-letter standard currency code 
	 * @return a Currencies instance corresponding to the code, or null if the code does not correspond to anything
	 */
	public static Currencies getCurrency(String currencyCode) {
		for (Currencies currency : Currencies.values())
			if (currency.isCode(currencyCode))
				return currency;
		return null;
	}
}
