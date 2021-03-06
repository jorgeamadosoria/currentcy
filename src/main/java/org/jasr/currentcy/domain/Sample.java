package org.jasr.currentcy.domain;

import java.text.NumberFormat;
import java.util.Locale;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * Core entity for the system. It represents the values of the buy/sell rates for one exchange, one currency and one moment of
 * time. Provides some formatting output methods in order to simplify JSON conversion of the entity.
 *
 */
@ApiModel(value = "Sample", description = "Core entity for the system. It represents the values of the buy/sell rates for one exchange, one currency and one moment of time")
public class Sample extends BaseSample{
    private int     id;

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

    public String getAvg() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format((buyValue + sellValue) / 2d);
    }

    public String getBuy() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(buyValue);
    }

    public String getSell() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(sellValue);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrendDate() {
        return date;
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
        Sample other = (Sample) obj;
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
