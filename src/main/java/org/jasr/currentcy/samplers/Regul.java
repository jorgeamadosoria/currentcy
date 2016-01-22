package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Regul")
public class Regul extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.cambioregul.com";
    }
    
    @Override
    public String getName() {
        return "Cambio Regul SA";
    }
}
