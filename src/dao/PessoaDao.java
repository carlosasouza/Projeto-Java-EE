package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cliente;
import model.Endereco;
import model.Funcionario;
import model.Pessoa;

public class PessoaDao  extends TemplateDao{

//	Instancias de objetos.
	private Pessoa pessoa = new Pessoa();
	private Endereco endereco;
	private Cliente cliente = new Cliente();
	private Funcionario funcionario = new Funcionario();
	
//	Objeto genérico.
	private Object objeto = new Object();
	
//	Método de login que retorna um objeto genérico, podendo ser cliente ou funcionario.
	public Object login(Pessoa pessoa) throws Exception {
		this.pessoa = pessoa;
		executaLogin();
		return this.objeto;
	}
	
	@Override
	protected void login(Connection c) throws Exception {
		String sql = "SELECT * FROM pessoa JOIN endereco ON  pessoa.cpf = endereco.pessoa_cpf JOIN login ON pessoa.cpf = login.pessoa_cpf WHERE login.pessoa_cpf = ? AND login.senha = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pessoa.getNumeroCpf());
		ps.setString(2, pessoa.getSenha());

		ResultSet rs = ps.executeQuery();
		
		this.objeto = new Object();

		while(rs.next()){
			endereco = new Endereco();
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setNumeroCep(rs.getInt("numero_cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setUnidadeFederativa(rs.getString("uf"));
			
			if(rs.getString("perfil") == null){
				funcionario = null;
				cliente = new Cliente();
				cliente.setEndereco(endereco);
				cliente.setNome(rs.getString("nome"));
				cliente.setNumeroRg(rs.getString("numero_rg"));
				cliente.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
				cliente.setNumeroTelefone(rs.getString("telefone"));
				cliente.setDataCadastro(rs.getString("data_cadastro"));
				cliente.setDataCancelamentoCadastro(rs.getString("data_cancelamento_cadastro"));
				cliente.setNumeroCpf(rs.getString("cpf"));
				cliente.setSenha(rs.getString("senha"));
				
//				Define que o objeto genérioc será um cliente.
				this.objeto = cliente;
			}
			else if(rs.getString("perfil") != null){
				cliente = null;
				funcionario = new Funcionario();
				funcionario.setEndereco(endereco);
				funcionario.setNome(rs.getString("nome"));
				funcionario.setNumeroRg(rs.getString("numero_rg"));
				funcionario.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
				funcionario.setNumeroTelefone(rs.getString("telefone"));
				funcionario.setNumeroCpf(rs.getString("cpf"));
				funcionario.setSenha(rs.getString("senha"));

//				Define que o objeto genérioc será um funcionário.
				this.objeto = funcionario;
			}
		}
	}
	
	@Override
	protected void cadastrar(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void atualizar(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void listar(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void excluir(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	@Override
	protected void atualizarEstado(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buscarAtender(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
