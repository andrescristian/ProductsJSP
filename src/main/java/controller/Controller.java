package controller;

import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
/*Arquivo de ATIVIDADE PRODUTOS*/
@WebServlet(urlPatterns = {"/main", "/insert", "/select", "/update", "/delete", "/report"})

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans(); //Criado depois da Importação model.JavaBeans;
	
	
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /*Método Principal do Servlet*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		dao.testeConexao();

		//Esta Variável armazena o caminho, usando o request.getServletPath()
		//action vai imprimir o /main, /insert, /select
		String action = request.getServletPath();
		System.out.println(action);
		
		if (action.equals("/main")) {
			produtos(request, response); //Faz requisição (request) e Resposta(response). Herdados das Classes HttpServletRequest
		} else if (action.equals("/insert")) {
			novoProduto(request, response);	//Vou redirecionar o método. Insere Dados
		} else if (action.equals("/select")) {
			listarProduto(request, response); //Encaminhar do /select pro método listarContato(). Faz a Seleção do Dados
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");	//Se não achar nenhuma das ações acima, vai redirecionar sempre ao index.html
		}
		
	}
	

	//contatos==produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");	//index.html vai redirecionar pro arquivo agenda.jsp
		
				//26/03 Criando um Objeto que vai receber os Dados do JavaBeans //Depois do Passo 5
				ArrayList<JavaBeans> lista = dao.listarProdutos();
				
				//Trazer dados de recebimento de lista pro agenda.jsp, vindo do Controller
				request.setAttribute("contatos", lista);	//Setando um Atributo do documento JSP. REQUEST É um objeto de classe modelo
				RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");	//rd vem dos contatos do banco e vai disparar pro arquivo JSP
				rd.forward(request, response);	//Aqui leva todos os dados pro agenda.jsp
				
				//Teste de recebimento da lista no Console
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(lista.get(i).getId());
					System.out.println(lista.get(i).getNome());
					System.out.println(lista.get(i).getQuantidade());
					System.out.println(lista.get(i).getPreco());
					
				}		
		
	}
	
	//novoContato==novoProduto
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Teste de recebimento do Formulário
				System.out.println(request.getParameter("nome"));
				System.out.println(request.getParameter("quantidade"));
				System.out.println(request.getParameter("preco"));
				
				contato.setNome(request.getParameter("nome"));	//Pega o que foi digitado no input e joga dentro da variavel contato JavaBeans
				contato.setQuantidade(request.getParameter("quantidade"));
				contato.setPreco(request.getParameter("preco"));
				
				
				//Invocando o Método "inserirContato()" da Classe DAO.java
				dao.inserirProduto(contato);
				
				response.sendRedirect("main");	//Vai redirecionar para o "main"
	}
	
	
	
	//listarContato==listarProduto
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recebimento do Id do contato que será editado, vindo do input
				String id = request.getParameter("id");	//Pega o Parâmetro
				//System.out.println(idcon); TESTE de recebimento do ID
				
				//Setar a Variável JavaBeans
				contato.setId(id);
				
				//Executar o Método selecionarContato() do DAO.java
				dao.selecionarProduto(contato);
				
				//Teste de Recebimento dos Dados da Tabela
				//System.out.println(contato.getIdcon());
				//System.out.println(contato.getNome());
				//System.out.println(contato.getFone());
				//System.out.println(contato.getEmail());
				
				//Setar(jogar) os atributos do formulário com o conteúdo do JavaBeans 16/04
				request.setAttribute("id", contato.getId());
				request.setAttribute("nome", contato.getNome());
				request.setAttribute("quantidade", contato.getQuantidade());
				request.setAttribute("preco", contato.getPreco());
				
				//Encaminhar (despachar) ao documento editar.jsp	16/04
				RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
				rd.forward(request, response);
		
	}
	
	//Método pra Requisição /update	Aula 16/04
	//editarContato == editarProduto
		protected void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Teste de recebimento de dados no Console
			/*System.out.println(request.getParameter("idcon"));
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("fone"));
			System.out.println(request.getParameter("email"));*/
			
			//Setar as Variáveis do JavaBeans
			contato.setId(request.getParameter("id")); //Vai pegar os dados que estão no input do editar.jsp e jogar nas variáveis do JavaBeans
			contato.setNome(request.getParameter("nome"));
			contato.setQuantidade(request.getParameter("quantidade"));
			contato.setPreco(request.getParameter("preco"));
			
			//Executar o Método alterarContato()	16/04
			dao.alterarProduto(contato); //Relembrar que o contato pega Métodos do JavaBeans
			
			//Voltar para a página agenda.jsp(main) depois de editar e salvar os dados
			response.sendRedirect("main");
		}
	
		//Método pra Requisição /delete	Aula 18/04
		//removerContato == removerProduto
		protected void removerProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String id = request.getParameter("id");
			
			//Teste de recebimento de requisição junto com o ID do Delete, no Console	18/04			
			//System.out.println(idcon);
			
			//Setar a Variável idcon JavaBeans	18/04
			contato.setId(id);
			
			//Executar o Método deletarContato()	18/04
			dao.deletarProduto(contato);
			
			//Voltar para a página agenda.jsp(main)		18/04
			response.sendRedirect("main");
		}
		
		//Método pra Requisição /report. Método responsável por gerar o relatório em PDF		Aula 23/04
		protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Document documento = new Document();	//Aula 23/04
			
			try {
				response.setContentType("apllication/pdf");
				response.addHeader("Content-Diposition", "inline; filename=" + "produtos.pdf");
				//Content-Diposition --> vai gerar um documento com o nome "contatos.pdf"
			
				//A linha de baixo vai criar o documento em PDF		Aula 23/04
				PdfWriter.getInstance(documento, response.getOutputStream());
				
				//Abrir o documento - gerar o conteúdo
				documento.open();
				documento.add(new Paragraph("Lista de Produtos"));	//Esta linha vai criar um parágrafo "Lista de Contatos"
				documento.add(new Paragraph(" "));
				
				//Criar a Tabela
				PdfPTable tabela = new PdfPTable(3); //Tabela com 3 colunas
				
				//Criar o Cabeçalho
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));	//Nome de cada Coluna
				PdfPCell col2 = new PdfPCell(new Paragraph("Quantidade"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Preço"));
				
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				
				//Popular/colocar os contatos na Tabela, reutilizando o Método listarContatos - Dinâmica
				ArrayList<JavaBeans> lista = dao.listarProdutos();
				for (int i = 0; i < lista.size(); i++) {
					tabela.addCell(lista.get(i).getNome());
					tabela.addCell(lista.get(i).getQuantidade());
					tabela.addCell(lista.get(i).getPreco());
				}
				
				documento.add(tabela);			
				documento.close();
							
			} catch (Exception e) {
				System.out.println(e);
				documento.close();
			}
		}
		
}
