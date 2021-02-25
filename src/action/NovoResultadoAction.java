package action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import business.ExameBo;
import business.PacienteBo;
import business.ResultadoBo;
import entidades.Exame;
import entidades.Paciente;
import entidades.Resultado;
import exceptions.DomainException;

public class NovoResultadoAction {

	private List<Exame> exames;
	private List<Paciente> pacientes;
	private Resultado resultado;
	private String erro;

	@Action(value = "/novo", results = { @Result(location = "novoResultado.jsp", name = "ok") })
	public String execute() throws SQLException {
		try {
			exames = new ExameBo().consultarExames();
			pacientes = new PacienteBo().consultarPacientes();
			return "ok";
		} catch (Exception e) {
			erro = e.getMessage();
			return "erro";
		}
	}

	@Action(value = "/salvaResultadoInclusao", results = {
			@Result(name = "ok", type = "redirectAction", params = { "actionName", "/" }) })
	public String criaResultado() throws SQLException {
		try {
			new ResultadoBo().criar(resultado);
			return "ok";
		} catch (DomainException e) {
			erro = e.getMessage();
			return "erro";
		}
	}

	public List<Exame> getExames() {
		return exames;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getErro() {
		return erro;
	}
}
