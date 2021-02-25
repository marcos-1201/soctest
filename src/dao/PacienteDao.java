package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Paciente;

public class PacienteDao {

	private Connection conn;

	public PacienteDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	PreparedStatement stmt = null;

	public void criar(Paciente paciente) throws SQLException {
		try {
			String sql = "INSERT INTO `soctest`.`paciente`(`idpaciente`,`nome`) VALUES(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, paciente.getCodigo());
			stmt.setString(2, paciente.getNome());
			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Paciente consultaPorId(Paciente paciente) throws SQLException {
		Paciente retorno = null;
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT p.idpaciente AS IDPACIENTE, ");
		qry.append("p.nome AS NOME FROM paciente p ");
		qry.append("WHERE p.idpaciente like ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, paciente.getCodigo());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int codigoPaciente = rs.getInt("IDPACIENTE");
				String nome = rs.getString("NOME");
				retorno = new Paciente(codigoPaciente, nome);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return retorno;
	}

	public List<Paciente> consultarPacientes() throws SQLException {
		List<Paciente> listaPacientes = new ArrayList<>();

		StringBuilder qry = new StringBuilder();
		qry.append("SELECT * FROM paciente ORDER BY nome");

		try {

			stmt = conn.prepareStatement(qry.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("idpaciente");
				String nome = rs.getString("nome");
				listaPacientes.add(new Paciente(codigo, nome));
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return listaPacientes;
	}

	public void atualizar(Paciente paciente) throws SQLException {
		try {
			String sql = "UPDATE `soctest`.`paciente` SET `nome` = ? WHERE `idpaciente` = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paciente.getNome());
			stmt.setInt(2, paciente.getCodigo());
			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void deletar(int id) throws SQLException {
		try {
			String sql = "DELETE FROM `soctest`.`paciente` WHERE idpaciente = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public int getIdPaciente() throws SQLException {

		int codigoPaciente = 0;
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT max(idpaciente)+1 AS proximoID FROM paciente");

		try {

			stmt = conn.prepareStatement(qry.toString());

			ResultSet rs = stmt.executeQuery();
			rs.next();

			codigoPaciente = rs.getInt("proximoID");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return codigoPaciente;
	}

	public Boolean existePacientePorId(Paciente paciente) throws SQLException {
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT idpaciente FROM paciente ");
		qry.append("where idpaciente = ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, paciente.getCodigo());

			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public Boolean existePacientePorNome(String nome) throws SQLException {
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT nome FROM paciente ");
		qry.append("where nome like ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public Boolean existePacienteComMesmoNome(Paciente paciente) throws SQLException {
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT nome FROM paciente ");
		qry.append("where nome = ? and idpaciente <> ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setString(1, paciente.getNome());
			stmt.setInt(2, paciente.getCodigo());
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public Boolean existeResultadoDoPaciente(Paciente paciente) throws SQLException {
		String qry = "SELECT * FROM soctest.paciente_exame where cd_paciente = ?";

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, paciente.getCodigo());

			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
}
