package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

//@Component("ASPN")
public class CambioAspen extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("table tbody tr.bd").get(0).select("td.valor").get(0).text());
            sellValue = Double
                    .parseDouble(doc.select("table tbody tr.bd").get(0).select("td.valor").get(1).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("table tbody tr.bd").get(1).select("td.valor").get(0).text());
            sellValue = Double
                    .parseDouble(doc.select("table tbody tr.bd").get(1).select("td.valor").get(1).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "ASPN";
    }

    @Override
    public String getUrl() {
        return "http://www.aspen.com.uy/sitio/";
    }

    @Override
    public String getName() {
        return "Cambio Aspen";
    }
}
