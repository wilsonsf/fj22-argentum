package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, 
										List<Negociacao> negociacoes) {
		double maximo = 0; //negociacoes.get(0).getPreco();
		double minimo = Double.MAX_VALUE;//negociacoes.get(0).getPreco();
		double volume = 0;
		
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			
			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			} 
			if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}
		
		CandleBuilder builder = new CandleBuilder();
		builder.comAbertura(negociacoes.isEmpty() ? 0.0 : negociacoes.get(0).getPreco())
			   .comFechamento(negociacoes.isEmpty() ? 0.0 : negociacoes.get(negociacoes.size()-1).getPreco())
			   .comMaximo(maximo)
			   .comMinimo(negociacoes.isEmpty() ? 0.0 : minimo)
			   .comVolume(volume);
		builder.comData(data);
		
		return builder.geraCandle();
		
//		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
		
	}
}
