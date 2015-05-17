package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;
import dao.PessoaDao;

/**
 * Servlet de login de usuário.
 */
public class LoginServlet extends HttpServlet {
	
	private Pessoa pessoa = new Pessoa(); 
	private PessoaDao pessoaDao = new PessoaDao();
	private Object objetoLogin = new Object();
	private RequestDispatcher requestDispatcher;
	
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
//		Recupera os dados do formulario pelo request.
		pessoa.setNumeroCpf(request.getParameter("txt_cpf"));
		pessoa.setSenha(request.getParameter("txt_senha"));
		
		try {
//			recupera um objeto do metodo login de pessoa dao. 
			objetoLogin = pessoaDao.login(pessoa);
			
//			verifica se o objeto tem o nome de cliente, se sim, coloca-o na sessão e redireciona para area home do cliente.
			if(objetoLogin.getClass().getName().equals("model.Cliente")){
				request.getSession().setAttribute("cliente", objetoLogin);
				requestDispatcher = request.getRequestDispatcher("home.jsp");
				requestDispatcher.forward(request, response);
			}
//			verifica se o objeto tem o nome de funcionario, se sim, coloca-o na sessão e redireciona para area adm do funcionario.
			else if(objetoLogin.getClass().getName().equals("model.Funcionario")){
				request.getSession().setAttribute("funcionario", objetoLogin);
				requestDispatcher = request.getRequestDispatcher("adm.jsp");
				requestDispatcher.forward(request, response);
			}
			else{
//				caso contrário, apresenta erro de login e redireciona para a pagina inicial.
				requestDispatcher = request.getRequestDispatcher("erroLogin.html");
				requestDispatcher.forward(request, response);
			}

		} catch (Exception e) {
//			em caso de exceção, mostra mensagem de erro e redireciona para pagina inicial.
			requestDispatcher = request.getRequestDispatcher("erroLogin.html");
			requestDispatcher.forward(request, response);
		}
	}
}
