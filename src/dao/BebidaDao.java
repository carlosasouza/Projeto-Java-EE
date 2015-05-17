package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Bebida;

//Classe dao de manipulação e persistencia do objeto Bebida.
public class BebidaDao extends TemplateDao {
		
//	Atributos e objetos utilizados na classe.
	private boolean resposta = false;
		
	private Bebida bebida = new Bebida();
	
	private List<Bebida> listabebidas = new ArrayList<Bebida>(); 
	

//	Metodo de cadastro de bebida.
	public boolean cadastrar(Bebida bebida) throws Exception {
		this.bebida = bebida;
		executaCadastro();
		return resposta;
	}
	
//	Metodo que atualiza/edita uma bebida.	
	public boolean atualizar(Bebida bebida) throws Exception {
		this.bebida = bebida;
		executaAtualizacao();
		return resposta;
	}
	
//	Metodo de listagem de bebidas.
	public List<Bebida> listar() throws Exception {
		executaListagem();
		return listabebidas;
	}
	
//	Metodo de exclusão de uma bebida.
	public boolean excluir(Bebida bebida) throws Exception {
		this.bebida = bebida;
		executaExclusao();
		return resposta;
	}
	
//	Metodo de busca por uma bebida.
	public Bebida buscar(Bebida bebida) throws Exception {
		this.bebida = bebida;
		executaBusca();
		return this.bebida;
	}
	
//	Implementação dos metodos da classe template dao para execução da manipulação do objeto.
	@Override
	public void cadastrar(Connection c) throws Exception {
		String sql = "INSERT INTO `sis_pizza_db`.`bebida` (`numero_bebida`, `nome_bebida`, `preco_bebida`)"
				   + "VALUES (null, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, bebida.getNome());
		ps.setDouble(2, bebida.getPreco());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void atualizar(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`bebida` SET `nome_bebida` = ?, `preco_bebida` = ?"
				   + "WHERE `numero_bebida` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, bebida.getNome());
		ps.setDouble(2, bebida.getPreco());
		ps.setInt	(3, bebida.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void listar(Connection c) throws Exception {
		String sql = "SELECT * FROM bebida;";
		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		listabebidas.clear();

		while(rs.next()){
			Bebida bebida = new Bebida();
			bebida.setNome(rs.getString("nome_bebida"));
			bebida.setNumero(rs.getInt("numero_bebida"));
			bebida.setPreco(rs.getFloat("preco_bebida"));
			
			listabebidas.add(bebida);
		}
	}

	@Override
	public void excluir(Connection c) throws Exception {
		String sql = "DELETE FROM bebida WHERE numero_bebida = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, bebida.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		String sql = "SELECT * FROM bebida WHERE nome_bebida = ? OR numero_bebida = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt	(1, bebida.getNumero() != null ? bebida.getNumero() : 0);
		ps.setString(2, bebida.getNome() != null ? bebida.getNome() : "");

		ResultSet rs = ps.executeQuery();

		bebida = new Bebida();

		while(rs.next()){
			bebida.setNome(rs.getString("nome_bebida"));
			bebida.setNumero(rs.getInt("numero_bebida"));
			bebida.setPreco(rs.getFloat("preco_bebida"));
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
