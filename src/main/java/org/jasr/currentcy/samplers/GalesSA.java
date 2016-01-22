package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Gales")
public class GalesSA extends SimpleJSoupSampler {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select(".cont_cotizaciones tbody tr").get(0).select("td").get(1).text().replace(",","."));
            sellValue = Double
                    .parseDouble(doc.select(".cont_cotizaciones tbody tr").get(0).select("td").get(2).text().replace(",","."));
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select(".cont_cotizaciones tbody tr").get(3).select("td").get(1).text().replace(",","."));
            sellValue = Double
                    .parseDouble(doc.select(".cont_cotizaciones tbody tr").get(3).select("td").get(2).text().replace(",","."));
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.gales.com.uy/home/";
    }

    @Override
    public String getName() {
        return "Gales SA";
    }
}
