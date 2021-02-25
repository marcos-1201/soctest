package entidades;

import java.sql.SQLException;

import business.PacienteBo;

public class Paciente {

	private int codigo;
	private String nome;

	PacienteBo pBo = new PacienteBo();

	public Paciente() {
	}

	public Paciente(int codigo) {
		this.codigo = codigo;
	}

	public Paciente(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws SQLException {
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
