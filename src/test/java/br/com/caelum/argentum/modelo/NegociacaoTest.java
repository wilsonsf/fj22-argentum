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

		Negociacao n = new Negociacao(10,5,c);

		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		assertEquals(10, n.getData().get(Calendar.DAY_OF_MONTH));

		c.set(Calendar.YEAR, 2010);
		assertEquals(2015, n.getData().get(Calendar.YEAR));
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10,5,null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoDevePermitirNegociacaoComValorMenorIgualAZero() {
		new Negociacao(-5, 5, Calendar.getInstance());
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoDevePermitirQuantidadeComValorMenorIgualAZero() {
		new Negociacao(5, -5, Calendar.getInstance());
	}

}
