<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.Cliente"%>
<%
//Instancia do cliente e recupera��o do objeto da sess�o.
	final Cliente cliente = (Cliente) session.getAttribute("cliente");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>...:::�rea do Usu�rio:::...</title>
</head>
<body>
	<center>
		<h2>BEM VINDO AO SISTEMA DE PIZZARIA!</h2>
		<p>
<!-- 		Exibi��o no nome do usu�rio logado. -->
			Conectado como: <% out.println(cliente.getNome()); %>
		</p>
		<h3>Menu</h3>
		<p>
			<a href="CardapioServlet">Card�pio</a>
		</p>
		<p>
			<a href="NovoPedidoServlet">Novo Pedido</a>
		</p>
		<p>
			<a href="ListaPedidosClienteServlet">Lista de Pedidos</a>
		</p>
		<p>
			<a href="LogoutServlet">Sair</a>
		</p>
	</center>
</body>
</html>