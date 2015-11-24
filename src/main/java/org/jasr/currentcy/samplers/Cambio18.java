package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("C18")
public class Cambio18 extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample) throws IOException{
	    ;
	    
		double buyValue = Double.parseDouble(doc.select("[src=images/dolares.jpg]").get(0).parent().parent().select("td").get(2).select("font").text());
		double sellValue = Double.parseDouble(doc.select("[src=images/dolares.jpg]").get(0).parent().parent().select("td").get(3).select("font").text());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "C18";
	}
	@Override
	public String getUrl() {
		return "http://www.cambio18.com/";	
	}
	@Override
	public String getName() {
		return "Cambio 18";
	}
}
