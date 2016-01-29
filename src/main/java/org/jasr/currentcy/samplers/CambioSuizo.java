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
                    .parseDouble(doc.select("td.style41 > table:eq(3) table > tbody > tr:eq(1) table:eq(2) table").get(0).text());
            sellValue = Double
                    .parseDouble(doc.select("td.style41 > table:eq(3) table > tbody > tr:eq(1) table:eq(2) table").get(1).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("td.style41 > table:eq(3) table > tbody > tr:eq(1) table:eq(2) table").get(3).text());
            sellValue = Double
                    .parseDouble(doc.select("td.style41 > table:eq(3) table > tbody > tr:eq(1) table:eq(2) table").get(4).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public CambioSuizo() {
        super("http://www.cambiosuizo.com.uy/","Cambio Suizo");
    }
}
