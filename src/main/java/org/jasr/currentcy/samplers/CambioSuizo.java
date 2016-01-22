package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("CSuizo")
public class CambioSuizo extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("img[src=imagenes/coti_pizzarra/usa.jpg]").parents().get(1).select("table td").get(0).text());
            sellValue = Double
                    .parseDouble(doc.select("img[src=imagenes/coti_pizzarra/usa.jpg]").parents().get(1).select("table td").get(1).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("img[src=imagenes/coti_pizzarra/euro.jpg]").parents().get(1).select("table td").get(0).text());
            sellValue = Double
                    .parseDouble(doc.select("img[src=imagenes/coti_pizzarra/euro.jpg]").parents().get(1).select("table td").get(1).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.cambiosuizo.com.uy/";
    }

    @Override
    public String getName() {
        return "Cambio Suizo";
    }
}
