/**
 * Validação do Formulário (novo.html)
 */

function validar() {
	let nome = frmContato.nome.value
	let quantidade = frmContato.quantidade.value

	if (nome === "") {
		alert("Preencha o nome do produto")
		frmContato.nome.focus()
	} else if (quantidade === "") {
		alert("Preencha a quantidade")
		frmContato.quantidade.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}