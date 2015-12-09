package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("GOOG")
public class GoogleFinance extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
		double buyValue = Double.parseDouble(doc.select("div#currency_value span.bld").eq(0).text().replace(" UYU", "").trim());
		double sellValue = Double.parseDouble(doc.select("div#currency_value span.bld").eq(0).text().replace(" UYU", "").trim());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "GOOG";
	}
	@Override
	public String getUrl() {
		return "https://www.google.com/finance?q=USDUYU";	
	}
	@Override
	public String getName() {
		return "Google Finance";
	}
}
