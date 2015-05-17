package model;

//Classe de modelo que representa um cliente com seus metodos de acesso e construtores.
public class Cliente extends Pessoa{
	
	private String dataCadastro;
	private String dataCancelamentoCadastro;
	
	public Cliente(String nome, String numeroRg, String orgaoEmissorRg,
			String numeroCpf, String numeroTelefone, Endereco endereco,
			String senha, String dataCadastro, String dataCancelamentoCadastro) {
		super(nome, numeroRg, orgaoEmissorRg, numeroCpf, numeroTelefone,
				endereco, senha);
		this.dataCadastro = dataCadastro;
		this.dataCancelamentoCadastro = dataCancelamentoCadastro;
	}
	
	public Cliente(){
		
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataCancelamentoCadastro() {
		return dataCancelamentoCadastro;
	}

	public void setDataCancelamentoCadastro(String dataCancelamentoCadastro) {
		this.dataCancelamentoCadastro = dataCancelamentoCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [getDataCadastro()=" + getDataCadastro()
				+ ", getDataCancelamentoCadastro()="
				+ getDataCancelamentoCadastro() + ", getNome()=" + getNome()
				+ ", getNumeroRg()=" + getNumeroRg() + ", getOrgaoEmissorRg()="
				+ getOrgaoEmissorRg() + ", getNumeroCpf()=" + getNumeroCpf()
				+ ", getNumeroTelefone()=" + getNumeroTelefone()
				+ ", getEndereco()=" + getEndereco() + ", getSenha()="
				+ getSenha() + "]";
	}
	
}
