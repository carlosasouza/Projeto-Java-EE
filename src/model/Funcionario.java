package model;

//Classe de modelo que representa um funcionario com seus metodos de acesso e construtores.

public class Funcionario extends Pessoa {

	private Integer matricula;
	private String perfil;
	
	public Funcionario(String nome, String numeroRg, String orgaoEmissorRg,
			String numeroCpf, String numeroTelefone, Endereco endereco,
			String senha, Integer matricula, String perfil) {
		super(nome, numeroRg, orgaoEmissorRg, numeroCpf, numeroTelefone,
				endereco, senha);
		this.matricula = matricula;
		this.perfil = perfil;
	}
	
	public Funcionario(){
		
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Funcionario [getMatricula()=" + getMatricula()
				+ ", getPerfil()=" + getPerfil() + ", getNome()=" + getNome()
				+ ", getNumeroRg()=" + getNumeroRg() + ", getOrgaoEmissorRg()="
				+ getOrgaoEmissorRg() + ", getNumeroCpf()=" + getNumeroCpf()
				+ ", getNumeroTelefone()=" + getNumeroTelefone()
				+ ", getEndereco()=" + getEndereco() + ", getSenha()="
				+ getSenha() + "]";
	}
	
}
