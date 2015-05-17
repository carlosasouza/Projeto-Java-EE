package testes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Endereco;
import model.Funcionario;
import dao.ClienteDao;
import dao.FuncionarioDao;

//Classe de teste de alguns modelos e daos.
public class Teste {

	public static void main(String[] args) throws Exception {
		
		testeCadastroFuncionario();
	}
	
	public static void testeBuscaFuncionario() throws Exception{
		Funcionario funcionario = new Funcionario(null, null, null, "17938651168", null, null, null, null, null);
		
		FuncionarioDao dao = new FuncionarioDao();
		
		System.out.println("Funcionario: "+dao.buscar(funcionario));
	}
	
	public static void testeExclusaoFuncionario() throws Exception{
		
		Endereco e = new Endereco("Quadra 300 Conjunto 99 Casa 13", "Setor Oeste", 72231420, "Guará", "Goiás");
		Funcionario funcionario = new Funcionario("Maria da Dores e Silva", "7411209", "SSPDF", "17938651168", "33761028", e, "123456789", 996, "Cozinheiro");
		
		FuncionarioDao dao = new FuncionarioDao();
		
		System.out.println(dao.excluir(funcionario) ? "Exclusão de funcionario realizado com sucesso!" : "Erro ao excluir funcionario!");
	}
	
	public static void testeListagemFuncionario() throws Exception{
		
		FuncionarioDao dao = new FuncionarioDao();
		
		List<Funcionario> funcionario  = dao.listar();
		
		if(funcionario.size() > 0){
			for (int i = 0; i < funcionario.size(); i++) {
				System.out.println(funcionario.get(i).toString());
			}		
		}
	}
	
	public static void testeAtualizacaoFuncionario() throws Exception{
		Endereco e = new Endereco("Quadra 300 Conjunto 99 Casa 13", "Setor Oeste", 72231420, "Guará", "Goiás");
		
		Funcionario funcionario = new Funcionario("Maria da Dores e Silva", "7411209", "SSPDF", "17938651168", "33761028", e, null, 996, "Cozinheiro");
		
		FuncionarioDao dao = new FuncionarioDao();
		
		System.out.println(dao.atualizar(funcionario) ? "Funcionario atualizado com sucesso!" : "Erro ao atualizar funcionario!");
	}
	
	public static void testeCadastroFuncionario() throws Exception{
		Endereco e = new Endereco("Rua 10 Casa 06", "Zona Sul", 720369741, "Samambaia", "Distrito Federal");
		
		Funcionario funcionario = new Funcionario("Maria da Dores", "7411209", "SSPDF", "17938651168", "33761028", e, "123456789", 996, "Cozinheiro");
		
		FuncionarioDao dao = new FuncionarioDao();
		
		System.out.println(dao.cadastrar(funcionario) ? "Cadastro de funcionario realizado com sucesso!" : "Erro ao cadastrar funcionario!");
	}
	
	public static void testeCadastroCliente() throws Exception{
		Endereco e = new Endereco("Quadra 15 Conjunto H Lote 11", "Setor Oeste", 72963450, "Guará", "Distrito Federal");
		
		Cliente c = new Cliente("Carlos André de Souza", "2644249", "SSPDF", "02529061106", "85569972", e, "200389" ,new SimpleDateFormat("dd/MM/yyyy").format(new Date()), null);
		
		ClienteDao dao = new ClienteDao();
		
		System.out.println(dao.cadastrar(c) ? "Cadastro de cliente realizado com sucesso!" : "Erro ao cadastrar cliente!");
	}
	
	public static void testeAtualizacaoCliente() throws Exception{
		Endereco e = new Endereco("Quadra 05 Conjunto 05 Casa 12", "Setor oeste", 72231420, "Guará", "Distrito Federal");
		Cliente c = new Cliente("Carlos André de Souza", "2644249", "SSPDF", "02529061106", "33775539", e, null ,new SimpleDateFormat("dd/MM/yyyy").format(new Date()), null);
		
		ClienteDao dao = new ClienteDao();
		
		System.out.println(dao.atualizar(c) ? "Cliente atualizado com sucesso!" : "Erro ao atualizar cliente!");
	}
	
	public static void testeListagemCliente() throws Exception{
		
		ClienteDao dao = new ClienteDao();
		
		List<Cliente> clientes  = dao.listar();
		
		if(clientes.size() > 0){
			
			for (int i = 0; i < clientes.size(); i++) {
				System.out.println(clientes.get(i).toString());
			}		
		}
		
	}
	
	public static void testeExclusaoCliente() throws Exception{
		Cliente c = new Cliente("Claudiana Gomes de Souza", "1839431", "SSPDF", "94622868172", "33775539", null, null, new SimpleDateFormat("dd/MM/yyyy").format(new Date()), null);
		
		ClienteDao dao = new ClienteDao();
		
		System.out.println(dao.excluir(c) ? "Exclusão de cliente realizado com sucesso!" : "Erro ao excluir cliente!");
	}
	
	public static void testeBuscaCliente() throws Exception{
		Cliente c = new Cliente(null, null, null, "94622868172", null, null, null,null, null);
		
		ClienteDao dao = new ClienteDao();
		
		System.out.println("Cliente: "+dao.buscar(c));
	}

}
