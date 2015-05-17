package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bebida;
import model.Cliente;
import model.Pedido;
import model.Pizza;
import dao.PedidoDao;

/**
 * Servlet de manipulação de pedido.
 */
public class PedidoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
//	lista de constantes para utilizar na classe, melhorando o look n feel.
	
	RequestDispatcher requestDispatcher;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(doCadastro(request, response)){
				requestDispatcher = request.getRequestDispatcher("ListaPedidosClienteServlet");
				requestDispatcher.forward(request, response);
			}else{
//				em caso de erro, mostra a mensagem e redireciona para pagina inical.
				requestDispatcher = request.getRequestDispatcher("erroIniciarPedido.html");
				requestDispatcher.forward(request, response);
			}
	}
	
//	private List<Pedido> doListagemPedidoFuncionario(HttpServletRequest request, HttpServletResponse response){
//		
//		Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionario");
//		
//		return null;
//	}
	
//	metodo que executa o cadastro de um pedido.
	@SuppressWarnings("unchecked")
	private boolean doCadastro(HttpServletRequest request, HttpServletResponse response){
		
		Pedido pedido = new Pedido();
		PedidoDao pedidoDao = new PedidoDao();
		
		int numeroPizza = Integer.parseInt(request.getParameter("sel_pizza"));
		int quantidadePizza = Integer.parseInt(request.getParameter("txt_qtd_pizza"));
		int numeroBebida = Integer.parseInt(request.getParameter("sel_bebida"));
		int quantidadeBebida = Integer.parseInt(request.getParameter("txt_qtd_bebida"));
		String funcionarioPizzaiolo = request.getParameter("sel_funcionario");
		String observacao = request.getParameter("txt_obs");
		
		Pizza pizza = null;
		Bebida bebida = null;
		
		List<Pizza> listaPizzas = (List<Pizza>) request.getSession().getAttribute("listaPizzas");
		List<Bebida> listaBebidas = (List<Bebida>) request.getSession().getAttribute("listaBebidas");
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		
		for (int i = 0; i < listaPizzas.size(); i++) {
			if(listaPizzas.get(i).getNumero() == numeroPizza){
				pizza = listaPizzas.get(i);
			}
		}
		for (int i = 0; i < listaBebidas.size(); i++) {
			if(listaBebidas.get(i).getNumero() == numeroBebida){
				bebida = listaBebidas.get(i);
			}
		}
		
		Double desconto = 1.99;
		Double valorPedido = (quantidadePizza * pizza.getPreco()) + (quantidadeBebida * bebida.getPreco()) - desconto;
		
		pedido.setCpfCliente(cliente.getNumeroCpf());
		pedido.setCpfCozinheiro(funcionarioPizzaiolo);
		pedido.setDataPedido(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		pedido.setDescontoPedido(0);
		pedido.setHoraPedido(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		pedido.setNumeroBebida(numeroBebida);
		pedido.setNumeroPizza(pizza.getNumero());
		pedido.setObservacao(observacao);
		pedido.setQuantidadeBebida(quantidadeBebida);
		pedido.setQuantidadePizza(quantidadePizza);
		pedido.setSituacao("Efetuado");
		pedido.setTamanhoPizza("Único");
		pedido.setValorPedido(valorPedido);
		
		try {
			return pedidoDao.cadastrar(pedido);
		} catch (Exception e) {
			return false;
		}
	}
}
