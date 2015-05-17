package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Endereco;
import dao.ClienteDao;

/**
 * Servlet de cadastro de clientes
 */
public class CadastroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

//	Objeto e atributo usados na classe.
	RequestDispatcher requestDispatcher;
	Endereco endereco = new Endereco();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		cadastro de cliente com os dados capturados pelo request do formulario.
		if(request.getParameter("cadastro").equalsIgnoreCase("cliente")){
			
			Cliente cliente = new Cliente();
			ClienteDao clienteDao = new ClienteDao();
			
			endereco.setLogradouro(request.getParameter("txt_logradouro"));
			endereco.setBairro(request.getParameter("txt_bairro"));
			endereco.setNumeroCep(Integer.parseInt(request.getParameter("txt_cep")));
			endereco.setCidade(request.getParameter("txt_cidade"));
			endereco.setUnidadeFederativa(request.getParameter("txt_uf"));
			cliente.setEndereco(endereco);
			cliente.setNome(request.getParameter("txt_nome"));
			cliente.setNumeroRg(request.getParameter("txt_rg"));
			cliente.setOrgaoEmissorRg(request.getParameter("txt_orgaoEmissor"));
			cliente.setNumeroCpf(request.getParameter("txt_cpf"));
			cliente.setNumeroTelefone(request.getParameter("txt_telefone"));
			cliente.setSenha(request.getParameter("txt_senha"));
			
			try {
//				caso o cadastro tenha sucesso, sera redirecionaddo para tela principal para login aopos exibição de mensagem.
				if(clienteDao.cadastrar(cliente)){
					requestDispatcher = request.getRequestDispatcher("cadastroSucesso.html");
					requestDispatcher.forward(request, response);
				}
				else{
//					em caso de erro no cadastro, exibe mensagem e redireciona para pagina inical do sistema.
					requestDispatcher = request.getRequestDispatcher("erroCadastro.html");
					requestDispatcher.forward(request, response);
				}
			} catch (Exception e) {
//				em caso de exceção no cadastro, exibe mensagem e redireciona para pagina inical do sistema.
				requestDispatcher = request.getRequestDispatcher("erroCadastro.html");
				requestDispatcher.forward(request, response);
			}
			
		}
//		cadastro de funcionário.
		else if(request.getParameter("cadastro").equalsIgnoreCase("funcionario")){
			
			//Funcionario funcionario = new Funcionario();
			//FuncionarioDao FuncionarioDao = new FuncionarioDao();
			
		}
	}

}
