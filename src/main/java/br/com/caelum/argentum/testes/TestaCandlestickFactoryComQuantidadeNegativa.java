package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactoryComQuantidadeNegativa {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, -50, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, -100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1,negociacao2); 
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		// Volume calculado usa quantidade negativa no cálculo
		System.out.println(candle);
	}

}
