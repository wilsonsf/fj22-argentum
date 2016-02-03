package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1,negociacao2,negociacao3,negociacao4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(40.5, candle.getAbertura().doubleValue(), 0.00001);
		assertEquals(42.3, candle.getFechamento().doubleValue(), 0.00001);
		assertEquals(39.8, candle.getMinimo().doubleValue(), 0.00001);
		assertEquals(45.0, candle.getMaximo().doubleValue(), 0.00001);
		assertEquals(16760.0, candle.getVolume().doubleValue(), 0.00001);
	}

	@Test
	public void semNegociacoesGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacoes = Arrays.asList();

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(0.0, candle.getAbertura().doubleValue(), 0.00001);
		assertEquals(0.0, candle.getFechamento().doubleValue(), 0.00001);
		assertEquals(0.0, candle.getMaximo().doubleValue(), 0.00001);
		assertEquals(0.0, candle.getMinimo().doubleValue(), 0.00001);
		assertEquals(0.0, candle.getVolume().doubleValue(), 0.00001);
	}

	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(40.5, candle.getAbertura().doubleValue(), 0.00001);
		assertEquals(40.5, candle.getFechamento().doubleValue(), 0.00001);
		assertEquals(40.5, candle.getMinimo().doubleValue(), 0.00001);
		assertEquals(40.5, candle.getMaximo().doubleValue(), 0.00001);
		assertEquals(4050.0, candle.getVolume().doubleValue(), 0.00001);
	}

	@Test
	public void negociacoesEmOrdemCrescenteDeValor (){
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao3,negociacao1,negociacao4,negociacao2);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(39.8, candle.getAbertura().doubleValue(), 0.00001);
		assertEquals(45.0, candle.getFechamento().doubleValue(), 0.00001);
		assertEquals(39.8, candle.getMinimo().doubleValue(), 0.00001);
		assertEquals(45.0, candle.getMaximo().doubleValue(), 0.00001);
		assertEquals(16760.0, candle.getVolume().doubleValue(), 0.00001);
	}

	@Test
	public void negociacoesEmOrdemDecrescenteDeValor (){
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao2,negociacao4,negociacao1,negociacao3);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(45.0, candle.getAbertura().doubleValue(), 0.00001);
		assertEquals(39.8, candle.getFechamento().doubleValue(), 0.00001);
		assertEquals(39.8, candle.getMinimo().doubleValue(), 0.00001);
		assertEquals(45.0, candle.getMaximo().doubleValue(), 0.00001);
		assertEquals(16760.0, candle.getVolume().doubleValue(), 0.00001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void deveDarErroAoCriarCandleComNegociacoesDeDatasDiferentes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		CandlestickFactory fabrica = new CandlestickFactory();
		fabrica.constroiCandleParaData(amanha, negociacoes);
	}
}
