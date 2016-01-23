package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Vexel")
public class CambioVexel extends Inpulsedm {

    public CambioVexel() {
        super("http://www.cambiovexel.com/portal/hgxpp001.aspx","Cambio Vexel");
    }
}
