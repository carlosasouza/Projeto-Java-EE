package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pedido;
import dao.PedidoDao;

/**
 * Servlet implementation class DespachoPedidoServlet
 */
public class DespachoPedidoServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		Pedido pedido = new Pedido();
		
		pedido.setNumero(Integer.parseInt(request.getParameter("pedido")));
		
		pedido.setSituacao("Entrega");
		
		if(doAtualizacaoPedido(pedido)){
			requestDispatcher = request.getRequestDispatcher("ListaAtendimentoFuncionarioServlet");
			requestDispatcher.forward(request, response);
		}
		else{
			requestDispatcher = request.getRequestDispatcher("erroAtenderPedido.html");
			requestDispatcher.forward(request, response);
		}
		
	}
	
	private boolean doAtualizacaoPedido(Pedido pedido){
		
		PedidoDao pedidoDao = new PedidoDao();
		
		try {
			return pedidoDao.atualizarEstado(pedido);
		} catch (Exception e) {
			return false;
		}
	}

}
