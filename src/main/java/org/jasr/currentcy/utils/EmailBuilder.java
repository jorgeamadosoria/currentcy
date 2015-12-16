package org.jasr.currentcy.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class EmailBuilder {

	@Resource
	private Configuration configuration;

	public String getUpdateEmailBody(List<List<Sample>> samplesByCurrency) {
		try {
			Template template = configuration.getTemplate("ftl/rates.ftl");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("currencies",Currencies.values());
			data.put("snapshots",samplesByCurrency);
			Writer out = new StringWriter();
			template.process(data, out);
			out.flush();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRegisterEmailBody(String token) {
		try {
			Template template = configuration.getTemplate("ftl/register.ftl");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("token", token);
			Writer out = new StringWriter();
			template.process(data, out);
			out.flush();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
