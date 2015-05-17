package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Endereco;

//Classe dao de manipulação e persistencia do objeto Cliente.
public class ClienteDao extends TemplateDao{
	
//	Atributos e objetos utilizados na classe.
	private boolean resposta = false;
	
	private Cliente cliente = new Cliente();
	
	private Endereco endereco = new Endereco();
	
	private List<Cliente> listaClientes = new ArrayList<Cliente>(); 
	
//	Metodo de cadastro de cliente.
	public boolean cadastrar(Cliente cliente) throws Exception {
		this.cliente = cliente;
		executaCadastro();
		return resposta;
	}
	
//	Metodo que atualiza/edita um cliente.
	public boolean atualizar(Cliente cliente) throws Exception {
		this.cliente = cliente;
		executaAtualizacao();
		return resposta;
	}
	
//	Metodo de listagem de cliente.
	public List<Cliente> listar() throws Exception {
		executaListagem();
		return listaClientes;
	}
	
//	Metodo de exclusão de um cliente.
	public boolean excluir(Cliente cliente) throws Exception {
		this.cliente = cliente;
		executaExclusao();
		return resposta;
	}
	
//	Metodo de busca por um cliente.
	public Cliente buscar(Cliente cliente) throws Exception {
		this.cliente = cliente;
		executaBusca();
		return this.cliente;
	}

//	Implementação dos metodos da classe template dao para execução da manipulação do objeto.

	@Override
	protected void cadastrar(Connection c) throws Exception {
		String sql = "INSERT INTO `sis_pizza_db`.`pessoa`"
				   + "(`cpf`, `nome`, `numero_rg`, `orgao_emissor_rg`, `telefone`, `data_cadastro`, `data_cancelamento_cadastro`)"
				   + "VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cliente.getNumeroCpf());
		ps.setString(2, cliente.getNome());
		ps.setString(3, cliente.getNumeroRg());
		ps.setString(4, cliente.getOrgaoEmissorRg());
		ps.setString(5, cliente.getNumeroTelefone());
		ps.setString(6, cliente.getDataCadastro());
		ps.setString(7, cliente.getDataCancelamentoCadastro());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
		
		if(resposta){
			sql = "INSERT INTO `sis_pizza_db`.`endereco`"
				+ "(`logradouro`, `bairro`, `numero_cep`, `cidade`, `uf`, `pessoa_cpf`)"
			    + "VALUES (?, ?, ?, ?, ?, ?);";
			ps = c.prepareStatement(sql);
			ps.setString(1, cliente.getEndereco().getLogradouro());
			ps.setString(2, cliente.getEndereco().getBairro());
			ps.setLong  (3, cliente.getEndereco().getNumeroCep());
			ps.setString(4, cliente.getEndereco().getCidade());
			ps.setString(5, cliente.getEndereco().getUnidadeFederativa());
			ps.setString(6, cliente.getNumeroCpf());
			
			rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "INSERT INTO `sis_pizza_db`.`login` (`pessoa_cpf`, `senha`) VALUES (?, ?);";
					ps = c.prepareStatement(sql);
					ps.setString(1, cliente.getNumeroCpf());
					ps.setString(2, cliente.getSenha());
					
					rs = ps.executeUpdate();
					
					resposta = rs > 0;
			}
		}
	}

	@Override
	protected void atualizar(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`pessoa` SET `nome` = ?, `numero_rg` = ?, `orgao_emissor_rg` = ?,"
				   + "`telefone` = ?, `data_cadastro` = ?, `data_cancelamento_cadastro` = ? WHERE `cpf` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getNumeroRg());
		ps.setString(3, cliente.getOrgaoEmissorRg());
		ps.setString(4, cliente.getNumeroTelefone());
		ps.setString(5, cliente.getDataCadastro());
		ps.setString(6, cliente.getDataCancelamentoCadastro());
		ps.setString(7, cliente.getNumeroCpf());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
		
		if(resposta){
			sql = "UPDATE `sis_pizza_db`.`endereco` SET `logradouro` = ?, `bairro` = ?,"
			    + "`numero_cep` = ?, `cidade` = ?, `uf` = ? WHERE `pessoa_cpf` = ?;";
			ps = c.prepareStatement(sql);
			ps.setString(1, cliente.getEndereco().getLogradouro());
			ps.setString(2, cliente.getEndereco().getBairro());
			ps.setLong  (3, cliente.getEndereco().getNumeroCep());
			ps.setString(4, cliente.getEndereco().getCidade());
			ps.setString(5, cliente.getEndereco().getUnidadeFederativa());
			ps.setString(6, cliente.getNumeroCpf());
			
			rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "UPDATE `sis_pizza_db`.`login` SET `senha` = ? WHERE `pessoa_cpf` = ?;";
					ps = c.prepareStatement(sql);
					ps.setString(1, cliente.getSenha());
					ps.setString(2, cliente.getNumeroCpf());
				
				rs = ps.executeUpdate();
					
				resposta = rs > 0;
			}
		}
	}

	@Override
	protected void listar(Connection c) throws Exception {
		String sql = "SELECT * FROM pessoa JOIN endereco ON  pessoa.cpf = endereco.pessoa_cpf JOIN login ON pessoa.cpf = login.pessoa_cpf WHERE pessoa.cpf = endereco.pessoa_cpf AND pessoa.perfil IS NULL;";
		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		listaClientes.clear();

		while(rs.next()){
			Cliente cliente = new Cliente();
			Endereco endereco = new Endereco();
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setNumeroCep(rs.getInt("numero_cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setUnidadeFederativa(rs.getString("uf"));
			cliente.setEndereco(endereco);
			cliente.setNome(rs.getString("nome"));
			cliente.setNumeroRg(rs.getString("numero_rg"));
			cliente.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
			cliente.setNumeroTelefone(rs.getString("telefone"));
			cliente.setDataCadastro(rs.getString("data_cadastro"));
			cliente.setDataCancelamentoCadastro(rs.getString("data_cancelamento_cadastro"));
			cliente.setNumeroCpf(rs.getString("cpf"));
			cliente.setSenha(rs.getString("senha"));
			
			listaClientes.add(cliente);
		}
	}

	@Override
	protected void excluir(Connection c) throws Exception {	
		String sql = "DELETE FROM endereco WHERE pessoa_cpf = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cliente.getNumeroCpf());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
		
		if(resposta){
			sql = "DELETE FROM login WHERE cpf = ?;";
			ps = c.prepareStatement(sql);
			ps.setString(1, cliente.getNumeroCpf());
			
			rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "DELETE FROM pessoa WHERE cpf = ?;";
				ps = c.prepareStatement(sql);
				ps.setString(1, cliente.getNumeroCpf());
				
				rs = ps.executeUpdate();
				
				resposta = rs > 0;
			}
		}
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		String sql = "SELECT * FROM pessoa JOIN endereco ON  pessoa.cpf = endereco.pessoa_cpf JOIN login ON pessoa.cpf = login.pessoa_cpf WHERE pessoa.cpf = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cliente.getNumeroCpf());

		ResultSet rs = ps.executeQuery();
		
		endereco = new Endereco();
		cliente = new Cliente();

		while(rs.next()){
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setNumeroCep(rs.getInt("numero_cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setUnidadeFederativa(rs.getString("uf"));
			cliente.setEndereco(endereco);
			cliente.setNome(rs.getString("nome"));
			cliente.setNumeroRg(rs.getString("numero_rg"));
			cliente.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
			cliente.setNumeroTelefone(rs.getString("telefone"));
			cliente.setDataCadastro(rs.getString("data_cadastro"));
			cliente.setDataCancelamentoCadastro(rs.getString("data_cancelamento_cadastro"));
			cliente.setNumeroCpf(rs.getString("cpf"));
			cliente.setSenha(rs.getString("senha"));
		}
	}

	@Override
	protected void login(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
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
