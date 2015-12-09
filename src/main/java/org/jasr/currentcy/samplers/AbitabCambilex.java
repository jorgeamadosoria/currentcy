package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Abit")
public class AbitabCambilex extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
	    ;
	    
		double buyValue = Double.parseDouble(doc.select("table#tabla_cambilex table tr").get(1).select("td").get(1).select("font>b").text());
		double sellValue = Double.parseDouble(doc.select("table#tabla_cambilex table tr").get(1).select("td").get(2).select("font>b").text());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "Abit";
	}
	@Override
	public String getUrl() {
		return "http://www.cambilex.com.uy/abitabinter/macros/cotizacion/innovanet/VerCotizaciones.jsp";	
	}
	@Override
	public String getName() {
		return "Abitab(Cambilex)";
	}
}
