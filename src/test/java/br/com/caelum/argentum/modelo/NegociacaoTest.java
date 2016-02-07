package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void naoDevePermitirMudarOPreco() {
		Negociacao negociacao = new Negociacao(BigDecimal.TEN, 5, Calendar.getInstance());

		BigDecimal preco = negociacao.getPreco();
		preco = preco.add(BigDecimal.TEN);

		assertTrue(negociacao.getPreco().equals(BigDecimal.TEN));
	}

	@Test
	public void dataDaNegociacaoEhImutavel(){
		Calendar c = new GregorianCalendar(2015,Calendar.JANUARY, 10);

		Negociacao n = new Negociacao(BigDecimal.TEN,5,c);

		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		assertEquals(10, n.getData().get(Calendar.DAY_OF_MONTH));

		c.set(Calendar.YEAR, 2010);
		assertEquals(2015, n.getData().get(Calendar.YEAR));
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(BigDecimal.TEN,5,null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoDevePermitirNegociacaoComValorMenorIgualAZero() {
		new Negociacao(BigDecimal.valueOf(-5), 5, Calendar.getInstance());
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoDevePermitirQuantidadeComValorMenorIgualAZero() {
		new Negociacao(BigDecimal.valueOf(5), -5, Calendar.getInstance());
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(BigDecimal.TEN, 5, agora);

		assertTrue(negociacao.isMesmODia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		agora.set(2016, 2, 7, 14, 0);

		Calendar algumasHorasDepois = (Calendar) agora.clone();
		algumasHorasDepois.add(Calendar.HOUR, 5);

		Negociacao negociacao = new Negociacao(BigDecimal.TEN, 5, agora);

		assertTrue(negociacao.isMesmODia(algumasHorasDepois));
	}

	@Test
	public void deveSaberSeUmaNegociacaoEhDaMesmaDataFornecida() {

		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(BigDecimal.TEN, 5, agora);

		assertTrue(negociacao.isMesmODia(mesmoMomento));
	}

}
