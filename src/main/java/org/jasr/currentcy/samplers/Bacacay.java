package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Bccy")
public class Bacacay extends SamplerBase {

    public Bacacay() {
        super("http://www.cambiobacacay.com","Bacacay S.F.","http://www.cambiobacacay.com/cinta/cinta.php");
    }
    
	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble(doc.select("div#divTopOverflow tr:eq(1) td.cintaValor:eq(2)").text().split("  ")[0]);
			sellValue = Double.parseDouble(doc.select("div#divTopOverflow tr:eq(1) td.cintaValor:eq(2)").text().split("  ")[1]);
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble(doc.select("div#divTopOverflow tr:eq(1) td.cintaValor:eq(7)").text().split("  ")[0]);
			sellValue = Double.parseDouble(doc.select("div#divTopOverflow tr:eq(1) td.cintaValor:eq(7)").text().split("  ")[1]);
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}

	
}
