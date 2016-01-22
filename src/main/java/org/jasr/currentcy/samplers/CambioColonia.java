package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("CColon")
public class CambioColonia extends Inpulsedm {


    @Override
    public String getUrl() {
        return "http://www.cambiocolonia.com.uy/portal/hgxpp001.aspx";
    }

    @Override
    public String getName() {
        return "Cambio Colonia";
    }
}
