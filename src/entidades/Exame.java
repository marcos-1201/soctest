package entidades;

public class Exame {
	
	private int codigo;
	private String nome;

	public Exame() {
	}
	
	public Exame(int codigo) {
		this.codigo = codigo;
	}
	
	public Exame(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return "Codigo: " + getCodigo() + ", Nome: " + getNome();
	}
}
