package br.com.caelum.argentum.testes;

import java.util.GregorianCalendar;

import br.com.caelum.argentum.modelo.CandleBuilder;
import br.com.caelum.argentum.modelo.Candlestick;

public class TestaCandlestickBuilder {

	public static void main(String[] args) {
		// TODO: Teste de criação de Candlestick com Builder
		
		CandleBuilder builder = new CandleBuilder();
		
//		builder.comAbertura(40.5);
//		builder.comFechamento(42.3);
//		builder.comMinimo(39.8);
//		builder.comMaximo(45.0);
//		builder.comVolume(145234.20);
//		builder.comData(new GregorianCalendar(2012, 8, 12, 0, 0));
		
		builder.comAbertura(40.5).comFechamento(42.3).comMinimo(39.8)
				.comMaximo(45.0).comVolume(145234.20).comData(
						new GregorianCalendar(2012, 8, 12, 0, 0));
		
		Candlestick candle = builder.geraCandle();
		
		System.out.println(candle);
		
	}

}
