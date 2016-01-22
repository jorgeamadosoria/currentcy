package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Scotia")
public class Scotiabank extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        String json = doc.select("script").get(doc.select("script").size() - 2).toString();
        String buy = ",\"Compra\":";
        String sell = ",\"Venta\":";
        if (currency.equals(Currencies.USD)) {
            String currencyString = "DOLARES USA BILLETE\"";
            String usdValues = json.substring(json.indexOf(currencyString) + currencyString.length(),
                    json.indexOf(",\"Moneda\":2222}"));
            sellValue = Double.parseDouble(usdValues.substring(sell.length(), usdValues.indexOf(buy)));
            buyValue = Double.parseDouble(usdValues.substring(usdValues.indexOf(buy) + buy.length()));
        }
        if (currency.equals(Currencies.EUR)) {
            String currencyString = "EUROS BILLETE\"";
            String usdValues = json.substring(json.indexOf(currencyString) + currencyString.length(),
                    json.indexOf(",\"Moneda\":1112}"));
            sellValue = Double.parseDouble(usdValues.substring(sell.length(), usdValues.indexOf(buy)));
            buyValue = Double.parseDouble(usdValues.substring(usdValues.indexOf(buy) + buy.length()));
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.scotiabank.com.uy/";
    }

    @Override
    public String getName() {
        return "Scotiabank";
    }
}
