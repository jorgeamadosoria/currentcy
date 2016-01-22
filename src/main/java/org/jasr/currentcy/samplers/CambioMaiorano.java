package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Maio")
public class CambioMaiorano extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.cambiomaiorano.com/portal/hgxpp001.aspx";
    }

    @Override
    public String getName() {
        return "Cambio Maiorano";
    }
}
