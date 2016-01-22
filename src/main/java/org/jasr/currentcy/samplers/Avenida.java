package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("AVE")
public class Avenida extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("div#bannerder table tr").get(1).select("td").get(1).select("div").text());
            sellValue = Double
                    .parseDouble(doc.select("div#bannerder table tr").get(1).select("td").get(2).select("div").text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("div#bannerder table tr").get(4).select("td").get(1).select("div").text());
            sellValue = Double
                    .parseDouble(doc.select("div#bannerder table tr").get(4).select("td").get(2).select("div").text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }


    @Override
    public String getUrl() {
        return "http://www.avenida.com.uy";
    }
    
    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://www.avenida.com.uy/avenida-cotizaciones-diarias.php";
    }
    
    @Override
    public String getName() {
        return "Avenida S.F.";
    }
}
