package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("CotD")
public class CotizacionDolar extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
		double buyValue = Double.parseDouble(doc.select(".cotizacion-contenido .cc-2b .cotizacion-num").eq(0).text().trim());
		double sellValue = Double.parseDouble(doc.select(".cotizacion-contenido .cc-3b .cotizacion-num").eq(0).text().trim());
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "CotD";
	}
	@Override
	public String getUrl() {
		return "http://uy.cotizacion-dolar.com/cotizacion_hoy_uruguay.php";	
	}
	@Override
	public String getName() {
		return "Cotización D&oacute;lar Uruguay";
	}
}
