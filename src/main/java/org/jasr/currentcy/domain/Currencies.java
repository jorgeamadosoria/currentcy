package org.jasr.currentcy.domain;

import java.util.Currency;
import java.util.Locale;

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
		return this.code.equals(currencyCode);
	}

	public static Currencies getCurrency(String currencyCode) {
		for (Currencies currency : Currencies.values())
			if (currency.isCode(currencyCode))
				return currency;
		return null;
	}
}
