package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bebida;
import model.Funcionario;
import model.Pizza;
import dao.BebidaDao;
import dao.FuncionarioDao;
import dao.PizzaDao;

/**
 * Servlet de pedido.
 */
public class NovoPedidoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher;
		
//		instancias dos objetos e listas a serem utilizados na classe.
		PizzaDao pizzaDao = new PizzaDao();
		BebidaDao bebidaDao = new BebidaDao();
		FuncionarioDao funcionarioDao = new FuncionarioDao(); 
		List<Pizza> listaPizzas = new ArrayList<Pizza>();
		List<Bebida> listaBebidas = new ArrayList<Bebida>();
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		
		try {
//			recupera as listas de pizzas e bebidas, envia para a sessão e redireciona para a página de novo pedido.
			listaPizzas.addAll(pizzaDao.listar());
			listaBebidas.addAll(bebidaDao.listar());
			listaFuncionarios.addAll(funcionarioDao.listar());
			
			if(listaPizzas.isEmpty() || listaBebidas.isEmpty()){
//				em caso de lista vazia, mostra a mensagem e redirecionapa para pagina inicial.
				requestDispatcher = request.getRequestDispatcher("erroIniciarPedido.html");
				requestDispatcher.forward(request, response);
			}
			else{
				request.getSession().setAttribute("listaPizzas", listaPizzas);
				request.getSession().setAttribute("listaBebidas", listaBebidas);
				request.getSession().setAttribute("listaFuncionarios", listaFuncionarios);
				requestDispatcher = request.getRequestDispatcher("novoPedido.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
//			em caso de exceção, mostra a mensagem e redirecionapa para pagina inicial.
			requestDispatcher = request.getRequestDispatcher("erroIniciarPedido.html");
			requestDispatcher.forward(request, response);
		}
	}

}
