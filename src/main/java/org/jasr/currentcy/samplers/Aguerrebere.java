package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Aguer")
public class Aguerrebere extends Inpulsedm {


    @Override
    public String getUrl() {
        return "http://www.cambioaguerrebere.com/portal/hgxpp001.aspx";
    }

    @Override
    public String getName() {
        return "Cambio Aguerrebere SA";
    }
}
