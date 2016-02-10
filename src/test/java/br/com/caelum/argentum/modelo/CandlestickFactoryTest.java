package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
		Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
		Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

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

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

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

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
		Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
		Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

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

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
		Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
		Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

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

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		CandlestickFactory fabrica = new CandlestickFactory();
		fabrica.constroiCandleParaData(amanha, negociacoes);
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
		Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
		Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DATE, 1);

		Negociacao negociacao5 = new Negociacao(BigDecimal.valueOf(48.8), 100, amanha);
		Negociacao negociacao6 = new Negociacao(BigDecimal.valueOf(49.3), 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DATE, 1);

		Negociacao negociacao7 = new Negociacao(BigDecimal.valueOf(51.8), 100, depois);
		Negociacao negociacao8 = new Negociacao(BigDecimal.valueOf(52.3), 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5,
				negociacao6, negociacao7, negociacao8);

		CandlestickFactory factory = new CandlestickFactory();
		List<Candlestick> candles = factory.constroiCandles(negociacoes);

		assertEquals(3, candles.size());

		assertEquals(40.5, candles.get(0).getAbertura().doubleValue(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento().doubleValue(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura().doubleValue(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento().doubleValue(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura().doubleValue(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento().doubleValue(), 0.00001);
	}

	@Test
	public void devePermitirConstruirCandlesComNegociacoesForaDeOrdem() {
		Calendar hoje = Calendar.getInstance();
		Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DATE, -1);
		Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(48.8), 100, amanha);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2);

		CandlestickFactory factory = new CandlestickFactory();

		factory.constroiCandles(negociacoes);
	}

}
