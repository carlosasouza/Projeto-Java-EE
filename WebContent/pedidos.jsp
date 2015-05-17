<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="model.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%
//Instancais da lista de objeto recuperado da sessão.
	List<Pedido> listaPedidos = (List<Pedido>) session.getAttribute("listaPedidos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>...:::Pedidos:::...</title>
</head>
<body>
	<center>
			<table border="1px" width="768px">
				<tr>
					<td colspan="9"><h3><center>Lista de Pedidos</center></h3></td>
				</tr>
				<tr>
					<td><center><b>Numero</b><center></td>
					<td><center><b>Data/Hora</b><center></td>
					<td><center><b>Quantidade de Pizzas</b><center></td>
					<td><center><b>Pizza</b><center></td>
					<td><center><b>Quantidade de Bebidas</b><center></td>
					<td><center><b>Bebida</b><center></td>
					<td><center><b>Observação</b><center></td>
					<td><center><b>Valor</b><center></td>
					<td><center><b>Situação</b><center></td>
				</tr>
				<%
// 				Iteração da lista de pedidos do usuário.
					for (Pedido pedido : listaPedidos) {
						out.println("<tr>");
						out.println("<td>" + pedido.getNumero() + "</td>");
						out.println("<td>" + pedido.getDataPedido() + " - " + pedido.getHoraPedido()+ "</td>");
						out.println("<td>" + pedido.getQuantidadePizza() + "</td>");
						out.println("<td>" + pedido.getPizza().getNome() + "</td>");
						out.println("<td>" + pedido.getQuantidadeBebida() + "</td>");
						out.println("<td>" + pedido.getBebida().getNome() + "</td>");
						out.println("<td>" + pedido.getObservacao() + "</td>");
						out.println("<td>" + new DecimalFormat("#,###.00").format(pedido.getValorPedido()) + "</td>");
						out.println("<td>" + pedido.getSituacao() + "</td>");
						out.println("</tr>");
					}
				%>
				<tr>
					<td colspan="9"><center><a href="home.jsp">Voltar</a></center></td>
				<tr>
			</table>
	</center>		
</body>
</html>