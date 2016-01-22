package org.jasr.currentcy.samplers;

import org.jasr.currentcy.domain.Currencies;
import org.springframework.stereotype.Component;

@Component("Abit")
public class AbitabCambilex extends SimpleJSoupSampler {

    public AbitabCambilex(){
        super("http://www.cambilex.com.uy","Abitab(Cambilex)","table#tabla_cambilex table tr:eq(1) td:eq(1) font>b",
                "table#tabla_cambilex table tr:eq(1) td:eq(2) font>b","table#tabla_cambilex table tr:eq(2) td:eq(1) font>b","table#tabla_cambilex table tr:eq(2) td:eq(2) font>b");
        
    }
    
    @Override
	public String getUrlByCurrency(Currencies currency) {
        return "http://www.cambilex.com.uy/abitabinter/macros/cotizacion/innovanet/VerCotizaciones.jsp";
	}

}
