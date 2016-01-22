package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Vexel")
public class CambioVexel extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.cambiovexel.com/portal/hgxpp001.aspx";
    }

	@Override
    public String getName() {
        return "Cambio Vexel";
    }
}
