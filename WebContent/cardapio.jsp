<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="model.Pizza"%>
<%@page import="model.Bebida"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%
// Instancias dos objetos e recuperação das lista da sessão.
	List<Pizza> listaPizzas = (List<Pizza>) session.getAttribute("listaPizzas");
	List<Bebida> listaBebidas = (List<Bebida>) session.getAttribute("listaBebidas");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>...:::Cardápio:::...</title>
</head>
<body>
	<center>
		<table border="1px" width="768px">
			<tr>
				<td colspan="5"><h3><center>Opções de Pizzas</center></h3></td>
			</tr>
			<tr>
				<td><center><b>Nome</b><center></td>
				<td><center><b>Tipo</b><center></td>
				<td><center><b>Massa</b><center></td>
				<td><center><b>Ingredientes</b><center></td>
				<td><center><b>Preço</b><center></td>
			</tr>
			<%
// 			Iteração da lista de pizzas do cardapio e exibição na página jsp.
				for (Pizza pizza : listaPizzas) {
					out.println("<tr>");
					out.println("<td>" + pizza.getNome() + "</td>");
					out.println("<td>" + pizza.getTipoPizza() + "</td>");
					out.println("<td>" + pizza.getTipoMassa() + "</td>");
					out.println("<td>" + pizza.getIngredientes() + "</td>");
					out.println("<td>" + new DecimalFormat("#,###.00").format(pizza.getPreco()) + "</td>");
					out.println("</tr>");
				}
			%>
		</table>
		<table border="1px" width="768px">
			<tr>
				<td colspan="2"><h3><center>Opções de Bebidas</center></h3></td>
			</tr>
			<tr>
				<td><center><b>Nome</b><center></td>
				<td><center><b>Preço</b><center></td>
			</tr>
			<%
// 			Iteração da lista de bebidas do cardapio e exibição na página jsp.
				for (Bebida bebida : listaBebidas) {
					out.println("<td>" + bebida.getNome() + "</td>");
					out.println("<td>" + new DecimalFormat("#,###.00").format(bebida.getPreco()) + "</td>");
				}
			%>
			<tr>
				<td colspan="2"><center><a onclick="window.history.back()" href="">Voltar</a></center></td>
			<tr>
		</table>		
	<center>
</body>
</html>