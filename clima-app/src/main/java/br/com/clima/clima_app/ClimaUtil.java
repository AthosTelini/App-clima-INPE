package br.com.clima.clima_app;

import java.util.Map;
import java.util.HashMap;

public class ClimaUtil {

    private static final Map<String, String> traducaoClima = new HashMap<>();

    static {
        traducaoClima.put("ec", "encoberto-chuva");
        traducaoClima.put("ci", "chuvas-isoladas");
        traducaoClima.put("c", "chuva");
        traducaoClima.put("in", "instavel");
        traducaoClima.put("pp", "chuva-sol");
        traducaoClima.put("cm", "chuva-melhora");
        traducaoClima.put("cn", "chuva-noite");
        traducaoClima.put("pt", "pancadas-tarde");
        traducaoClima.put("pm", "pancadas-manha");
        traducaoClima.put("np", "nublado-pancadas");
        traducaoClima.put("pc", "pancadas-chuva");
        traducaoClima.put("pn", "parcialmente-nublado");
        traducaoClima.put("cv", "chuvisco");
        traducaoClima.put("ch", "chuvoso");
        traducaoClima.put("t", "tempestade");
        traducaoClima.put("ps", "sol-chuva");
        traducaoClima.put("e", "encoberto");
        traducaoClima.put("n", "nublado");
        traducaoClima.put("cl", "ceu-claro");
        traducaoClima.put("nv", "nevoeiro");
        traducaoClima.put("g", "geada");
        traducaoClima.put("ne", "neve");
        traducaoClima.put("nd", "nao-definido");
        traducaoClima.put("pnt", "pancadas-noite");
        traducaoClima.put("psc", "possibilidade-chuva");
        traducaoClima.put("pcm", "possibilidade-chuva-manha");
        traducaoClima.put("pct", "possibilidade-chuva-tarde");
        traducaoClima.put("pcn", "possibilidade-chuva-noite");
        traducaoClima.put("npt", "nublado-pancadas-tarde");
        traducaoClima.put("npn", "nublado-pancadas-noite");
        traducaoClima.put("ncn", "nublado-chuva-noite");
        traducaoClima.put("nct", "nublado-chuva-tarde");
        traducaoClima.put("ncm", "nublado-chuva-manha");
        traducaoClima.put("npm", "nublado-pancadas-manha");
        traducaoClima.put("npp", "nublado-chuva-sol");
        traducaoClima.put("vn", "variacao-nuvens");
        traducaoClima.put("ct", "chuva-tarde");
        traducaoClima.put("ppn", "possibilidade-pancadas-noite");
        traducaoClima.put("ppt", "possibilidade-pancadas-tarde");
        traducaoClima.put("ppm", "possibilidade-pancadas-manha");
    }

    public static String getNomeImagem(String sigla) {
        return traducaoClima.getOrDefault(sigla, "nao-definido") + ".png";
    }
}