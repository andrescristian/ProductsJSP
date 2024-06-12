<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>	
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="icon" href="img/carrinho-de-compras.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<title>Cadastro de Produtos</title>
</head>
<body>

	<section
		class="container text-center text-dark py-3 bg-success bordasA mt-3">
		<h1>Cadastro de Produtos</h1>
		<a href="novo.html" class="btn btn-dark btn-lg mb-3 mr-3"> <img
			src="img/carrinho.png" alt="Novo produto"> Novo produto
		</a>
		<a href="report" class="btn btn-light btn-lg mb-3 border border-dark">	<!-- report é pra Requisição --> 
			<img src="img/relatoriopdf.png" alt="Relatório em PDF"> Relatório
		</a>

	<table class="table table-striped table-hover">
			<thead class="text-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Produto</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor Unitário</th>
					<th scope="col">Opções</th>
				</tr>
			</thead>
			<tbody class="text-light">

				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<th scope="row"><%=lista.get(i).getId()%></th>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getQuantidade()%></td>
					<td><%=lista.get(i).getPreco()%></td>
					<td>
					<a href="select?id=<%=lista.get(i).getId()%>"
						class="btn btn-light border border-dark"> 
							Editar
					</a>
					<a href="javascript:confirmar(<%= lista.get(i).getId() %>)" class="btn btn-danger"> <!-- Configurar o Link para requisição de nome "select" ao Servlet -->
							Excluir	
					</a></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>

		<p class="pt-3 text-light">&#169; 2024 - Somente para fins
			educativos.</p>
	</section>

	<!-- Links dos Scripts-->
	<script src="scripts/confirmador.js"></script>
	<script src="scripts/jquery.js"></script>
	<script src="scripts/popper.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>