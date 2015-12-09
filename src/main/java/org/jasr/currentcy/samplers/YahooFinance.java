package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("YHOO")
public class YahooFinance extends SamplerBase{
	
	public Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException{
		String prefix = "\"currency\",\"price\":{\"fmt\":\"";
		int idx = doc.toString().indexOf(prefix) + prefix.length();
		String val = doc.toString().substring(idx,idx+7);
		double buyValue = Double.parseDouble(val);
		double sellValue = Double.parseDouble(val);
		sample.setBuyValue(buyValue);
		sample.setSellValue(sellValue);
		return sample;
	}
	public String getCode(){
		return "YHOO";
	}
	@Override
	public String getUrl() {
		return "http://finance.yahoo.com/echarts?s=USDUYU=X&t=5d&l=on&z=m&q=l&c=#{%22allowChartStacking%22:true}";	
	}
	@Override
	public String getName() {
		return "Yahoo! Finance";
	}
}
