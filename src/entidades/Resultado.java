package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resultado {

	private int codigo;
	private Paciente paciente;
	private Exame exame;
	private Date dataExame;
	private String resultado;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Resultado() {
	}
	
	public Resultado(int codigo) {
		this.codigo = codigo;
	}

	public Resultado(Paciente paciente, Exame exame, Date dataExame) {
		this.paciente = paciente;
		this.exame = exame;
		this.dataExame = dataExame;
	}

	public Resultado(Paciente paciente, Exame exame, Date dataExame, String resultado) {
		this.paciente = paciente;
		this.exame = exame;
		this.dataExame = dataExame;
		this.resultado = resultado;
	}
	
	public Resultado(int codigo, Date dataExame, String resultado) {
		this.codigo = codigo;
		this.dataExame = dataExame;
		this.resultado = resultado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Date getDataExame() {
		return dataExame;
	}

	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String toString() {
		return "Paciente: " + getPaciente().getNome() + "| Exame: " + getExame().getNome() + "| Data do exame: "
				+ sdf.format(this.dataExame) + "| Resultado: " + this.resultado;
	}
}
