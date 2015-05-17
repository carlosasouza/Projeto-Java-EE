package model;

//Classe de modelo que representa uma bebida com seus metodos de acesso e construtores.
public class Bebida {

	private Integer numero;
	private String nome;
	private double preco;
	
	public Bebida(Integer numero, String nome, double preco) {
		this.numero = numero;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Bebida() {
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
