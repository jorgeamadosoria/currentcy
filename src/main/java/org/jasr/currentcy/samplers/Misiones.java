package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Miss")
public class Misiones extends SimpleJSoupSampler {

    public Misiones(){
        super("http://www.cambiomisiones.com.uy/","Cambio Misiones");
    }
    
	@Override
	public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
		double buyValue = 0;
		double sellValue = 0;
		if (currency.equals(Currencies.USD)) {
			buyValue = Double.parseDouble(doc.select("table.hovertable tr:eq(1) td:eq(1)").text());
			sellValue = Double.parseDouble(doc.select("table.hovertable tr:eq(1) td:eq(2)").text());
		}
		if (currency.equals(Currencies.EUR)) {
			buyValue = Double.parseDouble(doc.select("table.hovertable tr:eq(4) td:eq(1)").text());
			sellValue = Double.parseDouble(doc.select("table.hovertable tr:eq(4) td:eq(2)").text());
		}
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}

}
