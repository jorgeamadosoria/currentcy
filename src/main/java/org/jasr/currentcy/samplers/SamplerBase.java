package org.jasr.currentcy.samplers;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class SamplerBase {
	private int id;
   
	public abstract String getUrl();
	
    public String getUrlByCurrency(Currencies currency) {
       return getUrl();
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public abstract String getCode();
	public abstract String getName();
	
    public Sample sample(Currencies currency){
    	Sample sample = null;
    	try{
    		Document doc = Jsoup.connect(getUrlByCurrency(currency)).get();
    		sample = new Sample();
    		sample.setCode(getCode());
    		sample = doSample(doc,sample,currency);
    	}
        catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	return sample;
    }
    
    public abstract Sample doSample(Document doc,Sample sample,Currencies currency) throws IOException;
}
