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
			buyValue = Double.parseDouble( doc.select("table:eq(1) img[src=dolares.jpg]").parents().get(1).select("td:eq(2)").text());
			sellValue = Double.parseDouble(doc.select("table:eq(1) img[src=dolares.jpg]").parents().get(1).select("td:eq(3)").text());
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble( doc.select("table:eq(1) img[src=euros.jpg]").parents().get(1).select("td:eq(2)").text());
			sellValue = Double.parseDouble(doc.select("table:eq(1) img[src=euros.jpg]").parents().get(1).select("td:eq(3)").text());
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}


	public Baluma() {
		super("http://www.balumacambio.conrad.com.uy/","Baluma Cambio SA","http://www.balumacambio.conrad.com.uy/cotizacion.php");
	}

}
