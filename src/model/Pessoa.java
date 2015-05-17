package model;

//Classe de modelo que representa um pessoa com seus metodos de acesso e construtores.

public class Pessoa {
	
	private String nome;
	private String numeroRg;
	private String orgaoEmissorRg;
	private String numeroCpf;
	private String numeroTelefone;
	private Endereco endereco;
	private String senha;
	
	public Pessoa(String nome, String numeroRg, String orgaoEmissorRg,
			String numeroCpf, String numeroTelefone, Endereco endereco,
			String senha) {
		this.nome = nome;
		this.numeroRg = numeroRg;
		this.orgaoEmissorRg = orgaoEmissorRg;
		this.numeroCpf = numeroCpf;
		this.numeroTelefone = numeroTelefone;
		this.endereco = endereco;
		this.senha = senha;
	}
	
	public Pessoa(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Pessoa [getNome()=" + getNome() + ", getNumeroRg()="
				+ getNumeroRg() + ", getOrgaoEmissorRg()="
				+ getOrgaoEmissorRg() + ", getNumeroCpf()=" + getNumeroCpf()
				+ ", getNumeroTelefone()=" + getNumeroTelefone()
				+ ", getEndereco()=" + getEndereco() + ", getSenha()="
				+ getSenha() + "]";
	}	
}
