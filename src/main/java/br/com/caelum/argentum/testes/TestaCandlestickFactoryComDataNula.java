package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactoryComDataNula {
	public static void main(String[] args) {
		Negociacao negociacao1 = new Negociacao(40.5, 100, null);
		Negociacao negociacao2 = new Negociacao(45.0, 100, null);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1,negociacao2); 
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(null, negociacoes);
		
		// Erro apresentado no toString ao tentar converter data nula em texto
		System.out.println(candle);
	}
}
