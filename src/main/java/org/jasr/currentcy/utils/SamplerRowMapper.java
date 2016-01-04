package org.jasr.currentcy.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.samplers.SamplerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

/**
 * Standard rowmapper for Spring JDBC to return Sample instances.
 * 
 * @author jorge.amado
 *
 */
@Component
public class SamplerRowMapper extends BeanPropertyRowMapper<Sample> {

    @Autowired
    private Map<String, SamplerBase> samplers;

    public SamplerRowMapper() {
        super(Sample.class);
    }

    @Override
    public Sample mapRow(ResultSet arg0, int arg1) throws SQLException {
        Sample sample = super.mapRow(arg0, arg1);
        sample.setBestBuy(arg0.getBoolean("best_buy"));
        sample.setBestSell(arg0.getBoolean("best_sell"));
        sample.setUrl(samplers.get(sample.getCode()).getUrl());
        sample.setName(samplers.get(sample.getCode()).getName());
        return sample;
    }

}
