package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("CSir")
public class CambioSir extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(
                    doc.select("img[alt=Dolares]").get(0).parent().parent().select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double.parseDouble(
                    doc.select("img[alt=Dolares]").get(0).parent().parent().select("td:eq(3)").text().replace(",", ".").trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(
                    doc.select("img[alt=Euros]").get(0).parent().parent().select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double.parseDouble(
                    doc.select("img[alt=Euros]").get(0).parent().parent().select("td:eq(3)").text().replace(",", ".").trim());
        }

        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public CambioSir() {
        super("http://www.cambiosir.com.uy", "Cambio SIR","http://www.cambiosir.com.uy/Cotizaciones");
    }

}
