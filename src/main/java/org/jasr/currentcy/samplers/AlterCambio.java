package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("AltC")
public class AlterCambio extends SamplerBase {

	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble(doc.select("table.cuadrosLeft tr").eq(0).select("td").eq(2).text().replace(",",".").trim());
			sellValue = Double.parseDouble(doc.select("table.cuadrosLeft tr").eq(0).select("td").eq(3).text().replace(",",".").trim());
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble(doc.select("table.cuadrosLeft tr").eq(3).select("td").eq(2).text().replace(",",".").trim());
			sellValue = Double.parseDouble(doc.select("table.cuadrosLeft tr").eq(3).select("td").eq(3).text().replace(",",".").trim());
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}

	public String getCode() {
		return "AltC";
	}

	@Override
	public String getUrl() {
		return "http://www.altercambio.com.uy/reclamos/altercambio/Content/Quote.aspx";
	}

	@Override
	public String getName() {
		return "Altercambio SA";
	}

}
