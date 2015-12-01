package org.jasr.currentcy.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Sample {
   private int id;
   private String date;
   private double sellValue;
   private double buyValue;
   private double buyDiff;
   private double sellDiff;
   private String code;
   private String url;
   private String name;
    
	/**
     * Must be Mexico in order to use the peso symbol. Uruguay (UY) uses a weird NU$ symbol.
     */
    private Locale locale = new Locale("es","MX");
    
    public String getTrend() {
    	
        if (Math.signum(buyDiff) > 0 && Math.signum(sellDiff) > 0 )
            return ">";
        if (Math.signum(buyDiff) == 0 && Math.signum(sellDiff) == 0 )
            return "-";
        if (Math.signum(buyDiff) < 0 && Math.signum(sellDiff) < 0 )
            return "<";
        return "x";
    }

    public double getAvgValue(){
        return (buyValue + sellValue)/2d;
    }
    
    public String getAvg(){
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format((buyValue + sellValue)/2d);
    }
    
    public String getBuy(){
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(buyValue);
    }
    
    public String getSell(){
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(sellValue);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
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
