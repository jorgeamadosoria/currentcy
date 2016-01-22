package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Baluma")
public class Baluma extends SamplerBase {

	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble( doc.select("table").get(1).select("img[src=dolares.jpg]").parents().get(1).select("td").get(2).text());
			sellValue = Double.parseDouble(doc.select("table").get(1).select("img[src=dolares.jpg]").parents().get(1).select("td").get(3).text());
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble( doc.select("table").get(1).select("img[src=euros.jpg]").parents().get(1).select("td").get(2).text());
			sellValue = Double.parseDouble(doc.select("table").get(1).select("img[src=euros.jpg]").parents().get(1).select("td").get(3).text());
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}


	@Override
	public String getUrl() {
		return "http://www.balumacambio.conrad.com.uy/";
	}
	
	@Override
    public String getUrlByCurrency(Currencies currency) {
	    return "http://www.balumacambio.conrad.com.uy/cotizacion.php";
    }
    
	
	@Override
	public String getName() {
		return "Baluma Cambio SA";
	}

}
