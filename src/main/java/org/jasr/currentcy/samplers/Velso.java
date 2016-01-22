package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("Velso")
public class Velso extends Inpulsedm {

    public Velso() {
        super("http://www.cambiovelso.com/portal/hgxpp001.aspx","Cambio Velso");
    }
}
