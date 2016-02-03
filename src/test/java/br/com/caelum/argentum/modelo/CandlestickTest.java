package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

	@Test
	public void naoDevePermitirMudarAData() {
		Calendar data = new GregorianCalendar(2015,Calendar.JANUARY, 10);

		Candlestick candlestick = new Candlestick(BigDecimal.ZERO,BigDecimal.TEN,BigDecimal.ZERO,BigDecimal.TEN,BigDecimal.TEN, data);

		candlestick.getData().set(2014,Calendar.DECEMBER,25);
		data.add(Calendar.YEAR, 1);

		assertEquals(candlestick.getData().get(Calendar.YEAR),2015);
		assertEquals(candlestick.getData().get(Calendar.DAY_OF_YEAR),10);
	}

}
