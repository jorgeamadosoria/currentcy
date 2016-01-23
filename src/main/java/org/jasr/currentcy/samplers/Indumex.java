package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Indx")
public class Indumex extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(
                    doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Dolar)")
                    .get(0)
                            .parent().select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double.parseDouble(
                    doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Dolar)").get(0)
                            .parent().select("td:eq(3)").text().replace(",", ".").trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(
                    doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Euro)").get(0)
                            .parent().select("td:eq(2)").text().replace(",", ".").trim());
            sellValue = Double.parseDouble(
                    doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Euro)").get(0)
                            .parent().select("td:eq(3)").text().replace(",", ".").trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }


    public Indumex() {
        super("http://www.indumex.com/indumex/","Indumex");
    }
}
