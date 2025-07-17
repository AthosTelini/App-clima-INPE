package br.com.clima.clima_app.parse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.time.LocalDateConverter;
import br.com.clima.clima_app.model.Cidade;
import br.com.clima.clima_app.model.ListaCidades;
import br.com.clima.clima_app.model.Previsao;
import br.com.clima.clima_app.model.PrevisaoCidade;

public class XStreamParser {

    public static ListaCidades parseCidades(String xml) {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{"br.com.clima.clima_app.model.**"});
        xstream.alias("cidades", ListaCidades.class);
        xstream.alias("cidade", Cidade.class);
        xstream.addImplicitCollection(ListaCidades.class, "cidades");
        return (ListaCidades) xstream.fromXML(xml);
    }

    public static PrevisaoCidade parsePrevisao(String xml) {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{"br.com.clima.clima_app.model.**"});
        xstream.registerConverter(new LocalDateConverter());
        xstream.alias("cidade", PrevisaoCidade.class);
        xstream.alias("previsao", Previsao.class);
        xstream.addImplicitCollection(PrevisaoCidade.class, "previsoes");
        return (PrevisaoCidade) xstream.fromXML(xml);
    }
}