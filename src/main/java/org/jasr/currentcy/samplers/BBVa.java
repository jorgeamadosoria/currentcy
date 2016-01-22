package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("BBVa")
public class BBVa extends SimpleJSoupSampler {

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("tbody tr").eq(1).select("td").eq(1).text().trim());
            sellValue = Double.parseDouble(doc.select("tbody tr").eq(1).select("td:eq(2)").text().trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("tbody tr:eq(0)").select("td").eq(1).text().trim());
            sellValue = Double.parseDouble(doc.select("tbody tr:eq(0)").select("td:eq(2)").text().trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "https://www.bbva.com.uy/Inicio/Personas/";
    }

    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "https://bbvanet.bbva.com.uy/WebInst/Cotizaciones";
    }
    
    @Override
    public String getName() {
        return "BBVa";
    }

}
