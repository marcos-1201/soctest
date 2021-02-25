package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import business.PacienteBo;
import entidades.Paciente;

public class PacienteAction {
	
	private Paciente paciente;
	private String erro;

	@Action(value="/pacienteInclusao", 
			results = {@Result(location = "index.jsp",name = "ok"),@Result(location = "erro.jsp",name = "erro")})
	public String execute() {

		try {
		new PacienteBo().criar(paciente);
		return "ok";
		}
		catch (Exception e) {
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
}
