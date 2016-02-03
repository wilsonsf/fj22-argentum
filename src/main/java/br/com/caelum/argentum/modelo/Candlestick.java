package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public final class Candlestick {
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;

	@Deprecated
	public Candlestick(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		this(BigDecimal.valueOf(abertura),
				BigDecimal.valueOf(fechamento),
				BigDecimal.valueOf(minimo),
				BigDecimal.valueOf(maximo),
				BigDecimal.valueOf(volume),
				data);
	}

	public Candlestick(BigDecimal abertura, BigDecimal fechamento, BigDecimal minimo,
			BigDecimal maximo, BigDecimal volume, Calendar data) {
		if (abertura == null || fechamento == null || minimo == null || maximo == null || volume == null || data == null)
			throw new IllegalArgumentException("Não pode inicializar um Candlestick com valores nulos");

		if (abertura.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("abertura nao pode ser negativo");

		if (fechamento.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("fechamento nao pode ser negativo");

		if (minimo.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("minimo nao pode ser negativo");

		if (maximo.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("maximo nao pode ser negativo");

		if (minimo.compareTo(maximo) > 0)
			throw new IllegalArgumentException("minimo nao pode ser maior que maximo");

		if (volume.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("volume nao pode ser negativo");

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	public boolean isAlta() {
		return abertura.compareTo(fechamento) != 1;
	}

	public boolean isBaixa() {
		return abertura.compareTo(fechamento) < 0;
	}

	@Override
	public String toString() {
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
