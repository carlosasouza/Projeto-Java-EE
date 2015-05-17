package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Funcionario;

//Classe dao de manipulação e persistencia do objeto Funcionario.
public class FuncionarioDao extends TemplateDao{

//	Atributos e objetos utilizados na classe.
	private boolean resposta = false;
	
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	
	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	
//	Metodo de cadastro de funcionario.
	public boolean cadastrar(Funcionario funcionario) throws Exception {
		this.funcionario = funcionario;
		executaCadastro();
		return resposta;
	}
	
//	Metodo que atualiza/edita um funcionario.
	public boolean atualizar(Funcionario funcionario) throws Exception {
		this.funcionario = funcionario;
		executaAtualizacao();
		return resposta;
	}
	
//	Metodo de listagem de funcionario.
	public List<Funcionario> listar() throws Exception {
		executaListagem();
		return listaFuncionarios;
	}
	
//	Metodo de exclusão de um funcinario.
	public boolean excluir(Funcionario funcionario) throws Exception {
		this.funcionario = funcionario;
		executaExclusao();
		return resposta;
	}
	
//	Metodo de busca por um funcionario.
	public Funcionario buscar(Funcionario funcionario) throws Exception {
		this.funcionario = funcionario;
		executaBusca();
		return this.funcionario;
	}
	
//	Implementação dos metodos da classe template dao para execução da manipulação do objeto.

	@Override
	public void cadastrar(Connection c) throws Exception {

			String sql = "INSERT INTO `sis_pizza_db`.`pessoa` (`cpf`, `nome`, `numero_rg`, `orgao_emissor_rg`, `telefone`, `matricula`, `perfil`)"
					   + "VALUES (?, ?, ?, ?, ?, null, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, funcionario.getNumeroCpf());
			ps.setString(2, funcionario.getNome());
			ps.setString(3, funcionario.getNumeroRg());
			ps.setString(4, funcionario.getOrgaoEmissorRg());
			ps.setString	(5, funcionario.getNumeroTelefone());
			ps.setString(6, funcionario.getPerfil());
			
			int rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "INSERT INTO `sis_pizza_db`.`endereco`"
					+ "(`logradouro`, `bairro`, `numero_cep`, `cidade`, `uf`, `pessoa_cpf`)"
				    + "VALUES (?, ?, ?, ?, ?, ?);";
				ps = c.prepareStatement(sql);
				ps.setString(1, funcionario.getEndereco().getLogradouro());
				ps.setString(2, funcionario.getEndereco().getBairro());
				ps.setLong  (3, funcionario.getEndereco().getNumeroCep());
				ps.setString(4, funcionario.getEndereco().getCidade());
				ps.setString(5, funcionario.getEndereco().getUnidadeFederativa());
				ps.setString(6, funcionario.getNumeroCpf());
				
				rs = ps.executeUpdate();
				
				resposta = rs > 0;
				
				if(resposta){
					sql = "INSERT INTO `sis_pizza_db`.`login` (`pessoa_cpf`, `senha`) VALUES (?, ?);";
						ps = c.prepareStatement(sql);
						ps.setString(1, funcionario.getNumeroCpf());
						ps.setString(2, funcionario.getSenha());
						
						rs = ps.executeUpdate();
						
						resposta = rs > 0;
				}
			}
	}

	@Override
	public void atualizar(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`pessoa` SET `nome` = ?, `numero_rg` = ?, `orgao_emissor_rg` = ?,"
				   + "`telefone` = ?, `matricula` = ?, `perfil` = ? WHERE `cpf` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getNumeroRg());
		ps.setString(3, funcionario.getOrgaoEmissorRg());
		ps.setString	(4, funcionario.getNumeroTelefone());
		ps.setInt	(5, funcionario.getMatricula());
		ps.setString(6, funcionario.getPerfil());
		ps.setString(7, funcionario.getNumeroCpf());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
		
		if(resposta){
			sql = "UPDATE `sis_pizza_db`.`endereco` SET `logradouro` = ?, `bairro` = ?,"
				    + "`numero_cep` = ?, `cidade` = ?, `uf` = ? WHERE `pessoa_cpf` = ?;";
			ps = c.prepareStatement(sql);
			ps.setString(1, funcionario.getEndereco().getLogradouro());
			ps.setString(2, funcionario.getEndereco().getBairro());
			ps.setLong  (3, funcionario.getEndereco().getNumeroCep());
			ps.setString(4, funcionario.getEndereco().getCidade());
			ps.setString(5, funcionario.getEndereco().getUnidadeFederativa());
			ps.setString(6, funcionario.getNumeroCpf());
			
			rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "UPDATE `sis_pizza_db`.`login` SET `senha` = ? WHERE `pessoa_cpf` = ?;";
					ps = c.prepareStatement(sql);
					ps.setString(1, funcionario.getSenha());
					ps.setString(2, funcionario.getNumeroCpf());
				
				rs = ps.executeUpdate();
					
				resposta = rs > 0;
			}
		}
	}

	@Override
	public void listar(Connection c) throws Exception {
		String sql = "SELECT * FROM pessoa JOIN endereco ON  pessoa.cpf = endereco.pessoa_cpf JOIN login ON pessoa.cpf = login.pessoa_cpf WHERE pessoa.cpf = endereco.pessoa_cpf AND pessoa.perfil IS NOT NULL;";
		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		listaFuncionarios.clear();

		while(rs.next()){
			Funcionario funcionario = new Funcionario();
			Endereco endereco = new Endereco();
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setNumeroCep(rs.getInt("numero_cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setUnidadeFederativa(rs.getString("uf"));
			funcionario.setEndereco(endereco);
			funcionario.setNome(rs.getString("nome"));
			funcionario.setNumeroRg(rs.getString("numero_rg"));
			funcionario.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
			funcionario.setNumeroTelefone(rs.getString("telefone"));
			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setPerfil(rs.getString("perfil"));
			funcionario.setNumeroCpf(rs.getString("cpf"));
			funcionario.setSenha(rs.getString("senha"));
			
			listaFuncionarios.add(funcionario);
		}
	}

	@Override
	public void excluir(Connection c) throws Exception {
		String sql = "DELETE FROM endereco WHERE pessoa_cpf = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, funcionario.getNumeroCpf());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
		
		if(resposta){
			
			sql = "DELETE FROM login WHERE cpf = ?;";
			ps = c.prepareStatement(sql);
			ps.setString(1, funcionario.getNumeroCpf());
			
			rs = ps.executeUpdate();
			
			resposta = rs > 0;
			
			if(resposta){
				sql = "DELETE FROM pessoa WHERE cpf = ? AND matricula = ?;";
				ps = c.prepareStatement(sql);
				ps.setString(1, funcionario.getNumeroCpf());
				ps.setInt	(2, funcionario.getMatricula());
				
				rs = ps.executeUpdate();
				
				resposta = rs > 0;
			}
		}
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		String sql = "SELECT * FROM pessoa JOIN endereco ON  pessoa.cpf = endereco.pessoa_cpf JOIN login ON pessoa.cpf = login.pessoa_cpf WHERE pessoa.cpf = ? OR pessoa.matricula = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, funcionario.getNumeroCpf() != null ? funcionario.getNumeroCpf() : "");
		ps.setInt	(2, funcionario.getMatricula() != null ? funcionario.getMatricula() : 0);

		ResultSet rs = ps.executeQuery();

		funcionario = new Funcionario();
		endereco = new Endereco();

		while(rs.next()){
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setNumeroCep(rs.getInt("numero_cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setUnidadeFederativa(rs.getString("uf"));
			funcionario.setEndereco(endereco);
			funcionario.setNome(rs.getString("nome"));
			funcionario.setNumeroRg(rs.getString("numero_rg"));
			funcionario.setOrgaoEmissorRg(rs.getString("orgao_emissor_rg"));
			funcionario.setNumeroTelefone(rs.getString("telefone"));
			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setPerfil(rs.getString("perfil"));
			funcionario.setNumeroCpf(rs.getString("cpf"));
			funcionario.setSenha(rs.getString("senha"));
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
