package business;

import java.sql.SQLException;
import java.util.List;

import dao.DaoFactory;
import dao.ExameDao;
import entidades.Exame;
import exceptions.DomainException;

public class ExameBo {

	ExameDao exDao = DaoFactory.getExameDao();

	private int getIdExame() throws SQLException {
		return exDao.getIdExame();
	}
	
	public void criar(Exame exame) throws SQLException {
		nomeEmBranco(exame);
		exame.setCodigo(getIdExame());
		exDao.criar(exame);
	}

	public List<Exame> consultarExames() throws SQLException {
		return exDao.consultarExames();
	}
	
	public void atualizar(Exame exame) throws SQLException {
		nomeEmBranco(exame);
		validarExistenciaDoExame(exame);
		exDao.atualizar(exame);
	}

	public void deletar(Exame exame) throws SQLException {
		verificarSeExameEstaEmResultado(exame);
		exDao.deletar(exame.getCodigo());
	}
	
	public void validarExistenciaDoExame(Exame exame) throws SQLException {
		if (!exDao.existeExamePorId(exame)) {
			throw new DomainException("C�digo de exame informado n�o existe!");
		}
	}
	
	public void verificarSeExameEstaEmResultado(Exame exame) throws SQLException {
		if (exDao.exameExistemEmResultado(exame)) {
			throw new DomainException("Exame est� vinculado com resultado e n�o pode ser deletado!");
		}	
	}
	
	private void nomeEmBranco(Exame exame) {
		if (exame.getNome().isEmpty()) {
			throw new DomainException("Nome do exame n�o pode ser vazio!");
		}
	}
}
