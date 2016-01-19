package org.jasr.currentcy.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.samplers.SamplerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

/**
 * Standard rowmapper for Spring JDBC to return BaseSample instances.
 * 
 * @author jorge.amado
 *
 */
@Component
public class BaseSamplerRowMapper extends BeanPropertyRowMapper<BaseSample> {

    @Autowired
    private Map<String, SamplerBase> samplers;

    public BaseSamplerRowMapper() {
        super(BaseSample.class);
    }

    @Override
    public BaseSample mapRow(ResultSet arg0, int arg1) throws SQLException {
        BaseSample sample = super.mapRow(arg0, arg1);
        sample.setBestBuy(arg0.getBoolean("best_buy"));
        sample.setBestSell(arg0.getBoolean("best_sell"));
        if (samplers.get(sample.getCode()) == null)
        	return null;
        sample.setUrl(samplers.get(sample.getCode()).getUrl());
        sample.setName(samplers.get(sample.getCode()).getName());
        return sample;
    }

}
