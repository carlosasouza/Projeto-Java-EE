package model;

//Classe de modelo que representa um endereço com seus metodos de acesso e construtores.

public class Endereco {
	
	private String logradouro;
	private String bairro;
	private Integer numeroCep;
	private String cidade;
	private String unidadeFederativa;
	
	public Endereco(String logradouro, String bairro, Integer numeroCep,
			String cidade, String unidadeFederativa) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numeroCep = numeroCep;
		this.cidade = cidade;
		this.unidadeFederativa = unidadeFederativa;
	}

	public Endereco(){
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumeroCep() {
		return numeroCep;
	}

	public void setNumeroCep(Integer numeroCep) {
		this.numeroCep = numeroCep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(String unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	@Override
	public String toString() {
		return "Endereco [getLogradouro()=" + getLogradouro()
				+ ", getBairro()=" + getBairro() + ", getNumeroCep()="
				+ getNumeroCep() + ", getCidade()=" + getCidade()
				+ ", getUnidadeFederativa()=" + getUnidadeFederativa() + "]";
	}
	
}
