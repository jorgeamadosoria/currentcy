package org.jasr.currentcy.domain;

import java.text.NumberFormat;
import java.util.Locale;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * Core entity for the app. It represents a subset of the data in Sample for usage on mobiles.
 * {@link org.jasr.currentcy.service.impl.Sample}
 */
@ApiModel(value = "BaseSample", description = "Core entity for the app. It represents a subset of the data in Sample for usage on mobiles")
public class BaseSample {
    protected String  date;
    protected double  sellValue;
    protected double  buyValue;
    protected double  buyDiff;
    protected double  sellDiff;
    protected String  code;
    protected String  url;
    protected String  name;
    protected boolean bestBuy  = true;
    protected boolean bestSell = true;

    public boolean isBestBuy() {
        return bestBuy;
    }

    public void setBestBuy(boolean bestBuy) {
        this.bestBuy = bestBuy;
    }

    public boolean isBestSell() {
        return bestSell;
    }

    public void setBestSell(boolean bestSell) {
        this.bestSell = bestSell;
    }

    /**
     * Must be Mexico in order to use the peso symbol. Uruguay (UY) uses a weird NU$ symbol.
     */
    private Locale locale = new Locale("es", "MX");

    /**
     * The trend symbol is interpreted on the client side. The symbol is used to maintain decoupling between client and server.
     * 
     * @return a symbol to be interpreted on Javascript
     */
    public String getTrend() {

        if (Math.signum(buyDiff) > 0 && Math.signum(sellDiff) > 0)
            return "<";
        if (Math.signum(buyDiff) == 0 && Math.signum(sellDiff) == 0)
            return "-";
        if (Math.signum(buyDiff) < 0 && Math.signum(sellDiff) < 0)
            return ">";
        return "x";
    }

    public double getAvgValue() {
        return (buyValue + sellValue) / 2d;
    }


    /**
     * Formatted to leave out the seconds for the UI. It's easier to format on the server side than on the client.
     * 
     * @return a date formatted without seconds and a human readable form.
     */
    public String getDate() {
        return date.substring(0, date.length() - 5);
    }

    public String getTrendDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSellValue() {
        return sellValue;
    }

    public void setSellValue(double sellValue) {
        this.sellValue = sellValue;
    }

    public double getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(double buyValue) {
        this.buyValue = buyValue;
    }

    public double getBuyDiff() {
        return buyDiff;
    }

    public void setBuyDiff(double buyDiff) {
        this.buyDiff = buyDiff;
    }

    public double getSellDiff() {
        return sellDiff;
    }

    public void setSellDiff(double sellDiff) {
        this.sellDiff = sellDiff;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * For simmetry, as we have already overwritten the equals method. Default implementation by Eclipse
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(buyValue);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        temp = Double.doubleToLongBits(sellValue);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Comparison includes the samplers code, buyValue and sellValue.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseSample other = (BaseSample) obj;
        if (Double.doubleToLongBits(buyValue) != Double.doubleToLongBits(other.buyValue))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        }
        else if (!code.equals(other.code))
            return false;
        if (Double.doubleToLongBits(sellValue) != Double.doubleToLongBits(other.sellValue))
            return false;
        return true;
    }

}
