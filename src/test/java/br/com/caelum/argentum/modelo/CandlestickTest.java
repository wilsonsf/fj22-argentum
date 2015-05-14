package br.com.caelum.argentum.modelo;

import java.util.Calendar;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo () {
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {
		new Candlestick(10, 20, 20, 20, 100, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorAberturaNaoPodeSerNegativo (){
		new Candlestick(-10,10,10,10,10, Calendar.getInstance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorFechamentoNaoPodeSerNegativo (){
		new Candlestick(10,-10,10,10,10, Calendar.getInstance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorMinimoNaoPodeSerNegativo (){
		new Candlestick(10,10,-10,10,10, Calendar.getInstance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorMaximoNaoPodeSerNegativo (){
		new Candlestick(10,10,10,-10,10, Calendar.getInstance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorVolumeNaoPodeSerNegativo (){
		new Candlestick(10,10,10,10,-10, Calendar.getInstance());
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candlestick candle = new Candlestick(10,10,10,10,10, Calendar.getInstance());
		assertTrue("Candlestick.isAlta() nao esta em true", candle.isAlta());
		assertFalse("Candlestick.isBaixa() nao esta false",candle.isBaixa());
	}
	
}
