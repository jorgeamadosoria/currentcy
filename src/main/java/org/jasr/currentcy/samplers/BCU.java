package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("BCU")
public class BCU extends SimpleJSoupSampler {

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select(".Cotizaciones tr").eq(1).select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double
                    .parseDouble(doc.select(".Cotizaciones tr").eq(1).select("td:eq(2)").text().replace(",", ".").trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select(".Cotizaciones tr:eq(3)").select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double
                    .parseDouble(doc.select(".Cotizaciones tr:eq(3)").select("td:eq(2)").text().replace(",", ".").trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    @Override
    public String getUrl() {
        return "http://www.bcu.gub.uy";
    }
    
    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://www.bcu.gub.uy/Estadisticas-e-Indicadores/Paginas/Cotizaciones.aspx";
    }

    @Override
    public String getName() {
        return "Banco Central del Uruguay";
    }

}
