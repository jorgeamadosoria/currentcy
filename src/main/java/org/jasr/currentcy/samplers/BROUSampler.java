package org.jasr.currentcy.samplers;

/**
 * Base class for JSOUP samplers that can express each value request in a single JSoup selector. It is expected that most samplers
 * can inherit from this one, as JSoup selectors are quite powerful
 * 
 */
public class BROUSampler extends SimpleJSoupSampler {

    public BROUSampler(String url, String name) {
        super(url, name,"#exchangeRatesLarge tr:eq(1) .buy"
                ,"#exchangeRatesLarge tr:eq(1) .sale"
                ,"#exchangeRatesLarge tr:eq(2) .buy"
               ,"#exchangeRatesLarge tr:eq(2) .sale", 
                "http://www.brou.com.uy/c/portal/render_portlet?p_l_id=123137&p_p_id=ExchangeLarge_WAR_ExchangeRate5121_INSTANCE_P2Af&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_pos=0&p_p_col_count=1&currentURL=%2Fweb%2Fguest%2Finstitucional%2Fcotizaciones");
    }

}
