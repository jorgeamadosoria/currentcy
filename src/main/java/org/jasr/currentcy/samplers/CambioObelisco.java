package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Obel")
public class CambioObelisco extends SimpleJSoupSampler {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("table.moduletable table tr").get(3).select("td").get(2).text());
            sellValue = Double
                    .parseDouble(doc.select("table.moduletable table tr").get(3).select("td").get(3).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("table.moduletable table tr").get(7).select("td").get(2).text());
            sellValue = Double
                    .parseDouble(doc.select("table.moduletable table tr").get(7).select("td").get(3).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.cambioobelisco.com.uy/";
    }

	@Override
    public String getName() {
        return "Cambio Obelisco";
    }
}
