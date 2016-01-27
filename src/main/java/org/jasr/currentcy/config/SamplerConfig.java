package org.jasr.currentcy.config;

import org.jasr.currentcy.samplers.BROUSampler;
import org.jasr.currentcy.samplers.Inpulsedm;
import org.jasr.currentcy.samplers.SimpleJSoupSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to create Samplers that can be represented with any generic class that already existed. Samplers that need
 * special logic are represented with @Component in the samplers package
 *
 */
@Configuration
public class SamplerConfig {

    @Bean
    public SimpleJSoupSampler Trebol() {
        return new SimpleJSoupSampler("http://www.cambioeltrebol.com/", "Cambio El Tr&eacute;bol",
                "table#cotizaciones tr:eq(1) td.buy", "table#cotizaciones tr:eq(1) td.sale", "table#cotizaciones tr:eq(2) td.buy",
                "table#cotizaciones tr:eq(2) td.sale");
    }

    @Bean
    public SimpleJSoupSampler RedC() {
        return new SimpleJSoupSampler("http://www.redcambio.com.uy/", "Red Cambio",
                "div#pizarra div.moneda:eq(0) table tr:eq(1) td:eq(1)", "div#pizarra div.moneda:eq(0) table tr:eq(1) td:eq(2)",
                "div#pizarra div.moneda:eq(3) table tr:eq(1) td:eq(1)", "div#pizarra div.moneda:eq(3) table tr:eq(1) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Young() {
        return new SimpleJSoupSampler("http://www.youngencambio.com/", "Young en Cambio",
                "tr:eq(0) td:eq(0) tr:eq(3) tr:eq(3) tr:eq(0) tr:eq(1) td:eq(2)",
                "tr:eq(0) td:eq(0) tr:eq(3) tr:eq(3) tr:eq(0) tr:eq(1) td:eq(3)",
                "tr:eq(0) td:eq(0) tr:eq(3) tr:eq(3) tr:eq(0) tr:eq(2) td:eq(2)",
                "tr:eq(0) td:eq(0) tr:eq(3) tr:eq(3) tr:eq(0) tr:eq(2) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Flash() {
        return new SimpleJSoupSampler("http://www.flash.net.uy/money.php", "Flash S.F.",
                "table:eq(1) td:eq(1) td:eq(0) table:eq(2) td:eq(2)", "table:eq(1) td:eq(1) td:eq(0) table:eq(2) td:eq(3)",
                "table:eq(1) td:eq(1) td:eq(0) table:eq(5) td:eq(2)", "table:eq(1) td:eq(1) td:eq(0) table:eq(5) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Porto() {
        return new SimpleJSoupSampler("http://cambioporto.com.uy/", "Cambio Porto", "table.tabla-izquierda tr:eq(1) td:eq(1)",
                "table.tabla-izquierda tr:eq(1) td:eq(3)", "table.tabla-izquierda tr:eq(4) td:eq(1)",
                "table.tabla-izquierda tr:eq(4) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler MoneyC() {
        return new SimpleJSoupSampler("http://www.moneycenter.com.uy/Money/", "Pocitos Money Center",
                "div#dnn_ctr375_HtmlModule_HtmlModule_lblContent table tr:eq(1) td:eq(1)",
                "div#dnn_ctr375_HtmlModule_HtmlModule_lblContent table tr:eq(1) td:eq(2)",
                "div#dnn_ctr375_HtmlModule_HtmlModule_lblContent table tr:eq(4) td:eq(1)",
                "div#dnn_ctr375_HtmlModule_HtmlModule_lblContent table tr:eq(4) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler RevaT() {
        return new SimpleJSoupSampler("http://www.cambiorevatur.com/", "Revatur S.A.",
                "section#top-a div:eq(2) table tr:eq(1) td:eq(1)", "section#top-a div:eq(2) table tr:eq(1) td:eq(2)",
                "section#top-a div:eq(2) table tr:eq(4) td:eq(1)", "section#top-a div:eq(2) table tr:eq(4) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Europ() {
        return new SimpleJSoupSampler("http://www.europasf.com.uy/index_1.html", "Europa S.F.",
                "table.tablacotizaciones tr:eq(0) .celdacompra", "table.tablacotizaciones tr:eq(0) .celdaventa",
                "table.tablacotizaciones tr:eq(3) .celdacompra", "table.tablacotizaciones tr:eq(3) .celdaventa",
                "http://www.europasf.com.uy/mvdexchange/apizarradeldia.aspx");
    }

    @Bean
    public SimpleJSoupSampler Invest() {
        return new SimpleJSoupSampler("http://investa.com.uy/reclamos/investa/Content/Default.aspx", "Investa C.C.",
                "table#ctl00_ctlQuote_tblCurrency tr:eq(0) td:eq(2)", "table#ctl00_ctlQuote_tblCurrency tr:eq(0) td:eq(3)",
                "table#ctl00_ctlQuote_tblCurrency tr:eq(3) td:eq(2)", "table#ctl00_ctlQuote_tblCurrency tr:eq(3) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Fagal() {
        return new SimpleJSoupSampler("http://www.cambiofagalde.com.uy/home.php", "Cambio Fagalde",
                "table.Estilo4 tr:eq(1) td:eq(2)", "table.Estilo4 tr:eq(1) td:eq(3)", "table.Estilo4 tr:eq(4) td:eq(2)",
                "table.Estilo4 tr:eq(4) td:eq(3)");
    }

    @Bean
    public Inpulsedm Talsi() {
        return new Inpulsedm("http://www.cambiocentro.com/", "Cambio Centro");
    }

    @Bean
    public SimpleJSoupSampler LaFav() {
        return new SimpleJSoupSampler("http://www.lafavorita.com.uy/", "La Favorita S.F.",
                "div.grid_3:eq(0) ul.course-block li:eq(2)", "div.grid_3:eq(0) ul.course-block li:eq(5)",
                "div.grid_3:eq(1) ul.course-block li:eq(2)", "div.grid_3:eq(1) ul.course-block li:eq(5)");
    }

    @Bean
    public SimpleJSoupSampler AeroM() {
        return new SimpleJSoupSampler("http://www.aeromar.com.uy/", "Aeromar S.F.",
                "div.mod-cotizacion div.row div:eq(1) p:eq(1)", "div.mod-cotizacion div.row div:eq(2) p:eq(1)",
                "div.mod-cotizacion div.row div:eq(1) p:eq(4)", "div.mod-cotizacion div.row div:eq(2) p:eq(4)");
    }

    @Bean
    public SimpleJSoupSampler COri() {
        return new SimpleJSoupSampler("http://www.cambiooriental.com/", "Cambio Oriental", "table#cotizaciones tr:eq(0) td:eq(2)",
                "table#cotizaciones tr:eq(0) td:eq(3)", "table#cotizaciones tr:eq(1) td:eq(2)",
                "table#cotizaciones tr:eq(1) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Openn() {
        return new SimpleJSoupSampler("http://www.cambioopenn.com.uy/", "Openn C.C.", "div#tabla tr:eq(1) td:eq(1)",
                "div#tabla tr:eq(1) td:eq(2)", "div#tabla tr:eq(2) td:eq(1)", "div#tabla tr:eq(2) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler SaltoG() {
        return new SimpleJSoupSampler("http://cambiosaltogrande.com.uy/", "Salto Grande C.C.",
                "div.right_articles table tr:eq(1) td:eq(1)", "div.right_articles table tr:eq(1) td:eq(2)",
                "div.right_articles table tr:eq(4) td:eq(1)", "div.right_articles table tr:eq(4) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler BNA() {
        return new SimpleJSoupSampler("http://www.bna.com.uy/", "Banco de la Naci&oacute;n Argentina",
                "div#billetes table tr:eq(3) td:eq(1)", "div#billetes table tr:eq(3) td:eq(2)",
                "div#billetes table tr:eq(0) td:eq(1)", "div#billetes table tr:eq(0) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Carmel() {
        return new SimpleJSoupSampler("http://www.cambiocarmelo.com/", "Cambio Lerga", ".tablacotizaciones tr:eq(0) td:eq(1)",
                ".tablacotizaciones tr:eq(0) td:eq(2)", ".tablacotizaciones tr:eq(3) td:eq(1)",
                ".tablacotizaciones tr:eq(3) td:eq(2)", "http://www.europasf.com.uy/mvdexchange/apizarradeldia.aspx");
    }

    @Bean
    public SimpleJSoupSampler Matriz() {
        return new SimpleJSoupSampler("http://www.cambiomatriz.com.uy/", "Cambio Matriz",
                "td.col_der div.cuadros div.cont.cotizaciones table tbody tr:eq(0) td:eq(2)",
                "td.col_der div.cuadros div.cont.cotizaciones table tbody tr:eq(0) td:eq(4)",
                "td.col_der div.cuadros div.cont.cotizaciones table tbody tr:eq(3) td:eq(2)",
                "td.col_der div.cuadros div.cont.cotizaciones table tbody tr:eq(3) td:eq(4)");
    }

    @Bean
    public SimpleJSoupSampler Pando() {
        return new SimpleJSoupSampler("http://www.cambiopando.com.uy/", "Cambio Pando",
                "div.art-article table tbody tr:eq(0) td:eq(1)", "div.art-article table tbody tr:eq(0) td:eq(2)",
                "div.art-article table tbody tr:eq(3) td:eq(1)", "div.art-article table tbody tr:eq(3) td:eq(2)");
    }

    @Bean
    public BROUSampler Rocha() {

        return new BROUSampler("http://www.cambiorocha.com.uy/productos-servicios.php", "Cambio Rocha");
    }

    @Bean
    public BROUSampler Roman() {
        return new BROUSampler("http://www.cambioromantico.com/", "Cambio Rom&aacute;ntico");
    }

    @Bean
    public BROUSampler Varzy() {
        return new BROUSampler("http://www.cambiovarzy.com/", "Cambio Varzy S.A.");
    }

    @Bean
    public BROUSampler Nelson() {
        return new BROUSampler("http://cambionelson.com/", "Cambio Nelson");
    }

    @Bean
    public SimpleJSoupSampler Bandes() {
        return new SimpleJSoupSampler("https://www.bandes.com.uy/", "Bandes Uruguay", "table.cotizaciones tr:eq(1) td:eq(1)",
                "table.cotizaciones tr:eq(1) td:eq(2)", "table.cotizaciones tr:eq(4) td:eq(1)",
                "table.cotizaciones tr:eq(4) td:eq(2)");
    }

    @Bean
    public Inpulsedm Vertic() {
        return new Inpulsedm("http://www.cambiovertice.com/", "Cambio Vertice");
    }

    @Bean
    public SimpleJSoupSampler CMinas() {
        return new SimpleJSoupSampler("http://www.cambiominas.com.uy/pagina.php?id=Inicio", "Cambio Minas",
                "div.medio1_4 table:eq(1) tr:eq(0) td:eq(1)", "div.medio1_4 table:eq(1) tr:eq(0) td:eq(2)",
                "div.medio1_4 table:eq(1) tr:eq(6) td:eq(1)", "div.medio1_4 table:eq(1) tr:eq(6) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Finac() {
        return new SimpleJSoupSampler("http://www.finacam.com.uy/reclamos/finacam/Content/Default.aspx",
                "Finacam Inst Financiera", "table#ctl00_ctlQuote_tblCurrency tr:eq(0) td:eq(2)",
                "table#ctl00_ctlQuote_tblCurrency tr:eq(0) td:eq(3)", "table#ctl00_ctlQuote_tblCurrency tr:eq(3) td:eq(2)",
                "table#ctl00_ctlQuote_tblCurrency tr:eq(3) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Valsf() {
        return new SimpleJSoupSampler("http://www.valsf.com.uy/index_1.html", "Val S.F.",
                "td.backcotizacion table tbody tr:eq(0) table tbody tr td.celdacompra",
                "td.backcotizacion table tbody tr:eq(0) table tbody tr td.celdaventa",
                "td.backcotizacion table tbody tr:eq(3) table tbody tr td.celdacompra",
                "td.backcotizacion table tbody tr:eq(3) table tbody tr td.celdaventa",
                "http://www2.valsf.com.uy/mvdexchange/apizarradeldia.aspx");
    }

    @Bean
    public SimpleJSoupSampler Uruc() {
        return new SimpleJSoupSampler("http://www.urucambio.com.uy/", "UruCambio", "table#TBL11 tr:eq(0) td:eq(2)",
                "table#TBL11 tr:eq(0) td:eq(3)", "table#TBL11 tr:eq(3) td:eq(2)", "table#TBL11 tr:eq(3) td:eq(3)",
                "http://www.urucambio.com.uy/mvdexchange/HPizarraBasicaUC.cgi");
    }

    @Bean
    public SimpleJSoupSampler CUru() {
        return new SimpleJSoupSampler("http://www.cambiouruguay.com.uy/cambio/index.php", "Cambio Uruguay",
                "div.custom tr:eq(1) td:eq(2)", "div.custom tr:eq(1) td:eq(3)", "div.custom tr:eq(4) td:eq(2)",
                "div.custom tr:eq(4) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Dracma() {
        return new SimpleJSoupSampler("http://www.eurodracma.com/", "Eurodracma S.F.",
                "table#tabla_cotiz tbody tr:eq(0) td:eq(2)", "table#tabla_cotiz tbody tr:eq(0) td:eq(3)",
                "table#tabla_cotiz tbody tr:eq(3) td:eq(2)", "table#tabla_cotiz tbody tr:eq(3) td:eq(3)");
    }

    @Bean
    public SimpleJSoupSampler Pernas() {
        return new SimpleJSoupSampler("http://www.cambiopernas.com.uy/contacto.php", "Cambio Pernas",
                "div#divisas table table table tbody tr:eq(2) td:eq(3)", "div#divisas table table table tbody tr:eq(2) td:eq(6)",
                "div#divisas table table table tbody tr:eq(8) td:eq(3)", "div#divisas table table table tbody tr:eq(8) td:eq(6)");
    }

    @Bean
    public SimpleJSoupSampler Cambi() {
        return new SimpleJSoupSampler("http://www.cambialcasadecambios.com.uy/", "Cambial S.A.",
                "div.newsflash table table tbody tr:eq(1) td:eq(1) div span",
                "div.newsflash table table tbody tr:eq(1) td:eq(2) div span",
                "div.newsflash table table tbody tr:eq(4) td:eq(1) div span",
                "div.newsflash table table tbody tr:eq(4) td:eq(2) div span");
    }

    @Bean
    public SimpleJSoupSampler Prov() {
        return new SimpleJSoupSampler("http://www.provincia.com.uy/", "Provincia Casa Financiera",
                "div#cotizaciones table tbody tr:eq(4) td:eq(1)", "div#cotizaciones table tbody tr:eq(4) td:eq(2)",
                "div#cotizaciones table tbody tr:eq(2) td:eq(1)", "div#cotizaciones table tbody tr:eq(2) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Fucep() {
        SimpleJSoupSampler bean = new SimpleJSoupSampler("http://www.fucerep.com.uy/", "Fucerep",
                "div.block-cotizaciones table tbody tr:eq(0) td:eq(1)", "div.block-cotizaciones table tbody tr:eq(0) td:eq(2)",
                "div.block-cotizaciones table tbody tr:eq(1) td:eq(1)", "div.block-cotizaciones table tbody tr:eq(1) td:eq(2)");
        bean.setTimeout(0);
        return bean;
    }

    @Bean
    public SimpleJSoupSampler Varlix() {
        return new SimpleJSoupSampler("http://www.varlix.com.uy/", "Varlix S.F.",
                "aside.barRightContent div.cotizacion tr:eq(0) td:eq(4) strong",
                "aside.barRightContent div.cotizacion tr:eq(0) td:eq(5) strong",
                "aside.barRightContent div.cotizacion tr:eq(3) td:eq(4) strong",
                "aside.barRightContent div.cotizacion tr:eq(3) td:eq(5) strong");
    }

    @Bean
    public Inpulsedm Unic() {
        return new Inpulsedm("http://www.unicambio.com.uy/portal/hgxpp001.aspx", "uniCambio");
    }

    @Bean
    public Inpulsedm Regul() {
        return new Inpulsedm("http://www.cambioregul.com", "Cambio Regul SA");
    }

    @Bean
    public SimpleJSoupSampler Nix() {
        return new SimpleJSoupSampler("http://www.nixus.com.uy", "Nixus Servicios Financieros",
                "table#AutoNumber21 > tbody > tr:eq(1) tr:eq(3) td:eq(1)",
                "table#AutoNumber21 > tbody > tr:eq(1) tr:eq(3) td:eq(2)",
                "table#AutoNumber21 > tbody > tr:eq(1) tr:eq(6) td:eq(1)",
                "table#AutoNumber21 > tbody > tr:eq(1) tr:eq(6) td:eq(1)");
    }

    @Bean
    public SimpleJSoupSampler Monex() {
        return new SimpleJSoupSampler("http://www.monex.com.uy/", "Monex S.F.", "div#main:eq(0) div#column2",
                "div#main:eq(0) div#column3", "div#main:eq(3) div#column2", "div#main:eq(3) div#column3");
    }

    @Bean
    public SimpleJSoupSampler Miss() {
        return new SimpleJSoupSampler("http://www.cambiomisiones.com.uy/", "Cambio Misiones",
                "table.hovertable tr:eq(1) td:eq(1)", "table.hovertable tr:eq(1) td:eq(2)", "table.hovertable tr:eq(4) td:eq(1)",
                "table.hovertable tr:eq(4) td:eq(2)");
    }

    @Bean
    public SimpleJSoupSampler Iberia() {
        return new SimpleJSoupSampler("http://www.cambioiberia.com/", "Cambio Iberia",
                "div.entry-content table tr:eq(1) td:eq(2)", "div.entry-content table tr:eq(1) td:eq(3)",
                "div.entry-content table tr:eq(4) td:eq(2)", "div.entry-content table tr:eq(4) td:eq(3)");
    }

    @Bean
    public Inpulsedm Globus() {
        return new Inpulsedm("http://www.cambioglobus.com/portal/hgxpp001.aspx", "Cambios Globus Exchange");
    }

    @Bean
    public SimpleJSoupSampler Fortex() {
        return new SimpleJSoupSampler("http://www.fortex.com.uy/sitio/index.php?lang=es", "Fortex S.F.",
                "div.boxContenido div#cotizacion ul li:eq(0) p.monedaCompra",
                "div.boxContenido div#cotizacion ul li:eq(0) p.monedaVenta",
                "div.boxContenido div#cotizacion ul li:eq(3) p.monedaCompra",
                "div.boxContenido div#cotizacion ul li:eq(3) p.monedaVenta");
    }

    @Bean
    public SimpleJSoupSampler Gales() {
        return new SimpleJSoupSampler("http://www.gales.com.uy/home/", "Gales SA", ".cont_cotizaciones tbody tr:eq(0) td:eq(1)",
                ".cont_cotizaciones tbody tr:eq(0) td:eq(2)", ".cont_cotizaciones tbody tr:eq(3) td:eq(1)",
                ".cont_cotizaciones tbody tr:eq(3) td:eq(2)");
    }

    @Bean
    public Inpulsedm Pampex() {
        return new Inpulsedm("http://www.cambiopampex.com/portal/hgxpp001.aspx", "Cambio Pampex");
    }

    @Bean
    public SimpleJSoupSampler Obel() {
        return new SimpleJSoupSampler("http://www.cambioobelisco.com.uy/", "Cambio Obelisco",
                "table.moduletable table tr:eq(3) td:eq(2)", "table.moduletable table tr:eq(3) td:eq(3)",
                "table.moduletable table tr:eq(7) td:eq(2)", "table.moduletable table tr:eq(7) td:eq(3)");
    }

    @Bean
    public Inpulsedm CColon() {
        return new Inpulsedm("http://www.cambiocolonia.com.uy/portal/hgxpp001.aspx", "Cambio Colonia");
    }

    @Bean
    public SimpleJSoupSampler ASPN() {
        return new SimpleJSoupSampler("http://www.aspen.com.uy/sitio/", "Cambio Aspen", "table tbody tr.bd:eq(0) td.valor:eq(0)",
                "table tbody tr.bd:eq(0) td.valor:eq(1)", "table tbody tr.bd:eq(1) td.valor:eq(0)",
                "table tbody tr.bd:eq(1) td.valor:eq(1)");
    }

    @Bean
    public SimpleJSoupSampler CotD() {
        return new SimpleJSoupSampler("http://uy.cotizacion-dolar.com/cotizacion_hoy_uruguay.php",
                "Cotizaci&oacute;n D&oacute;lar Uruguay", ".cotizacion-contenido .cc-2b .cotizacion-num:eq(0)",
                ".cotizacion-contenido .cc-3b .cotizacion-num:eq(0)", ".cotizacion-contenido .cc-2b .cotizacion-num:eq(1)",
                ".cotizacion-contenido .cc-3b .cotizacion-num:eq(1)");
    }

    @Bean
    public SimpleJSoupSampler BBVa() {
        return new SimpleJSoupSampler("https://www.bbva.com.uy/Inicio/Personas/", "BBVa", "tbody tr:eq(1) td:eq(1)",
                "tbody tr:eq(1) td:eq(2)", "tbody tr:eq(0) td:eq(1)", "tbody tr:eq(0) td:eq(2)",
                "https://bbvanet.bbva.com.uy/WebInst/Cotizaciones");
    }

    @Bean
    public SimpleJSoupSampler Abit() {
        return new SimpleJSoupSampler("http://www.cambilex.com.uy", "Abitab(Cambilex)",
                "table#tabla_cambilex table tr:eq(1) td:eq(1) font>b", "table#tabla_cambilex table tr:eq(1) td:eq(2) font>b",
                "table#tabla_cambilex table tr:eq(2) td:eq(1) font>b", "table#tabla_cambilex table tr:eq(2) td:eq(2) font>b",
                "http://www.cambilex.com.uy/abitabinter/macros/cotizacion/innovanet/VerCotizaciones.jsp");
    }

    @Bean
    public SimpleJSoupSampler BCU() {
        return new SimpleJSoupSampler("http://www.bcu.gub.uy", "Banco Central del Uruguay", ".Cotizaciones tr:eq(1) td:eq(2)",
                ".Cotizaciones tr:eq(1) td:eq(2)", ".Cotizaciones tr:eq(3) td:eq(2)", ".Cotizaciones tr:eq(3) td:eq(2)",
                "http://www.bcu.gub.uy/Estadisticas-e-Indicadores/Paginas/Cotizaciones.aspx");
    }

    @Bean
    public BROUSampler BROU() {
        return new BROUSampler("http://www.brou.com.uy", "Banco Rep&uacute;blica");
    }

    @Bean
    public Inpulsedm Galim() {
        return new Inpulsedm("http://www.cambiogalimirsa.com/portal/hgxpp001.aspx?171", "Cambio Galimir");
    }

    @Bean
    public Inpulsedm Maio() {
        return new Inpulsedm("http://www.cambiomaiorano.com/portal/hgxpp001.aspx", "Cambio Maiorano");
    }

    @Bean
    public Inpulsedm Velso() {
        return new Inpulsedm("http://www.cambiovelso.com/portal/hgxpp001.aspx", "Cambio Velso");
    }

    @Bean
    public Inpulsedm Aguer() {
        return new Inpulsedm("http://www.cambioaguerrebere.com/portal/hgxpp001.aspx", "Cambio Aguerrebere SA");
    }

    @Bean
    public SimpleJSoupSampler AltC() {
        return new SimpleJSoupSampler("http://www.altercambio.com.uy", "Altercambio SA", "table.cuadrosLeft tr:eq(0) td:eq(2)",
                "table.cuadrosLeft tr:eq(0) td:eq(3)", "table.cuadrosLeft tr:eq(3) td:eq(2)",
                "table.cuadrosLeft tr:eq(3) td:eq(3)", "http://www.altercambio.com.uy/reclamos/altercambio/Content/Quote.aspx");
    }

    @Bean
    public SimpleJSoupSampler AVE() {
        return new SimpleJSoupSampler("http://www.avenida.com.uy", "Avenida S.F.", "div#bannerder table tr:eq(1) td:eq(1) div",
                "div#bannerder table tr:eq(1) td:eq(2) div", "div#bannerder table tr:eq(4) td:eq(1) div",
                "div#bannerder table tr:eq(4) td:eq(2) div", "http://www.avenida.com.uy/avenida-cotizaciones-diarias.php");
    }

}
