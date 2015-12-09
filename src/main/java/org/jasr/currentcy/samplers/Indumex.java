package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Indx")
public class Indumex extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
		double buyValue = Double.parseDouble(doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Dolar)").get(0).parent().select("td").eq(2).text().replace(",",".").trim());
		double sellValue = Double.parseDouble(doc.select("table#dnn_ctr368_ViewCotizador_ViewCotizador_DataGridHistorico tr td:contains(Dolar)").get(0).parent().select("td").eq(3).text().replace(",",".").trim());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "Indx";
	}
	@Override
	public String getUrl() {
		return "http://www.indumex.com/indumex/";	
	}
	@Override
	public String getName() {
		return "Indumex";
	}
}
