package business;

import java.sql.SQLException;
import java.util.List;

import dao.DaoFactory;
import dao.PacienteDao;
import entidades.Paciente;
import exceptions.DomainException;

public class PacienteBo {

	PacienteDao pDao = DaoFactory.getPacienteDao();

	private int getIdPaciente() throws SQLException {
		return pDao.getIdPaciente();
	}

	public void criar(Paciente paciente) throws SQLException {
		nomeEmBranco(paciente);
		validarPaciente(paciente);
		paciente.setCodigo(getIdPaciente());
		pDao.criar(paciente);
	}

	public List<Paciente> consultarPacientes() throws SQLException {
		return pDao.consultarPacientes();
	}

	public void atualizar(Paciente paciente) throws SQLException {
		validarExistenciaDoPaciente(paciente);
		nomeEmBranco(paciente);
		pDao.atualizar(paciente);
	}

	public void deletar(Paciente paciente) throws SQLException {
		validarExistenciaDoPaciente(paciente);
		verificarSePacientePossuiResultado(paciente);
		pDao.deletar(paciente.getCodigo());
	}

	private void validarPaciente(Paciente paciente) throws SQLException {
		if (pDao.existePacientePorNome(paciente.getNome())) {
			throw new DomainException("Nome já está cadastrado!");
		}
	}

	private void validarExistenciaDoPaciente(Paciente paciente) throws SQLException {
		if (!pDao.existePacientePorId(paciente)) {
			throw new DomainException("Código informado não existe!");
		}

		if (pDao.existePacienteComMesmoNome(paciente)) {
			throw new DomainException("Nome já está cadastrado!");
		}
	}

	private void verificarSePacientePossuiResultado(Paciente paciente) throws SQLException {
		if (pDao.existeResultadoDoPaciente(paciente)) {
			throw new DomainException("Este paciente tem resultados criados no sistema e não pode ser deletado!");
		}
	}
	
	private void nomeEmBranco(Paciente paciente) {
		if (paciente.getNome().isEmpty()) {
			throw new DomainException("Nome do paciente não pode ser vazio!");
		}
	}
}
