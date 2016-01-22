package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Galim")
public class Galimir extends Inpulsedm {

 
    @Override
    public String getUrl() {
        return "http://www.cambiogalimirsa.com/portal/hgxpp001.aspx?171";
    }

    @Override
    public String getName() {
        return "Cambio Galimir";
    }
}
