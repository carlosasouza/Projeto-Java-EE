package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PedidoDao;
import model.Cliente;
import model.Pedido;

/**
 * Servlet implementation class ListaPedidosClienteServlet
 */
public class ListaPedidosClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		List<Pedido> listaRespostaPedidos = doListagem(request, response);
		if(listaRespostaPedidos != null && !listaRespostaPedidos.isEmpty()){
			request.getSession().setAttribute("listaPedidos", listaRespostaPedidos);
			requestDispatcher = request.getRequestDispatcher("pedidos.jsp");
			requestDispatcher.forward(request, response);
		}
		else{
//			em caso de erro, exibe mensagem e redireciona para a pagina inicial.
			requestDispatcher = request.getRequestDispatcher("erroListarPedido.html");
			requestDispatcher.forward(request, response);
		}
	}
		
//		metodo que gera a listagem de pedidos.
	private List<Pedido> doListagem(HttpServletRequest request, HttpServletResponse response){
		
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		Pedido pedido = new Pedido();
		PedidoDao pedidoDao = new PedidoDao();
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		pedido.setCpfCliente(cliente.getNumeroCpf());;
		try {
			listaPedidos.addAll(pedidoDao.buscar(pedido));
			return listaPedidos;
		} catch (Exception e) {
			return null;
		}
	}
}
