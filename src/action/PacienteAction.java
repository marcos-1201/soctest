package action;

import java.sql.SQLException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import business.PacienteBo;
import entidades.Paciente;
import exceptions.DomainException;

public class PacienteAction {

	private Paciente paciente;
	private String erro;
	private PacienteBo pBo = new PacienteBo();

	@Action(value="/paciente", 
			results = {@Result(location = "paciente.jsp",name = "ok"),@Result(location = "erro.jsp",name = "erro")})
	public String execute() {

		try {
		paciente = pBo.consultaPorId(paciente);
		return "ok";
		}
		catch (Exception e) {
			erro = e.getMessage();
			return "erro";
		}
	}
	
	@Action(value = "/criarPaciente", results = {
			@Result(name = "ok", type = "redirectAction", params = { "actionName", "/" }) })
	public String cria() throws SQLException {

		try {
			new PacienteBo().criar(paciente);
			return "ok";
		} catch (DomainException e) {
			erro = e.getMessage();
			return "erro";
		}
	}

	@Action(value = "/atualizarPaciente", results = {
			@Result(name = "ok", type = "redirectAction", params = { "actionName", "/" }) })
	public String atualizaPaciente() throws SQLException {

		try {
			new PacienteBo().atualizar(paciente);
			return "ok";
		} catch (DomainException e) {
			erro = e.getMessage();
			return "erro";
		}
	}
	
	@Action(value = "/excluirPaciente", results = { 
			@Result(name = "ok", type = "redirectAction", params = {"actionName", "/"}) })
	public String excluirResultado() throws SQLException {
		try {
		new PacienteBo().deletar(paciente);
		return "ok";
		} catch (DomainException e) {
			e.printStackTrace();
			erro = e.getMessage();
			return "erro";
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getErro() {
		return erro;
	}
}
