package br.com.clima.clima_app.model;

import java.time.LocalDate;

public class Previsao {
	private LocalDate dia;
	private String tempo;
	private String maxima;
	private String minima;
	private String iuv;
	
	public LocalDate getDia() {
		return dia;
	}
	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getMaxima() {
		return maxima;
	}
	public void setMaxima(String maxima) {
		this.maxima = maxima;
	}
	public String getMinima() {
		return minima;
	}
	public void setMinima(String minima) {
		this.minima = minima;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
}