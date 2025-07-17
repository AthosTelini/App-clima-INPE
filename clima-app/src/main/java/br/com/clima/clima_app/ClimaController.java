package br.com.clima.clima_app;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.clima.clima_app.model.Cidade;
import br.com.clima.clima_app.model.ListaCidades;
import br.com.clima.clima_app.model.Previsao; // Importe a classe Previsao
import br.com.clima.clima_app.model.PrevisaoCidade;
import br.com.clima.clima_app.parse.XStreamParser;
import br.com.clima.clima_app.service.WeatherForecastService;


@Controller
public class ClimaController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/buscar-clima")
    public String buscarClima(@RequestParam("cidade") String nomeCidade, Model model) {
        try {
            String cidadesXML = WeatherForecastService.cidades(nomeCidade);
            ListaCidades listaCidades = XStreamParser.parseCidades(cidadesXML);
            List<Cidade> cidades = listaCidades.getCidades();

            if (cidades != null && !cidades.isEmpty()) {
                Cidade cidadeEncontrada = cidades.get(0);
                String previsaoXML = WeatherForecastService.previsoesParaSeteDias(cidadeEncontrada.getId());
                PrevisaoCidade previsaoCidade = XStreamParser.parsePrevisao(previsaoXML);

                // --- NOVA LÓGICA DE PREPARAÇÃO DE DADOS ---
                if (previsaoCidade != null && !previsaoCidade.getPrevisoes().isEmpty()) {
                    
                    // 1. Pega a previsão de HOJE (o primeiro item)
                    Previsao hoje = previsaoCidade.getPrevisoes().get(0);
                    model.addAttribute("previsaoHoje", hoje);

                    // 2. Cria uma lista APENAS com os próximos dias
                    List<Previsao> proximosDias;
                    if (previsaoCidade.getPrevisoes().size() > 1) {
                        proximosDias = previsaoCidade.getPrevisoes().subList(1, previsaoCidade.getPrevisoes().size());
                    } else {
                        proximosDias = Collections.emptyList(); // Lista vazia se só houver previsão para hoje
                    }
                    model.addAttribute("proximosDias", proximosDias);
                    
                    // 3. Envia informações gerais da cidade
                    model.addAttribute("cidadeInfo", previsaoCidade);
                }
                // --- FIM DA NOVA LÓGICA ---

            } else {
                model.addAttribute("erro", "Cidade não encontrada. Tente novamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Ocorreu um erro ao buscar a previsão. Verifique o console para mais detalhes.");
        }

        return "index";
    }
}