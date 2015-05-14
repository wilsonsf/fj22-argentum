package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CandleBuilder {
	private double 	abertura,
					fechamento,
					minimo,
					maximo,
					volume;
	private GregorianCalendar data;
	private boolean isSetAbertura = false,
					isSetFechamento = false,
					isSetMinimo = false,
					isSetMaximo = false,
					isSetVolume = false,
					isSetData = false;
	
//	public CandleBuilder() {
//		isSetAbertura = isSetFechamento = isSetMinimo = isSetMaximo = isSetVolume = isSetData = false; 
//	}
	
	public CandleBuilder comAbertura(double abertura) {
		this.isSetAbertura = true;
		this.abertura = abertura;
		return this;
	}
	
	public CandleBuilder comFechamento (double fechamento) {
		this.isSetFechamento = true;
		this.fechamento = fechamento;
		return this;
	}

	public CandleBuilder comMinimo (double minimo) {
		this.isSetMinimo = true;
		this.minimo = minimo;
		return this;
	}
	
	public CandleBuilder comMaximo (double maximo) {
		this.isSetMaximo = true;
		this.maximo = maximo;
		return this;
	}
	
	public CandleBuilder comVolume (double volume) {
		this.isSetVolume = true;
		this.volume = volume;
		return this;
	}
	
	public CandleBuilder comData (GregorianCalendar data) {
		this.isSetData = true;
		this.data = data;
		return this;
	}
	
	public CandleBuilder comData (Calendar data) {
		return this.comData((GregorianCalendar) data);
	}
	
	public Candlestick geraCandle() {
		if (!isSetAbertura) {
			throw new IllegalStateException("Não chamou comAbertura(double)");
		}
		if (!isSetFechamento) {
			throw new IllegalStateException("Não chamou comFechamento(double)");
		}
		if (!isSetMaximo) {
			throw new IllegalStateException("Não chamou comMaximo(double)");
		}
		if (!isSetMinimo) {
			throw new IllegalStateException("Não chamou comMinimo(double)");
		}
		if (!isSetVolume) {
			throw new IllegalStateException("Não chamou comVolume(double)");
		}
		if (!isSetData) {
			throw new IllegalStateException("Não chamou comData(Calendar*)");
		}
		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
