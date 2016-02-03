package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	public Negociacao(double preco, int quantidade, Calendar data) {
		this(BigDecimal.valueOf(preco),quantidade,data);
	}

	public Negociacao(BigDecimal preco, int quantidade, Calendar data) {
		if (data == null)
			throw new IllegalArgumentException("data nao pode ser nula");
		if (preco == null)
			throw new IllegalArgumentException("preco nao pode ser nulo");
		else if (preco.compareTo(BigDecimal.ZERO) != 1)
			throw new IllegalArgumentException("valor deve ser maior que 0");
		if (quantidade <= 0)
			throw new IllegalArgumentException("quantidade deve ser maior que 0");

		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		/*
		 * Calendar copia = Calendar.getInstance();
		 * copia.setTimeInMillis(this.data.getTimeInMillis());
		 * return copia;
		 */

		return (Calendar) data.clone();
	}

	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
}
