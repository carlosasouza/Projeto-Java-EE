package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Pizza;

//Classe dao de manipulação e persistencia do objeto pizza.

public class PizzaDao extends TemplateDao{

//	Atributos e objetos utilizados na classe.
	private boolean resposta = false;
	
	private Pizza pizza = new Pizza();
	
	private List<Pizza> listaPizzas = new ArrayList<Pizza>(); 
	
//	Metodo de cadastro de pizza.
	public boolean cadastrar(Pizza pizza) throws Exception {
		this.pizza = pizza;
		executaCadastro();
		return resposta;
	}
	
//	Metodo que atualiza/edita uma bebida.	
	public boolean atualizar(Pizza pizza) throws Exception {
		this.pizza = pizza;
		executaAtualizacao();
		return resposta;
	}
	
//	Metodo de listagem de pizzas.
	public List<Pizza> listar() throws Exception {
		executaListagem();
		return listaPizzas;
	}
	
//	Metodo de exclusão de uma pizza.
	public boolean excluir(Pizza pizza) throws Exception {
		this.pizza = pizza;
		executaExclusao();
		return resposta;
	}
	
//	Metodo de busca por uma pizza.
	public Pizza buscar(Pizza pizza) throws Exception {
		this.pizza = pizza;
		executaBusca();
		return this.pizza;
	}
	
//	Implementação dos metodos da classe template dao para execução da manipulação do objeto.
	@Override
	public void cadastrar(Connection c) throws Exception {
		String sql = "INSERT INTO `sis_pizza_db`.`pizza` (`numero_pizza`, `nome_pizza`, `tipo_pizza`, `tipo_massa`, `ingredientes_pizza`, `preco_pizza`)"
				   + "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt	(1, pizza.getNumero());
		ps.setString(2, pizza.getNome());
		ps.setString(3, pizza.getTipoPizza());
		ps.setString(4, pizza.getTipoMassa());
		ps.setString(5, pizza.getIngredientes());
		ps.setDouble(6, pizza.getPreco());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void atualizar(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`pizza` SET `nome_pizza` = ?, `tipo_pizza` = ?, `tipo_massa` = ?, `ingredientes_pizza` = ?, `preco_pizza` = ?"
				   + "WHERE `numero_pizza` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pizza.getNome());
		ps.setString(2, pizza.getTipoPizza());
		ps.setString(3, pizza.getTipoMassa());
		ps.setString(4, pizza.getIngredientes());
		ps.setDouble(5, pizza.getPreco());
		ps.setInt	(6, pizza.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void listar(Connection c) throws Exception {
		String sql = "SELECT * FROM pizza;";
		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		listaPizzas.clear();

		while(rs.next()){
			Pizza pizza = new Pizza();
			pizza.setNome(rs.getString("nome_pizza"));
			pizza.setTipoPizza(rs.getString("tipo_pizza"));
			pizza.setTipoMassa(rs.getString("tipo_massa"));
			pizza.setIngredientes(rs.getString("ingredientes_pizza"));
			pizza.setPreco(rs.getDouble("preco_pizza"));
			pizza.setNumero(rs.getInt("numero_pizza"));
			
			listaPizzas.add(pizza);
		}
	}

	@Override
	public void excluir(Connection c) throws Exception {
		String sql = "DELETE FROM pizza WHERE numero_pizza = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, pizza.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		String sql = "SELECT * FROM bebida WHERE nome_pizza = ? OR numero_pizza = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt	(1, pizza.getNumero() != null ? pizza.getNumero() : 0);
		ps.setString(2, pizza.getNome() != null ? pizza.getNome() : "");

		ResultSet rs = ps.executeQuery();

		pizza = new Pizza();

		while(rs.next()){
			Pizza pizza = new Pizza();
			pizza.setNome(rs.getString("nome_pizza"));
			pizza.setTipoPizza(rs.getString("tipo_pizza"));
			pizza.setTipoMassa(rs.getString("tipo_massa"));
			pizza.setIngredientes(rs.getString("ingredientes_pizza"));
			pizza.setPreco(rs.getDouble("preco_pizza"));
			pizza.setNumero(rs.getInt("numero_pizza"));
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
