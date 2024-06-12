<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
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
	<section class="container text-center text-success py-3 bg-dark bordasA mt-3">
		<h1>Editar produto</h1>
		
		<form name="frmContato" class="py-3" action="update">
			<div class="col-auto">		
				<input type="text" name="id" class="form-control mb-2" id="inlineFormInput" readonly value="<%out.print(request.getAttribute("id"));%>"> <!-- readonly obriga a não editar, só leitura -->
				<input type="text" name="nome" class="form-control mb-2" id="inlineFormInput" value="<%out.print(request.getAttribute("nome"));%>" >
				<input type="text" name="quantidade" class="form-control mb-2" id="inlineFormInput" value="<%out.print(request.getAttribute("quantidade"));%>" >
				<input type="text" name="preco" class="form-control mb-2" id="inlineFormInput" value="<%out.print(request.getAttribute("preco"));%>">
				
				<input type=button value="Salvar" class="btn btn-outline-success btn-lg  mt-3" onclick="validar()">				
			</div>
		</form>

		<p class="pt-3 text-light">&#169; 2024 - Somente para fins
			educativos.</p>
	</section>
	
		
	<!-- Links dos Scripts-->
	<script src="scripts/validador.js"></script>
	<script src="scripts/jquery.js"></script>
	<script src="scripts/popper.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>