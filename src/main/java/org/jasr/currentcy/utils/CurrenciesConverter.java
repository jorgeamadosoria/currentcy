package org.jasr.currentcy.utils;

import org.jasr.currentcy.domain.Currencies;
import org.springframework.core.convert.converter.Converter;
/**
 * Currency converter for Spring MVC argument simplification in controllers
 *
 */
public final class CurrenciesConverter implements Converter<String, Currencies> {
	 
    @Override
    public Currencies convert(String source) {
        if (source == null || source.isEmpty()) {
            return Currencies.USD;
        }
 
        return Currencies.getCurrency(source);
    }
}
