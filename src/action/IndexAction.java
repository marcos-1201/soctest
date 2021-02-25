package action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import business.PacienteBo;
import business.ResultadoBo;
import entidades.Paciente;
import entidades.Resultado;

public class IndexAction {

	private List<Paciente> pacientes;
	private List<Resultado> resultados;
	private String erro;

	@Action(value="/", 
			results = {@Result(location = "index.jsp",name = "ok"),@Result(location = "erro.jsp",name = "erro")})
	public String execute() {

		try {
		resultados = new ResultadoBo().consultarResultados();
		pacientes = new PacienteBo().consultarPacientes();
		return "ok";
		}
		catch (Exception e) {
			erro = e.getMessage();
			return "erro";
		}
	}

	public List<Resultado> getResultados() {
		return resultados;
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public String getErro() {
		return erro;
	}
}
