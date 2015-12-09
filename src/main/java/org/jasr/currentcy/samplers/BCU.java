package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("BCU")
public class BCU extends SamplerBase{

	@Override
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException {
		double buyValue = Double.parseDouble(doc.select(".Cotizaciones tr").eq(1).select("td").eq(2).text().replace(",", ".").trim());
		double sellValue = Double.parseDouble(doc.select(".Cotizaciones tr").eq(1).select("td").eq(2).text().replace(",", ".").trim());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "BCU";
	}
	@Override
	public String getUrl() {
		return "http://www.bcu.gub.uy/Estadisticas-e-Indicadores/Paginas/Cotizaciones.aspx";
	}
	@Override
	public String getName() {
		return "Banco Central del Uruguay";
	}

}
