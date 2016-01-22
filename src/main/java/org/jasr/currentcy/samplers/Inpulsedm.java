package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class Inpulsedm extends SimpleJSoupSampler {

    public Inpulsedm(String url, String name){
        super(url,name,"table table tr:eq(2) td:eq(2)","table table tr:eq(2) td:eq(3)","table table tr:eq(8) td:eq(2)","table table tr:eq(8) td:eq(3)");
    }
    
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        
            int timeout = timeout();
            if (timeout != -1)
                doc = Jsoup.connect(doc.select("iframe").attr("src")).timeout(timeout).get();
            else
                doc = Jsoup.connect(doc.select("iframe").attr("src")).get();
            return super.doSample(doc, sample, currency);
    }
    
}
