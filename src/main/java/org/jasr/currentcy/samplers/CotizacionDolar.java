package org.jasr.currentcy.samplers;

import org.springframework.stereotype.Component;

@Component("CotD")
public class CotizacionDolar extends SimpleJSoupSampler {

    public CotizacionDolar() {
        super("http://uy.cotizacion-dolar.com/cotizacion_hoy_uruguay.php","Cotizaci&oacute;n D&oacute;lar Uruguay",
                ".cotizacion-contenido .cc-2b .cotizacion-num:eq(0)",".cotizacion-contenido .cc-3b .cotizacion-num:eq(0)",
                ".cotizacion-contenido .cc-2b .cotizacion-num:eq(1)",".cotizacion-contenido .cc-3b .cotizacion-num:eq(1)");
    }
}
