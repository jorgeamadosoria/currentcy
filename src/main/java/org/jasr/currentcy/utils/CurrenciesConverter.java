package org.jasr.currentcy.utils;

import org.jasr.currentcy.domain.Currencies;
import org.springframework.core.convert.converter.Converter;

public final class CurrenciesConverter implements Converter<String, Currencies> {
	 
    @Override
    public Currencies convert(String source) {
        if (source == null || source.isEmpty()) {
            return Currencies.USD;
        }
 
        return Currencies.getCurrency(source);
    }
}
