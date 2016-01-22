package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Globus")
public class Globus extends Inpulsedm {

    @Override
    public String getUrl() {
        return "http://www.cambioglobus.com/portal/hgxpp001.aspx";
    }

    @Override
    public String getName() {
        return "Cambios Globus Exchange";
    }
}
