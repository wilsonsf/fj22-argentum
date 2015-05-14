package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactoryComQuantidadeZero {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 0, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 0, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1,negociacao2); 
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		// Volume está saindo 0 por causa da quantidade zerada.
		System.out.println(candle);
	}

}
