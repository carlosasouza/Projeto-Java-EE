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
import model.Pizza;
import dao.BebidaDao;
import dao.PizzaDao;

/**
 * Servlet de cadastro de cardápio.
 */
public class CardapioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		RequestDispatcher requestDispatcher;
		
//		Instancias dos objetos pizza e bebida e suas respectivas listas.
		PizzaDao pizzaDao = new PizzaDao();
		BebidaDao bebidaDao = new BebidaDao(); 
		List<Pizza> listaPizzas = new ArrayList<Pizza>();
		List<Bebida> listaBebidas = new ArrayList<Bebida>();
		
		try {
//			Tenta preencher as listas, caso uma delas esteja vazia mostra mensagem de erro e redireciona para pagina inicial.
			listaPizzas.addAll(pizzaDao.listar());
			listaBebidas.addAll(bebidaDao.listar());
			
			if(listaPizzas.isEmpty() || listaBebidas.isEmpty()){
				requestDispatcher = request.getRequestDispatcher("erroCardapio.html");
				requestDispatcher.forward(request, response);
			}
			else{
				request.getSession().setAttribute("listaPizzas", listaPizzas);
				request.getSession().setAttribute("listaBebidas", listaBebidas);				
				requestDispatcher = request.getRequestDispatcher("cardapio.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
//			em caso de exceção, mostra mensagem de erro e redireciona para pagina inicial.
			requestDispatcher = request.getRequestDispatcher("erroCardapio.html");
			requestDispatcher.forward(request, response);
		}
	}
}
