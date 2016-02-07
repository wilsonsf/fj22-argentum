package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Negociacao {

	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	@Deprecated
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
		this.data = (Calendar) data.clone();
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	@Override
	public String toString() {
		return "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(data.getTime()) + "\n"
			+	"Quantidade: " + quantidade + "\n"
			+	"Preço: " + preco + "\n"
			+ 	"Volume: " + getVolume() + "\n";
	}

	public boolean isMesmODia(Calendar outraData) {
		return data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH);
	}

}
