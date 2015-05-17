package model;


//Classe de modelo que representa um pedido com seus metodos de acesso e construtores.

public class Pedido {

	private Integer numero;
	private String dataPedido;
	private String horaPedido;
	private Integer numeroPizza;
	private String cpfCliente;
	private String cpfCozinheiro;
	private Integer numeroBebida;
	private double valorPedido;
	private double descontoPedido;
	private String dataCancelamento;
	private String horaCancelamento;
	private Integer quantidadePizza;
	private Integer quantidadeBebida;
	private String tamanhoPizza;
	private String observacao;
	private String situacao;
	private Bebida bebida;
	private Pizza pizza;
	
	public Pedido(Integer numero, String dataPedido, String horaPedido,
			Integer numeroPizza, String cpfCliente, String cpfCozinheiro,
			Integer numeroBebida, double valorPedido, double descontoPedido,
			String dataCancelamento, String horaCancelamento,
			Integer quantidadePizza, Integer quantidadeBebida,
			String tamanhoPizza, String observacao, String situacao,
			Bebida bebida, Pizza pizza) {
		this.numero = numero;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.numeroPizza = numeroPizza;
		this.cpfCliente = cpfCliente;
		this.cpfCozinheiro = cpfCozinheiro;
		this.numeroBebida = numeroBebida;
		this.valorPedido = valorPedido;
		this.descontoPedido = descontoPedido;
		this.dataCancelamento = dataCancelamento;
		this.horaCancelamento = horaCancelamento;
		this.quantidadePizza = quantidadePizza;
		this.quantidadeBebida = quantidadeBebida;
		this.tamanhoPizza = tamanhoPizza;
		this.observacao = observacao;
		this.situacao = situacao;
		this.bebida = bebida;
		this.pizza = pizza;
	}

	public Pedido() {

	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public Integer getNumeroPizza() {
		return numeroPizza;
	}

	public void setNumeroPizza(Integer numeroPizza) {
		this.numeroPizza = numeroPizza;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCpfCozinheiro() {
		return cpfCozinheiro;
	}

	public void setCpfCozinheiro(String cpfCozinheiro) {
		this.cpfCozinheiro = cpfCozinheiro;
	}

	public Integer getNumeroBebida() {
		return numeroBebida;
	}

	public void setNumeroBebida(Integer numeroBebida) {
		this.numeroBebida = numeroBebida;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public double getDescontoPedido() {
		return descontoPedido;
	}

	public void setDescontoPedido(double descontoPedido) {
		this.descontoPedido = descontoPedido;
	}

	public String getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getHoraCancelamento() {
		return horaCancelamento;
	}

	public void setHoraCancelamento(String horaCancelamento) {
		this.horaCancelamento = horaCancelamento;
	}

	public Integer getQuantidadePizza() {
		return quantidadePizza;
	}

	public void setQuantidadePizza(Integer quantidadePizza) {
		this.quantidadePizza = quantidadePizza;
	}

	public Integer getQuantidadeBebida() {
		return quantidadeBebida;
	}

	public void setQuantidadeBebida(Integer quantidadeBebida) {
		this.quantidadeBebida = quantidadeBebida;
	}

	public String getTamanhoPizza() {
		return tamanhoPizza;
	}

	public void setTamanhoPizza(String tamanhoPizza) {
		this.tamanhoPizza = tamanhoPizza;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
}
