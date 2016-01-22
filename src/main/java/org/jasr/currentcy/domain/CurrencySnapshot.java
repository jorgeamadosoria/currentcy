package org.jasr.currentcy.domain;

import java.text.NumberFormat;
import java.util.Locale;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * Entity to show on the exchange tables, containing the code for each exchange and the buy/sell values for all exchanges and
 * currencies.
 *
 */
@ApiModel(value = "CurrencySnapshot", description = "Entity to show on the exchange tables, containing the code for each exchange and the buy/sell values for all exchanges and currencies.")
public class CurrencySnapshot extends BaseSample {

    protected String  code;
    protected double  sellValueUSD;
    protected double  buyValueUSD;
    protected double  sellValueEUR;
    protected double  buyValueEUR;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public double getSellValueUSD() {
        return sellValueUSD;
    }
    public void setSellValueUSD(double sellValueUSD) {
        this.sellValueUSD = sellValueUSD;
    }
    public double getBuyValueUSD() {
        return buyValueUSD;
    }
    public void setBuyValueUSD(double buyValueUSD) {
        this.buyValueUSD = buyValueUSD;
    }
    public double getSellValueEUR() {
        return sellValueEUR;
    }
    public void setSellValueEUR(double sellValueEUR) {
        this.sellValueEUR = sellValueEUR;
    }
    public double getBuyValueEUR() {
        return buyValueEUR;
    }
    public void setBuyValueEUR(double buyValueEUR) {
        this.buyValueEUR = buyValueEUR;
    }
}
