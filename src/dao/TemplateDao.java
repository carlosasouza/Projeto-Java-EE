package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe dao base para todas as daos do projeto.
public abstract class TemplateDao {
	
	private Connection c = null;
	
	// bloco static para registro do Driver
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao registrar Driver");
			e.printStackTrace();
		}
	}
	
	private void setup(){
		c = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sis_pizza_db";
			c = DriverManager.getConnection(url,"root",null);
		} catch (Exception e) {
			System.out.println("Erro ao executar conexão com o banco de dados");
			e.printStackTrace();
		}
	}
	
	protected void executaCadastro(){
		try{
			setup();
			cadastrar(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaAtualizacao(){
		try{
			setup();			
			atualizar(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaAtualizacaoEstado(){
		try{
			setup();			
			atualizarEstado(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaListagem(){
		try{
			setup();
			listar(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaExclusao(){
		try{
			setup();
			excluir(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaBusca(){
		try{
			setup();
			buscar(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaBuscaAtender(){
		try{
			setup();
			buscarAtender(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected void executaLogin(){
		try{
			setup();
			login(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	protected abstract void cadastrar(Connection c) throws Exception;
	
	protected abstract void atualizar(Connection c) throws Exception;
	
	protected abstract void atualizarEstado(Connection c) throws Exception;
	
	protected abstract void listar(Connection c) throws Exception;
	
	protected abstract void excluir(Connection c) throws Exception;
	
	protected abstract void buscar(Connection c) throws Exception;
	
	protected abstract void buscarAtender(Connection c) throws Exception;
	
	protected abstract void login(Connection c) throws Exception;
	
}
