package action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import business.ExameBo;
import business.ResultadoBo;
import entidades.Exame;
import entidades.Resultado;
import exceptions.DomainException;

public class ResultadoAction {

	private List<Exame> exames;
	private List<Resultado> resultados;
	private Resultado resultado;
	private String erro;
	private ResultadoBo reBo = new ResultadoBo();

	@Action(value="/resultado", 
			results = {@Result(location = "resultado.jsp",name = "ok"),@Result(location = "erro.jsp",name = "erro")})
	public String execute() {

		try {
		exames = new ExameBo().consultarExames();
		resultados = reBo.consultarResultados();
		resultado = reBo.consultaPorId(resultado);
		return "ok";
		}
		catch (Exception e) {
			erro = e.getMessage();
			return "erro";
		}
	}
	
	@Action(value = "/salvaResultadoAlteracao",
			results = { @Result(name = "ok", type = "redirectAction", params = {"actionName", "/"}) })
	public String atualizaResultado() throws SQLException {
		try {
		new ResultadoBo().atualizar(resultado);
		return "ok";
		} catch (DomainException e) {
			e.printStackTrace();
			erro = e.getMessage();
			return "erro";
		}
	}
	
	@Action(value = "/excluirResultado",
			results = { @Result(name = "ok", type = "redirectAction", params = {"actionName", "/"}) })
	public String excluirResultado() throws SQLException {
		try {
		new ResultadoBo().deletar(resultado);
		return "ok";
		} catch (DomainException e) {
			e.printStackTrace();
			erro = e.getMessage();
			return "erro";
		}
	}

	public List<Resultado> getResultados() {
		return resultados;
	}
	
	public Resultado getResultado() {
		return resultado;
	}
	
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public List<Exame> getExames() {
		return exames;
	}

	public String getErro() {
		return erro;
	}
}
