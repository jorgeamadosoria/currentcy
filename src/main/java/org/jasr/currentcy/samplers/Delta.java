package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

//@Component("Delta")
//Need XML parsing. Will revisit later
public class Delta extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            System.out.println(doc.toString());
            buyValue = Double
                    .parseDouble(doc.select("table#datos").get(0).select("td").get(1).text());
            sellValue = Double
                    .parseDouble(doc.select("table#datos2 tr").get(0).select("td").get(2).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("table#datos tr").get(3).select("td").get(1).text());
            sellValue = Double
                    .parseDouble(doc.select("table#datos2 tr").get(3).select("td").get(2).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "Delta";
    }

    @Override
    public String getUrl() {
        return "http://deltasf.com.uy/pizarra/files/Cotizaciones.xml";
    }

    @Override
    public String getName() {
        return "Delta S.F.";
    }
}
