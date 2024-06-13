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

@WebServlet(urlPatterns = {"/main", "/insert", "/select", "/update", "/delete", "/report"})

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
	
    public Controller() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao.testeConexao();
		String action = request.getServletPath();
		System.out.println(action);
		
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/insert")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}	
	}
	
	protected void produtos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<JavaBeans> lista = dao.listarProdutos();
				
			request.setAttribute("contatos", lista);
			RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
			rd.forward(request, response);
				
			for (int i = 0; i < lista.size(); i++) {
				System.out.println(lista.get(i).getId());
				System.out.println(lista.get(i).getNome());
				System.out.println(lista.get(i).getQuantidade());
				System.out.println(lista.get(i).getPreco());	
			}		
	}
	
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("quantidade"));
			System.out.println(request.getParameter("preco"));
				
			contato.setNome(request.getParameter("nome"));
			contato.setQuantidade(request.getParameter("quantidade"));
			contato.setPreco(request.getParameter("preco"));
				
			dao.inserirProduto(contato);				
			response.sendRedirect("main");
	}

	protected void listarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");	
			contato.setId(id);
			dao.selecionarProduto(contato);
			
			request.setAttribute("id", contato.getId());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("quantidade", contato.getQuantidade());
			request.setAttribute("preco", contato.getPreco());
	
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);
	}
	
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			contato.setId(request.getParameter("id"));
			contato.setNome(request.getParameter("nome"));
			contato.setQuantidade(request.getParameter("quantidade"));
			contato.setPreco(request.getParameter("preco"));
			
			dao.alterarProduto(contato);
			response.sendRedirect("main");
		}
	
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			contato.setId(id);
			dao.deletarProduto(contato);
			response.sendRedirect("main");
		}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Document documento = new Document();
			
			try {
				response.setContentType("apllication/pdf");
				response.addHeader("Content-Diposition", "inline; filename=" + "produtos.pdf");
			
				PdfWriter.getInstance(documento, response.getOutputStream());
		
				documento.open();
				documento.add(new Paragraph("Lista de Produtos"));
				documento.add(new Paragraph(" "));
				
				PdfPTable tabela = new PdfPTable(3);
				
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Quantidade"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Preço"));
				
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				
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
