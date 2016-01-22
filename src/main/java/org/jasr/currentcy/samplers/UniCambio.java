package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Unic")
public class UniCambio extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.unicambio.com.uy/portal/hgxpp001.aspx";
    }

    @Override
    public String getName() {
        return "uniCambio";
    }
}
