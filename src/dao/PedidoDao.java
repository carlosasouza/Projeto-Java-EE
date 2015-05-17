package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Bebida;
import model.Pedido;
import model.Pizza;

//Classe dao de manipulação e persistencia do objeto pedido.
public class PedidoDao extends TemplateDao{

//	Atributos e objetos utilizados na classe.
	private boolean resposta = false;
	
	private Pedido pedido = new Pedido();
	
	private List<Pedido> listaPedidos = new ArrayList<Pedido>(); 
	
//	Metodo de cadastro de pedido.
	public boolean cadastrar(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaCadastro();
		return resposta;
	}
	
//	Metodo que atualiza/edita um pedido.
	public boolean atualizar(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaAtualizacao();
		return resposta;
	}
	
//	Metodo que atualiza/edita um pedido.
	public boolean atualizarEstado(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaAtualizacaoEstado();
		return resposta;
	}

//	Metodo de listagem de pedido.
	public List<Pedido> listar() throws Exception {
		executaListagem();
		return listaPedidos;
	}
	
//	Metodo de exclusão de um pedido.
	public boolean excluir(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaExclusao();
		return resposta;
	}
	
//	Metodo de busca por um pedido.

	public List<Pedido> buscar(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaBusca();
		return listaPedidos;
	}
	
	public List<Pedido> buscarPedidoAtender(Pedido pedido) throws Exception {
		this.pedido = pedido;
		executaBuscaAtender();
		return listaPedidos;
	}
	
//	Implementação dos metodos da classe template dao para execução da manipulação do objeto.

	@Override
	public void cadastrar(Connection c) throws Exception {
		String sql = "INSERT INTO `sis_pizza_db`.`pedido` (`numero_pedido`, `data_pedido`, `pizza_numero_pizza`, `pessoa_cpf_cliente`, `pessoa_cpf_cozinheiro`,"
				   + "`bebida_numero_bebida`, `hora_pedido`, `valor_pedido`, `desconto_pedido`, `data_cancelamento`, `hora_cancelamento`, `quantidade_pizza`,"
				   + "`quantidade_bebida`, `tamanho_pizza`, `observacao`, `descricao_situacao_pedido`)"
				   + "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pedido.getDataPedido());
		ps.setInt	(2, pedido.getNumeroPizza());
		ps.setString(3, pedido.getCpfCliente());
		ps.setString(4, pedido.getCpfCozinheiro());
		ps.setInt	(5, pedido.getNumeroBebida());
		ps.setString(6, pedido.getHoraPedido());
		ps.setDouble(7, pedido.getValorPedido());
		ps.setDouble(8, pedido.getDescontoPedido());
		ps.setString(9, pedido.getDataCancelamento());
		ps.setString(10, pedido.getHoraCancelamento());
		ps.setInt	(11, pedido.getQuantidadePizza());
		ps.setInt	(12, pedido.getQuantidadeBebida());
		ps.setString(13, pedido.getTamanhoPizza());
		ps.setString(14, pedido.getObservacao());
		ps.setString(15, pedido.getSituacao());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}
	
	@Override
	public void atualizarEstado(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`pedido` SET `descricao_situacao_pedido` = ? WHERE `numero_pedido` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pedido.getSituacao());
		ps.setInt   (2, pedido.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void atualizar(Connection c) throws Exception {
		String sql = "UPDATE `sis_pizza_db`.`pedido` SET `data_pedido` = ?, `pizza_numero_pizza` = ?, `pessoa_cpf_cliente` = ?,"
				   + "`pessoa_cpf_cozinheiro` = ?, `bebida_numero_bebida` = ?, `hora_pedido` = ?, `valor_pedido` = ?, `desconto_pedido` = ?,"
				   + "`data_cancelamento` = ?, `hora_cancelamento` = ?, `quantidade_pizza` = ?, `quantidade_bebida` = ?, `tamanho_pizza` = ?,"
				   + "`observacao` = ?, `descricao_situacao_pedido` = ? WHERE `numero_pedido` = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pedido.getDataPedido());
		ps.setInt	(2, pedido.getNumeroPizza());
		ps.setString(3, pedido.getCpfCliente());
		ps.setString(4, pedido.getCpfCozinheiro());
		ps.setInt	(5, pedido.getNumeroBebida());
		ps.setString(6, pedido.getHoraPedido());
		ps.setDouble(7, pedido.getValorPedido());
		ps.setDouble(8, pedido.getDescontoPedido());
		ps.setString(9, pedido.getDataCancelamento());
		ps.setString(10, pedido.getHoraCancelamento());
		ps.setInt	(11, pedido.getQuantidadePizza());
		ps.setInt	(12, pedido.getQuantidadeBebida());
		ps.setString(13, pedido.getTamanhoPizza());
		ps.setString(14, pedido.getObservacao());
		ps.setString(15, pedido.getSituacao());
		ps.setInt	(16, pedido.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	public void listar(Connection c) throws Exception {
		String sql = "SELECT * FROM pedido;";
		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		listaPedidos.clear();

		while(rs.next()){
			Pedido pedido = new Pedido();
			pedido.setDataPedido(rs.getString("data_pedido"));
			pedido.setNumeroPizza(rs.getInt("pizza_numero_pizza"));
			pedido.setCpfCliente(rs.getString("pessoa_cpf_cliente"));
			pedido.setCpfCozinheiro(rs.getString("pessoa_cpf_cozinheiro"));
			pedido.setNumeroBebida(rs.getInt("bebida_numero_bebida"));
			pedido.setHoraPedido(rs.getString("hora_pedido"));
			pedido.setValorPedido(rs.getDouble("valor_pedido"));
			pedido.setDescontoPedido(rs.getDouble("desconto_pedido"));
			pedido.setDataCancelamento(rs.getString("data_cancelamento"));
			pedido.setHoraCancelamento(rs.getString("hora_cancelamento"));
			pedido.setQuantidadePizza(rs.getInt("quantidade_pizza"));
			pedido.setQuantidadeBebida(rs.getInt("quantidade_bebida"));
			pedido.setTamanhoPizza(rs.getString("tamanho_pizza"));
			pedido.setObservacao(rs.getString("observacao"));
			pedido.setSituacao(rs.getString("descricao_situacao_pedido"));
			pedido.setNumero(rs.getInt("numero_pedido"));
			
			listaPedidos.add(pedido);
		}
	}

	@Override
	public void excluir(Connection c) throws Exception {
		String sql = "DELETE FROM pedido WHERE numero_pedido = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, pedido.getNumero());
		
		int rs = ps.executeUpdate();
		
		resposta = rs > 0;
	}

	@Override
	protected void buscar(Connection c) throws Exception {
		String sql = "SELECT * FROM pedido JOIN pizza ON pizza.numero_pizza = pedido.pizza_numero_pizza JOIN bebida ON bebida.numero_bebida = pedido.bebida_numero_bebida "
				   + "WHERE (numero_pedido = ? OR pessoa_cpf_cliente = ? OR pessoa_cpf_cozinheiro = ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt	(1, pedido.getNumero() != null ? pedido.getNumero() : 0);
		ps.setString(2, pedido.getCpfCliente() != null ? pedido.getCpfCliente() : "");
		ps.setString(3, pedido.getCpfCozinheiro() != null ? pedido.getCpfCozinheiro() : "");

		ResultSet rs = ps.executeQuery();

		pedido = new Pedido();

		while(rs.next()){
			Pedido pedido = new Pedido();
			Bebida bebida = new Bebida();
			Pizza pizza = new Pizza();
			
			bebida.setNome(rs.getString("nome_bebida"));
			bebida.setNumero(Integer.parseInt(rs.getString("numero_bebida")));
			bebida.setPreco(rs.getDouble("preco_bebida"));
			
			pizza.setNome(rs.getString("nome_pizza"));
			pizza.setIngredientes(rs.getString("ingredientes_pizza"));
			pizza.setNumero(Integer.parseInt(rs.getString("pizza_numero_pizza")));
			pizza.setPreco(rs.getDouble("preco_pizza"));
			pizza.setTipoMassa(rs.getString("tipo_massa"));
			pizza.setTipoPizza(rs.getString("tipo_pizza"));
			
			pedido.setPizza(pizza);
			pedido.setBebida(bebida);
			pedido.setDataPedido(rs.getString("data_pedido"));
			pedido.setNumeroPizza(rs.getInt("pizza_numero_pizza"));
			pedido.setCpfCliente(rs.getString("pessoa_cpf_cliente"));
			pedido.setCpfCozinheiro(rs.getString("pessoa_cpf_cozinheiro"));
			pedido.setNumeroBebida(rs.getInt("bebida_numero_bebida"));
			pedido.setHoraPedido(rs.getString("hora_pedido"));
			pedido.setValorPedido(rs.getDouble("valor_pedido"));
			pedido.setDescontoPedido(rs.getDouble("desconto_pedido"));
			pedido.setDataCancelamento(rs.getString("data_cancelamento"));
			pedido.setHoraCancelamento(rs.getString("hora_cancelamento"));
			pedido.setQuantidadePizza(rs.getInt("quantidade_pizza"));
			pedido.setQuantidadeBebida(rs.getInt("quantidade_bebida"));
			pedido.setTamanhoPizza(rs.getString("tamanho_pizza"));
			pedido.setObservacao(rs.getString("observacao"));
			pedido.setSituacao(rs.getString("descricao_situacao_pedido"));
			pedido.setNumero(rs.getInt("numero_pedido"));
			
			listaPedidos.add(pedido);
		}		
	}

	@Override
	protected void login(Connection c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buscarAtender(Connection c) throws Exception {
		String sql = "SELECT * FROM pedido JOIN pizza ON pizza.numero_pizza = pedido.pizza_numero_pizza JOIN bebida ON bebida.numero_bebida = pedido.bebida_numero_bebida "
				   + "WHERE (numero_pedido = ? OR pessoa_cpf_cliente = ? OR pessoa_cpf_cozinheiro = ?) AND (descricao_situacao_pedido != 'Entrega');";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt	(1, pedido.getNumero() != null ? pedido.getNumero() : 0);
		ps.setString(2, pedido.getCpfCliente() != null ? pedido.getCpfCliente() : "");
		ps.setString(3, pedido.getCpfCozinheiro() != null ? pedido.getCpfCozinheiro() : "");

		ResultSet rs = ps.executeQuery();

		pedido = new Pedido();

		while(rs.next()){
			Pedido pedido = new Pedido();
			Bebida bebida = new Bebida();
			Pizza pizza = new Pizza();
			
			bebida.setNome(rs.getString("nome_bebida"));
			bebida.setNumero(Integer.parseInt(rs.getString("numero_bebida")));
			bebida.setPreco(rs.getDouble("preco_bebida"));
			
			pizza.setNome(rs.getString("nome_pizza"));
			pizza.setIngredientes(rs.getString("ingredientes_pizza"));
			pizza.setNumero(Integer.parseInt(rs.getString("pizza_numero_pizza")));
			pizza.setPreco(rs.getDouble("preco_pizza"));
			pizza.setTipoMassa(rs.getString("tipo_massa"));
			pizza.setTipoPizza(rs.getString("tipo_pizza"));
			
			pedido.setPizza(pizza);
			pedido.setBebida(bebida);
			pedido.setDataPedido(rs.getString("data_pedido"));
			pedido.setNumeroPizza(rs.getInt("pizza_numero_pizza"));
			pedido.setCpfCliente(rs.getString("pessoa_cpf_cliente"));
			pedido.setCpfCozinheiro(rs.getString("pessoa_cpf_cozinheiro"));
			pedido.setNumeroBebida(rs.getInt("bebida_numero_bebida"));
			pedido.setHoraPedido(rs.getString("hora_pedido"));
			pedido.setValorPedido(rs.getDouble("valor_pedido"));
			pedido.setDescontoPedido(rs.getDouble("desconto_pedido"));
			pedido.setDataCancelamento(rs.getString("data_cancelamento"));
			pedido.setHoraCancelamento(rs.getString("hora_cancelamento"));
			pedido.setQuantidadePizza(rs.getInt("quantidade_pizza"));
			pedido.setQuantidadeBebida(rs.getInt("quantidade_bebida"));
			pedido.setTamanhoPizza(rs.getString("tamanho_pizza"));
			pedido.setObservacao(rs.getString("observacao"));
			pedido.setSituacao(rs.getString("descricao_situacao_pedido"));
			pedido.setNumero(rs.getInt("numero_pedido"));
			
			listaPedidos.add(pedido);
		}		
		
	}
}
