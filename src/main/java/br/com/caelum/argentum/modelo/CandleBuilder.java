package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CandleBuilder {
	private BigDecimal 	abertura,
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

	@Deprecated
	public CandleBuilder comAbertura(double abertura) {
		return comAbertura(BigDecimal.valueOf(abertura));
	}

	public CandleBuilder comAbertura(BigDecimal abertura) {
		isSetAbertura = true;
		this.abertura = abertura;
		return this;
	}

	@Deprecated
	public CandleBuilder comFechamento (double fechamento) {
		return comFechamento(BigDecimal.valueOf(fechamento));
	}

	public CandleBuilder comFechamento (BigDecimal fechamento) {
		isSetFechamento = true;
		this.fechamento = fechamento;
		return this;
	}

	@Deprecated
	public CandleBuilder comMinimo (double minimo) {
		return comMinimo(BigDecimal.valueOf(minimo));
	}

	public CandleBuilder comMinimo (BigDecimal minimo) {
		isSetMinimo = true;
		this.minimo = minimo;
		return this;
	}

	@Deprecated
	public CandleBuilder comMaximo (double maximo) {
		return comMaximo(BigDecimal.valueOf(maximo));
	}

	public CandleBuilder comMaximo (BigDecimal maximo) {
		isSetMaximo = true;
		this.maximo = maximo;
		return this;
	}

	@Deprecated
	public CandleBuilder comVolume (double volume) {
		return comVolume(BigDecimal.valueOf(volume));
	}

	public CandleBuilder comVolume (BigDecimal volume) {
		isSetVolume = true;
		this.volume = volume;
		return this;
	}

	public CandleBuilder comData (GregorianCalendar data) {
		isSetData = true;
		this.data = data;
		return this;
	}

	public CandleBuilder comData (Calendar data) {
		return this.comData((GregorianCalendar) data);
	}

	public Candlestick geraCandle() {
		if (!isSetAbertura)
			throw new IllegalStateException("Não chamou comAbertura(BigDecimal)");
		if (!isSetFechamento)
			throw new IllegalStateException("Não chamou comFechamento(BigDecimal)");
		if (!isSetMaximo)
			throw new IllegalStateException("Não chamou comMaximo(BigDecimal)");
		if (!isSetMinimo)
			throw new IllegalStateException("Não chamou comMinimo(BigDecimal)");
		if (!isSetVolume)
			throw new IllegalStateException("Não chamou comVolume(BigDecimal)");
		if (!isSetData)
			throw new IllegalStateException("Não chamou comData(Calendar*)");

		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
