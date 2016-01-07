package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("BROU")
public class BROU extends SamplerBase {

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("#exchangeRatesLarge tr.odd").eq(0).select(".buy").text().trim());
            sellValue = Double.parseDouble(doc.select("#exchangeRatesLarge tr.odd").eq(0).select(".sale").text().trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("#exchangeRatesLarge tr.odd").eq(1).select(".buy").text().trim());
            sellValue = Double.parseDouble(doc.select("#exchangeRatesLarge tr.odd").eq(1).select(".sale").text().trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "BROU";
    }

    @Override
    public String getUrl() {
        return "http://www.brou.com.uy";
    }
    
    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://www.brou.com.uy/c/portal/render_portlet?p_l_id=123137&p_p_id=ExchangeLarge_WAR_ExchangeRate5121_INSTANCE_P2Af&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_pos=0&p_p_col_count=1&currentURL=%2Fweb%2Fguest%2Finstitucional%2Fcotizaciones";
    }

    @Override
    public String getName() {
        return "Banco Rep&uacute;blica";
    }

}
