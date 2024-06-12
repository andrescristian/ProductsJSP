package model;

public class JavaBeans {
	private String id;
	private String nome;
	private String quantidade;
	private String preco;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String id, String nome, String quantidade, String preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
		
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}		
}
