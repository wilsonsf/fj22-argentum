package br.com.caelum.argentum.modelo;

//import br.com.caelum.argentum.modelo.CandleBuilder;

//import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class CandleBuilderTest {

	@Test(expected = IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		CandleBuilder builder = new CandleBuilder();
		builder.geraCandle();
		builder.comAbertura(10);
		builder.geraCandle();
		builder.comFechamento(10);
		builder.geraCandle();
		builder.comMaximo(10);
		builder.geraCandle();
		builder.comMinimo(10);
		builder.geraCandle();
		builder.comVolume(10);
		builder.geraCandle();
		builder.comData(Calendar.getInstance());
	}
}
