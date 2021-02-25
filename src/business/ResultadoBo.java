package business;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.DaoFactory;
import dao.ResultadoDao;
import entidades.Resultado;
import exceptions.DomainException;

public class ResultadoBo {

	ResultadoDao reDao = DaoFactory.getResultadoDao();
	ExameBo exBo = new ExameBo();

	private int getIdResultado() throws SQLException {
		return reDao.getIdResultado();
	}

	public void criar(Resultado resultado) throws SQLException {
		resultadoEmBranco(resultado);
		validarDataExame(resultado);
		exBo.validarExistenciaDoExame(resultado.getExame());
		resultado.setCodigo(getIdResultado());
		reDao.criar(resultado);
	}

	public List<Resultado> consultarResultados() throws SQLException {
		return reDao.consultarResultados();
	}
	
	public Resultado consultaPorId(Resultado resultado) throws SQLException {
		return reDao.consultarResultadoPorId(resultado);
	}

	public void atualizar(Resultado resultado) throws SQLException {
		resultadoEmBranco(resultado);
		verificarSeResultadoExistePorId(resultado);
		validarDataExame(resultado);
		reDao.atualizar(resultado);
	}

	public void deletar(Resultado resultado) throws SQLException {
		verificarSeResultadoExistePorId(resultado);
		reDao.deletar(resultado.getCodigo());
	}

	private void validarDataExame(Resultado resultado) {
		if (resultado.getDataExame().before(new Date()) || resultado.getDataExame().equals(new Date())) {
			throw new DomainException("Data do exame só pode ser maior que a data atual.");
		}
	}

	private void verificarSeResultadoExistePorId(Resultado resultado) throws SQLException {
		if (!reDao.existeResultadoPorId(resultado)) {
			throw new DomainException("Não existe resultado com esse código!");
		}
	}
	
	private void resultadoEmBranco(Resultado resultado) {
		if (resultado.getDataExame().toString().isEmpty() || resultado.getResultado().isEmpty()) {
			throw new DomainException("Dados do exame não podem estar em branco(Data/Resultado)");
		} 
	}
}
