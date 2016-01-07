package org.jasr.currentcy.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Spring component to build the body for all emails in use in the application. Each method corresponds to a different kind of
 * email. Emails use freemarker for template building and value substitution.
 * 
 * @author jorge.amado
 *
 */
@Component
public class EmailBuilder {

    @Resource
    private Configuration configuration;

    public String getUpdateEmailBody(Currencies currency, Sample sample) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currency", currency);
        data.put("snapshot", sample);
        return doGetBody("ftl/rates.ftl", data);
    }

    public String getRegisterEmailBody(String token) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", token);
        return doGetBody("ftl/register.ftl", data);
    }

    /**
     * Method to abstract boilerplate code on each template building method. Each template can call this method passing its name
     * and data
     * 
     * @param templateName
     *            name of the freemarker template to build the body
     * @param data
     *            the values to insert into the template to customize it
     * @return fully built email body
     */
    private String doGetBody(String templateName, Map<String, Object> data) {
        try {
            Template template = configuration.getTemplate(templateName);
            Writer out = new StringWriter();
            template.process(data, out);
            out.flush();
            return out.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
