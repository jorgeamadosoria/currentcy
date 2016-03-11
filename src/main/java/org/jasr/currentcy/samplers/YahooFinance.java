package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

//@Component("YHOO")
public class YahooFinance extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            String prefix = "\"currency\",\"price\":{\"fmt\":\"";
            int idx = doc.toString().indexOf(prefix) + prefix.length();
            String val = doc.toString().substring(idx, idx + 7);
            buyValue = Double.parseDouble(val);
            sellValue = Double.parseDouble(val);
        }
        if (currency.equals(Currencies.EUR)) {
            String prefix = "\"currency\",\"price\":{\"fmt\":\"";
            int idx = doc.toString().indexOf(prefix) + prefix.length();
            String val = doc.toString().substring(idx, idx + 7);
            buyValue = Double.parseDouble(val);
            sellValue = Double.parseDouble(val);
        }

        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public YahooFinance() {
        super("http://finance.yahoo.com","Yahoo! Finance");
    }

    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://finance.yahoo.com/echarts?s=" + currency.code.toUpperCase()
                + "UYU=X&t=5d&l=on&z=m&q=l&c=#{%22allowChartStacking%22:true}";
    }

}
