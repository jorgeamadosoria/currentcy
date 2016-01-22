package org.jasr.currentcy.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.CurrencySnapshot;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.utils.BaseSamplerRowMapper;
import org.jasr.currentcy.utils.SamplerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SampleDAOImpl implements SampleDAO {

    @Autowired
    private Environment      env;
    @Resource
    private JdbcTemplate     template;
    @Resource
    private SamplerRowMapper rowMapper;
    @Resource
    private BaseSamplerRowMapper baseRowMapper;

    private static final int TREND_LIMIT = 30;

    @Override
    public Trend getLatestSamples(String source, Currencies currency) {
        String cur = currency.code;
        List<Sample> samples = template.query(env.getProperty("latest.samples"),
                new Object[] { source, source, cur, TREND_LIMIT + 1, source, cur, TREND_LIMIT + 1 },
                new BeanPropertyRowMapper<Sample>(Sample.class));
        double max = template.queryForObject(env.getProperty("max.trend.value"), new Object[] { source, cur, TREND_LIMIT + 1 },
                Double.class);
        double min = template.queryForObject(env.getProperty("min.trend.value"), new Object[] { source, cur, TREND_LIMIT + 1 },
                Double.class);
        Trend trend = new Trend();
        trend.setSamples(samples);
        trend.setMax(max + 1);
        trend.setMin(min - 1);
        return trend;
    }

    @Override
    public List<Sample> getSnapshot(Currencies currency) {
        return template.query(env.getProperty("select.snapshot"), new Object[] { currency.code, currency.code, currency.code },
                rowMapper);
    }

    @Override
    public List<BaseSample> getChangesSnapshot(Currencies currency) {
        return template.query(env.getProperty("select.changes.snapshot"),
                new Object[] { currency.code, currency.code, currency.code }, baseRowMapper);
    }

    @Override
    public List<CurrencySnapshot> getCurrencySnapshot() {
        return template.query(env.getProperty("select.currency.snapshot"), new BeanPropertyRowMapper<CurrencySnapshot>(CurrencySnapshot.class));
    }
    
    
    @Override
    public void saveSnapshot(List<Sample> samples, Currencies currency) {
        String cur = currency.code;
        String saveSample = env.getProperty("save.sample");
        String saveSnapshot = env.getProperty("save.snapshot");
        for (Sample sample : samples) {
            if (sample != null) {
                template.update(saveSnapshot, sample.getCode(), sample.getBuyValue(), sample.getSellValue(), cur,
                        sample.getBuyValue(), sample.getSellValue(), sample.getCode(), cur, TREND_LIMIT + 1, sample.getCode(),
                        cur, TREND_LIMIT + 1);
                template.update(saveSample, sample.getCode(), sample.getBuyValue(), sample.getSellValue(), cur);
            }
        }

    }

}
