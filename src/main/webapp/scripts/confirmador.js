/**
 * Confirmador de exclusão de dados
 */

function confirmar(id){
	let resposta = confirm("Confirmar a exclusão deste produto?")
	if(resposta === true){
		window.location.href = "delete?id=" + id	//Encaminhar a requisição ao Servlet
		//window.location	--> Usado para redirecionar ao Servlet. Sair desse documento e ir para outro local, que no caso é pra requisição ao Servlet
	}
}