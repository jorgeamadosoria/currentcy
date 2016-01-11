package org.jasr.currentcy.domain;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * Entity that represents the tendency of a exchange rate in time. It uses several samples to graph this development through time,
 * as well as maximum and minimum values for this trend (for UI graphing purposes).
 *
 */
@ApiModel(value="Trend",description="Entity that represents the tendency of a exchange rate in time")
public class Trend {

    private double       max;
    private double       min;
    private List<Sample> samples;

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

}
