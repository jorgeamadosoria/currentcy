package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Fortex")
public class Fortex extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("div.boxContenido div#cotizacion ul").select("li").get(0).select("p.monedaCompra").text());
            sellValue = Double
                    .parseDouble(doc.select("div.boxContenido div#cotizacion ul").select("li").get(0).select("p.monedaVenta").text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("div.boxContenido div#cotizacion ul").select("li").get(3).select("p.monedaCompra").text());
            sellValue = Double
                    .parseDouble(doc.select("div.boxContenido div#cotizacion ul").select("li").get(3).select("p.monedaVenta").text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "Fortex";
    }

    @Override
    public String getUrl() {
        return "http://www.fortex.com.uy/sitio/index.php?lang=es";
    }

    @Override
    public String getName() {
        return "Fortex S.F.";
    }
}
