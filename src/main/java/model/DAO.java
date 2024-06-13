package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "";
	private String user = "";
	private String password = "";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void inserirProduto(JavaBeans contato) {
		String create = "insert into Cadastro(nome, quantidade, preco) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getQuantidade());
			pst.setString(3, contato.getPreco());
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<JavaBeans> listarProdutos(){
		String read = "select * from Cadastro order by nome ";
		
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);	
			ResultSet rs = pst.executeQuery();
			 
			while(rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String quantidade = rs.getString(3);
				String preco = rs.getString(4);
			
				contatos.add(new JavaBeans(id, nome, quantidade, preco));
			}
			con.close();
			return contatos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void selecionarProduto(JavaBeans contato) {
		String read2 = "select * from Cadastro where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setQuantidade(rs.getString(3));
				contato.setPreco(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	public void alterarProduto(JavaBeans contato){
		String create = "update Cadastro set nome=?, quantidade=?,preco=? where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getQuantidade());
			pst.setString(3, contato.getPreco());
			pst.setString(4, contato.getId());			
			pst.executeUpdate();			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletarProduto(JavaBeans contato) {
		String delete = "delete from Cadastro where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getId());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
