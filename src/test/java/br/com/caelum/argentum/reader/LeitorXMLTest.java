package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void deveCarregarXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste =
			"<list>"+
				"<negociacao>" +
			    	"<preco>43.5</preco>" +
			    	"<quantidade>1000</quantidade>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
			"</list>";


		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negocios = new LeitorXML().carrega(xml);

		assertEquals(1,negocios.size());
		assertEquals(43.5,negocios.get(0).getPreco().doubleValue(),0.00001);
		assertEquals(1000, negocios.get(0).getQuantidade());
	}

	@Test
	public void deveCarregarXmlComNenhumaNegociacao() {
		String xmlDeTeste = "<list></list>";

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negocios = new LeitorXML().carrega(xml);

		assertTrue(negocios != null);
		assertTrue("lista deve estar vazia",negocios.isEmpty());
	}

	@Test
	public void naoDeveCarregarXmlComNegociacaoSemPreco() {
		String xmlDeTeste =
			"<list>" +
				"<negociacao>" +
			    	"<quantidade>1000</quantidade>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
	    	"</list>";

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> list = new LeitorXML().carrega(xml);
		assertEquals(0, list.size());
	}

	@Test
	public void naoDeveCarregarXmlComNegociacaoSemData() {
		String xmlDeTeste =
			"<list>" +
				"<negociacao>" +
					"<preco>25.39</preco>" +
					"<quantidade>1000</quantidade>" +
				"</negociacao>" +
			"</list>";

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> list = new LeitorXML().carrega(xml);
		assertEquals(0, list.size());
	}

	@Test
	public void naoDeveCarregarXmlComNegociacaoSemQuantidade() {
		String xmlDeTeste =
			"<list>"+
				"<negociacao>" +
			    	"<preco>43.5</preco>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
			"</list>";

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> list = new LeitorXML().carrega(xml);
		assertEquals(0, list.size());
	}

	@Test
	public void carregaXmlComVariasNegociacoesEmLista() {
		String xmlDeTeste =
			"<list>"+
				"<negociacao>" +
			    	"<preco>43.5</preco>" +
			    	"<quantidade>1000</quantidade>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
	    		"<negociacao>" +
			    	"<preco>43.5</preco>" +
			    	"<quantidade>1000</quantidade>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
	    		"<negociacao>" +
			    	"<preco>43.5</preco>" +
			    	"<quantidade>1000</quantidade>" +
			    	"<data>" +
			    		"<time>1322233344455</time>" +
		    		"</data>" +
	    		"</negociacao>" +
			"</list>";

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negocios = new LeitorXML().carrega(xml);

		assertEquals(3,negocios.size());
		assertEquals(43.5,negocios.get(0).getPreco().doubleValue(),0.00001);
		assertEquals(1000, negocios.get(0).getQuantidade());
	}

}
