package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Bccy")
public class Bacacay extends SimpleJSoupSampler {

	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble(doc.select("div#divTopOverflow").select("tr").get(1).select("td.cintaValor").get(0).text().split("  ")[0]);
			sellValue = Double.parseDouble(doc.select("div#divTopOverflow").select("tr").get(1).select("td.cintaValor").get(0).text().split("  ")[1]);
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble(doc.select("div#divTopOverflow").select("tr").get(1).select("td.cintaValor").get(1).text().split("  ")[0]);
			sellValue = Double.parseDouble(doc.select("div#divTopOverflow").select("tr").get(1).select("td.cintaValor").get(1).text().split("  ")[1]);
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}

	@Override
	public String getUrl() {
		return "http://www.cambiobacacay.com";
	}
	
	@Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://www.cambiobacacay.com/cinta/cinta.php";
    }

	@Override
	public String getName() {
		return "Bacacay S.F.";
	}

}
