package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Varlix")
public class Varlix extends SimpleJSoupSampler {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("aside.barRightContent div.cotizacion tr").get(0).select("td").get(4).select("strong").text());
            sellValue = Double
                    .parseDouble(doc.select("aside.barRightContent div.cotizacion tr").get(0).select("td").get(5).select("strong").text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("aside.barRightContent div.cotizacion tr").get(3).select("td").get(4).select("strong").text());
            sellValue = Double
                    .parseDouble(doc.select("aside.barRightContent div.cotizacion tr").get(3).select("td").get(5).select("strong").text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.varlix.com.uy/";
    }

    @Override
    public String getName() {
        return "Varlix S.F.";
    }
}
