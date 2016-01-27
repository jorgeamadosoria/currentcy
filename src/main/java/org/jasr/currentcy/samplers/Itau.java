package org.jasr.currentcy.samplers;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Component("Itau")
public class Itau extends SamplerBase {
    public class DeltaHandler extends DefaultHandler {

        private boolean cotizacionFlag = false;
        private boolean monIdFlag      = false;
        private boolean CotMCotComFlag = false;
        private boolean CotMCotVenFlag = false;
        private boolean parsingDollars = false;
        private boolean parsingEuro    = false;
        private double  buyDollar      = 0;
        private double  sellDollar     = 0;
        private double  buyEuro        = 0;
        private double  sellEuro       = 0;

        public double getBuyDollar() {
            return buyDollar;
        }

        public void setBuyDollar(double buyDollar) {
            this.buyDollar = buyDollar;
        }

        public double getSellDollar() {
            return sellDollar;
        }

        public void setSellDollar(double sellDollar) {
            this.sellDollar = sellDollar;
        }

        public double getBuyEuro() {
            return buyEuro;
        }

        public void setBuyEuro(double buyEuro) {
            this.buyEuro = buyEuro;
        }

        public double getSellEuro() {
            return sellEuro;
        }

        public void setSellEuro(double sellEuro) {
            this.sellEuro = sellEuro;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if ("cotizacion".equalsIgnoreCase(qName)) {
                cotizacionFlag = true;
            }

            if (cotizacionFlag) {
                if ("moneda".equalsIgnoreCase(qName)) {
                    monIdFlag = true;
                }
                if ("compra".equalsIgnoreCase(qName)) {
                    CotMCotComFlag = true;
                }
                if ("venta".equalsIgnoreCase(qName)) {
                    CotMCotVenFlag = true;
                }
            }

        }

        public void endElement(String uri, String localName, String qName) throws SAXException {

            if ("cotizacion".equalsIgnoreCase(qName)) {
                cotizacionFlag = false;
                parsingDollars = false;
                parsingEuro = false;
            }

            if (cotizacionFlag) {
                if ("moneda".equalsIgnoreCase(qName)) {
                    monIdFlag = false;
                }
                if ("compra".equalsIgnoreCase(qName)) {
                    CotMCotComFlag = false;
                }
                if ("venta".equalsIgnoreCase(qName)) {
                    CotMCotVenFlag = false;
                }
            }

        }

        public void characters(char ch[], int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            // System.out.println(value);
            if (cotizacionFlag) {
                if (monIdFlag && !"US.D".equals(value.trim()) && !"EUR".equals(value.trim())) {
                    cotizacionFlag = false;
                    monIdFlag = false;
                    parsingDollars = false;
                    parsingEuro = false;
                    return;
                }
                if (monIdFlag) {
                    if ("US.D".equals(value.trim()))
                        parsingDollars = true;
                    else if ("EUR".equals(value.trim()))
                        parsingEuro = true;
                }

                if (parsingDollars) {
                    if (CotMCotComFlag)
                        buyDollar = new Double(value.trim().replace(",", "."));
                    if (CotMCotVenFlag)
                        sellDollar = new Double(value.trim().replace(",", "."));
                }

                if (parsingEuro) {
                    if (CotMCotComFlag)
                        buyEuro = new Double(value.trim().replace(",", "."));
                    if (CotMCotVenFlag)
                        sellEuro = new Double(value.trim().replace(",", "."));
                }
            }

        }

    };

    public DeltaHandler sampleXML(String xml) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DeltaHandler handler = new DeltaHandler();
            saxParser.parse(new InputSource(new StringReader(xml)), handler);

            return handler;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {

        DeltaHandler handler = sampleXML(doc.toString());
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = handler.getBuyDollar();
            sellValue = handler.getSellDollar();
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = handler.getBuyEuro();
            sellValue = handler.getSellEuro();
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public Itau() {
        super("https://www.itau.com.uy/inst/","Ita&uacute;","https://www.itau.com.uy/inst/aci/cotiz.xml");
    }

}
