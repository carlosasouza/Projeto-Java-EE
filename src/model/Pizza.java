package model;

//Classe de modelo que representa uma pizza com seus metodos de acesso e construtores.

public class Pizza {

	private Integer numero;
	private String nome;
	private String tipoPizza;
	private String tipoMassa;
	private String ingredientes;
	private double preco;

	public Pizza(Integer numero, String nome, String tipoPizza,
			String tipoMassa, String ingredientes, Double preco) {
		this.numero = numero;
		this.nome = nome;
		this.tipoPizza = tipoPizza;
		this.tipoMassa = tipoMassa;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	public Pizza() {
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
	public String getTipoPizza() {
		return tipoPizza;
	}
	public void setTipoPizza(String tipoPizza) {
		this.tipoPizza = tipoPizza;
	}
	public String getTipoMassa() {
		return tipoMassa;
	}
	public void setTipoMassa(String tipoMassa) {
		this.tipoMassa = tipoMassa;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}	
}
