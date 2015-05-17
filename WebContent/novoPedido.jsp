<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="model.Pizza"%>
<%@page import="model.Bebida"%>
<%@page import="model.Funcionario"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%
// Instancais das listas de objetos recuperados da sessão.
	List<Pizza> listaPizzas = (List<Pizza>) session.getAttribute("listaPizzas");
	List<Bebida> listaBebidas = (List<Bebida>) session.getAttribute("listaBebidas");
	List<Funcionario> listaFuncionarios = (List<Funcionario>) session.getAttribute("listaFuncionarios");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>...:::Novo Pedido:::...</title>
</head>
<body>
	<center>
		<form action="PedidoServlet" method="post">
			<table border="1px">
				<tr>
					<td colspan="3"><h2><center>Novo Pedido</center></h2></td>
				</tr>
				<tr>
					<td colspan="3"><h3><center>Selecione a sua pizza abaixo:</center></h3></td>
				</tr>
				<tr>
					<td colspan="3">
						<select name="sel_pizza">
		  					<option selected="selected" value="0" >Selecione...</option>
		  					<%
// 		  					Iteração da lista de pizzas e exibição na jsp.
								for (int i = 0; i < listaPizzas.size(); i++) {
									out.println("<option value='"+listaPizzas.get(i).getNumero()+"'>"+listaPizzas.get(i).getNome()+" "+new DecimalFormat("#,###.00").format(listaPizzas.get(i).getPreco())+"</option>");
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td><center>Quantidade de Pizzas:</center></td>
					<td colspan="2"><center><input type="text" name="txt_qtd_pizza"></center></td>
				</tr>
				<tr>
					<td colspan="3"><h3><center>Selecione o pizzaiolo de preferência:</center></h3></td>
				</tr>
				<tr>
					<td colspan="3">
						<select name="sel_funcionario">
		  					<option selected="selected" value="0" >Selecione...</option>
		  					<%
// 		  					Iteração da lista de funcionários e exibição na jsp.
								for (int i = 0; i < listaFuncionarios.size(); i++) {
									out.println("<option value='"+listaFuncionarios.get(i).getNumeroCpf()+"'>"+listaFuncionarios.get(i).getNome()+"</option>");
								}
							%>
						</select>
					</td>
				</tr>				
				<tr>
					<td colspan="3"><h3><center>Selecione a sua bebida abaixo:</center></h3></td>
				</tr>
				<tr>
					<td colspan="3">
						<select name="sel_bebida">
		  					<option selected="selected" value="0">Selecione...</option>
		  					<%
// 		  					Iteração da lista de babidas e exibiçção na jsp.
								for (int i = 0; i < listaBebidas.size(); i++) {
									out.println("<option value='"+listaBebidas.get(i).getNumero()+"'>"+listaBebidas.get(i).getNome()+" "+new DecimalFormat("#,###.00").format(listaBebidas.get(i).getPreco())+"</option>");
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td><center>Quantidade de Bebidas:</center></td>
					<td colspan="2"><center><input type="text" name="txt_qtd_bebida"></center></td>	
				</tr>
				<tr>
					<td><center>Observações:</center></td>
					<td colspan="2"><center><input type="text" name="txt_obs"></center></td>	
				</tr>
				<tr>
					<td><center><a href="CardapioServlet">Cardápio</a></center></td>
					<td><center><input type="submit" name="btn_pedido" value="Efetuar Pedido"></center></td>
					<td><center><a onclick="window.history.back()" href="">Voltar</a></center></td>	
				</tr>
			</table>
		</form>
	</center>
</body>
</html>