package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("CSir")
public class CambioSir extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
		double buyValue = Double.parseDouble(doc.select("img[alt=Dolares]").get(0).parent().parent().select("td").eq(2).text().replace(",",".").trim());
		double sellValue = Double.parseDouble(doc.select("img[alt=Dolares]").get(0).parent().parent().select("td").eq(3).text().replace(",",".").trim());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "CSir";
	}
	@Override
	public String getUrl() {
		return "http://www.cambiosir.com.uy/Cotizaciones";	
	}
	@Override
	public String getName() {
		return "Cambio SIR";
	}
}
