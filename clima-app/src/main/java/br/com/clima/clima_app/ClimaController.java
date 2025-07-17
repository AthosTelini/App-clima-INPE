package br.com.clima.clima_app;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 

import br.com.clima.clima_app.model.Cidade;
import br.com.clima.clima_app.model.ListaCidades;
import br.com.clima.clima_app.model.Previsao;
import br.com.clima.clima_app.model.PrevisaoCidade;
import br.com.clima.clima_app.parse.XStreamParser;
import br.com.clima.clima_app.service.WeatherForecastService;

@Controller
public class ClimaController {

    @GetMapping("/")
    public String index(Model model) {
       
        model.addAttribute("cidadePesquisada", "");
        return "index";
    }

   
    @GetMapping("/api/cidades")
    @ResponseBody
    public List<Cidade> sugerirCidades(@RequestParam("termo") String termo) {
        if (termo == null || termo.length() < 3) {
            return Collections.emptyList(); 
        }
        try {
            String cidadesXML = WeatherForecastService.cidades(termo);
            ListaCidades listaCidades = XStreamParser.parseCidades(cidadesXML);
            
            return listaCidades.getCidades().stream().limit(7).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList(); 
        }
    }

 
    @PostMapping("/buscar-clima")
    public String buscarClima(@RequestParam("cidadeId") int cidadeId, @RequestParam("cidadeNome") String cidadeNome, Model model) {
        model.addAttribute("cidadePesquisada", cidadeNome); 
        try {
            String previsaoXML = WeatherForecastService.previsoesParaSeteDias(cidadeId);
            PrevisaoCidade previsaoCidade = XStreamParser.parsePrevisao(previsaoXML);

            if (previsaoCidade != null && !previsaoCidade.getPrevisoes().isEmpty()) {
                Previsao hoje = previsaoCidade.getPrevisoes().get(0);
                model.addAttribute("previsaoHoje", hoje);

                List<Previsao> proximosDias = (previsaoCidade.getPrevisoes().size() > 1)
                        ? previsaoCidade.getPrevisoes().subList(1, previsaoCidade.getPrevisoes().size())
                        : Collections.emptyList();
                model.addAttribute("proximosDias", proximosDias);
                model.addAttribute("cidadeInfo", previsaoCidade);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Não foi possível obter a previsão para esta cidade.");
        }

        return "index";
    }
}