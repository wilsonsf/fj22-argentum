package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {

	public Candlestick constroiCandleParaData(Calendar data,
										List<Negociacao> negociacoes) {
		BigDecimal maximo = BigDecimal.ZERO;
		BigDecimal minimo = BigDecimal.valueOf(Double.MAX_VALUE);
		BigDecimal volume = BigDecimal.ZERO;

		for (Negociacao negociacao : negociacoes) {
			if (!isDatasIguais(data, negociacao.getData()))
					throw new IllegalArgumentException("data de negociacao diferente do candle");

			volume = volume.add(negociacao.getVolume());

			if (negociacao.getPreco().compareTo(maximo) > 0) {
				maximo = negociacao.getPreco();
			}
			if (negociacao.getPreco().compareTo(minimo) < 0) {
				minimo = negociacao.getPreco();
			}
		}

		CandleBuilder builder = new CandleBuilder();
		builder.comAbertura(negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco())
			   .comFechamento(negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(negociacoes.size()-1).getPreco())
			   .comMaximo(maximo)
			   .comMinimo(negociacoes.isEmpty() ? BigDecimal.ZERO : minimo)
			   .comVolume(volume);
		builder.comData(data);

		return builder.geraCandle();

//		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);

	}

	private boolean isDatasIguais(Calendar candleD, Calendar negociacaoD) {
		return candleD.get(Calendar.YEAR) == negociacaoD.get(Calendar.YEAR) &&
				candleD.get(Calendar.DAY_OF_YEAR) == negociacaoD.get(Calendar.DAY_OF_YEAR);
	}
}
