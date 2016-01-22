package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Pampex")
public class CambioPampex extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.cambiopampex.com/portal/hgxpp001.aspx";
    }

	@Override
    public String getName() {
        return "Cambio Pampex";
    }
}
