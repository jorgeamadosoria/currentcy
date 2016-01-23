package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("C18")
public class Cambio18 extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
	    double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("[src=images/dolares.jpg]").get(0).parent().parent().select("td:eq(2) font").text());
            sellValue = Double.parseDouble(doc.select("[src=images/dolares.jpg]").get(0).parent().parent().select("td:eq(3) font").text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("[src=images/euros.jpg]").get(0).parent().parent().select("td:eq(2) font").text());
            sellValue = Double.parseDouble(doc.select("[src=images/euros.jpg]").get(0).parent().parent().select("td:eq(3) font").text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
	    
	}

	public Cambio18() {
		super("http://www.cambio18.com/","Cambio 18");
	}
}
