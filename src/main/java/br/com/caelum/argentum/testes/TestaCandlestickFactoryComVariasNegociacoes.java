package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactoryComVariasNegociacoes {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(49.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(53.3, 100, hoje);
		
		// Crescente
		List<Negociacao> negociacoes = 
				// Crescente
				Arrays.asList(negociacao1,negociacao2,negociacao3,negociacao4); 
				// Decrescente
//				Arrays.asList(negociacao4,negociacao3,negociacao2,negociacao1);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		// Crescente: o valor mínimo não é substituído
		// Decrescente: Factory funciona normalmente
		System.out.println(candle);
	}

}
