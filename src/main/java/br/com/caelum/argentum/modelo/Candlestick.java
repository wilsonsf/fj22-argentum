package br.com.caelum.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public final class Candlestick {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candlestick(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		if (minimo > maximo) {
			throw new IllegalArgumentException("minimo nao pode ser maior que maximo");
		}
		if (abertura < 0) {
			throw new IllegalArgumentException("abertura nao pode ser negativo");
		}
		if (fechamento< 0) {
			throw new IllegalArgumentException("fechamento nao pode ser negativo");
		}
		if (minimo < 0) {
			throw new IllegalArgumentException("minimo nao pode ser negativo");
		}
		if (maximo < 0) {
			throw new IllegalArgumentException("maximo nao pode ser negativo");
		}
		if (volume < 0) {
			throw new IllegalArgumentException("volume nao pode ser negativo");
		}
		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}
		

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String abertura = "Abertura " + this.abertura;
		String fechamento = "Fechamento " + this.fechamento;
		String minimo = "Mínima " + this.minimo;
		String maximo = "Máxima " + this.maximo;
		String volume = "Volume " + this.volume;
		String data = "Data " + new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime());
		
		return Arrays.asList(abertura, fechamento, minimo, 
							maximo, volume, data).toString();
	}
}
