package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Brimar")
public class Brimar extends SamplerBase {

	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble(doc.select("div.tm-article-content.uk-clearfix").select("p").get(0).text().split(" | ")[0]);
			sellValue = Double.parseDouble(doc.select("div.tm-article-content.uk-clearfix").select("p").get(0).text().split(" | ")[2]);
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble(doc.select("div.tm-article-content.uk-clearfix").select("p").get(3).text().split(" | ")[0]);
			sellValue = Double.parseDouble(doc.select("div.tm-article-content.uk-clearfix").select("p").get(3).text().split(" | ")[2]);
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}


	@Override
	public String getUrl() {
		return "http://www.brimar.com.uy/";
	}
	
	@Override
	public String getName() {
		return "Brimar S. F.";
	}

}
