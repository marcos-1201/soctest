package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.Exame;
import entidades.Paciente;
import entidades.Resultado;

public class ResultadoDao {

	private Connection conn;
	PreparedStatement stmt = null;

	public ResultadoDao() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public void criar(Resultado resultado) throws SQLException {
		try {
			StringBuilder qry = new StringBuilder();
			
			qry.append("INSERT INTO `soctest`.`paciente_exame` (");
			qry.append("`idpaciente_exame`,`cd_paciente`,");
			qry.append("`cd_exame`,`data`,`resultado`) ");
			qry.append(
					"VALUES (?,?,?,?,?)");
			//				 1,2,3,4,5	
			
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, resultado.getCodigo());
			stmt.setInt(2, resultado.getPaciente().getCodigo());
			stmt.setInt(3, resultado.getExame().getCodigo());
			stmt.setDate(4, new java.sql.Date(resultado.getDataExame().getTime()));
			stmt.setString(5, resultado.getResultado());
			stmt.executeUpdate();
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Resultado consultarResultadoPorId(Resultado resultado) throws SQLException {

		Resultado retorno = null;

		StringBuilder qry = new StringBuilder();
		qry.append("SELECT p.idpaciente AS ID_PACIENTE,");
		qry.append("p.nome AS NOME_PACIENTE,");
		qry.append("e.idexame AS ID_EXAME,");
		qry.append("e.nome AS NOME_EXAME,");
		qry.append("pe.data AS DATA_EXAME,");
		qry.append("pe.resultado AS RESULTADO, ");
		qry.append("pe.idpaciente_exame AS ID_RESULTADO ");
		qry.append("FROM ");
		qry.append("paciente_exame pe INNER JOIN ");
		qry.append("paciente p ");
		qry.append("ON pe.cd_paciente = p.idpaciente ");
		qry.append("INNER JOIN exame e ");
		qry.append("ON pe.cd_exame = e.idexame ");
		qry.append("WHERE pe.idpaciente_exame = ?");

		try {

			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, resultado.getCodigo());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int codigoPaciente = rs.getInt("ID_PACIENTE");
				String nomePaciente = rs.getString("NOME_PACIENTE");
				int codigoExame = rs.getInt("ID_EXAME");
				String nomeExame = rs.getString("NOME_EXAME");
				Date dataExame = new Date(rs.getDate("DATA_EXAME").getTime());
				String r = rs.getString("RESULTADO");
				int codigoResultado = rs.getInt("ID_RESULTADO");
				Paciente p = new Paciente(codigoPaciente, nomePaciente);
				Exame e = new Exame(codigoExame, nomeExame);
				retorno = new Resultado(p, e, dataExame, r);
				retorno.setCodigo(codigoResultado);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return retorno;
	}

	public List<Resultado> consultarResultados() throws SQLException {

		List<Resultado> listaResultado = new ArrayList<>();

		StringBuilder qry = new StringBuilder();
		qry.append("SELECT p.idpaciente AS ID_PACIENTE,");
		qry.append("p.nome AS NOME_PACIENTE,");
		qry.append("e.idexame AS ID_EXAME,");
		qry.append("e.nome AS NOME_EXAME,");
		qry.append("pe.data AS DATA_EXAME,");
		qry.append("pe.resultado AS RESULTADO, ");
		qry.append("pe.idpaciente_exame AS ID_RESULTADO ");
		qry.append("FROM ");
		qry.append("paciente_exame pe INNER JOIN ");
		qry.append("paciente p ");
		qry.append("ON pe.cd_paciente = p.idpaciente ");
		qry.append("INNER JOIN exame e ");
		qry.append("ON pe.cd_exame = e.idexame ");
		qry.append("ORDER BY p.nome, e.nome");

		try {

			stmt = conn.prepareStatement(qry.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int codigoPaciente = rs.getInt("ID_PACIENTE");
				String nomePaciente = rs.getString("NOME_PACIENTE");
				int codigoExame = rs.getInt("ID_EXAME");
				String nomeExame = rs.getString("NOME_EXAME");
				Date dataExame = new Date(rs.getDate("DATA_EXAME").getTime());
				String resultado = rs.getString("RESULTADO");
				int codigoResultado = rs.getInt("ID_RESULTADO");
				Paciente p = new Paciente(codigoPaciente, nomePaciente);
				Exame e = new Exame(codigoExame, nomeExame);
				Resultado r = new Resultado(p, e, dataExame, resultado);
				r.setCodigo(codigoResultado);
				listaResultado.add(r);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return listaResultado;
	}
	
	public void atualizar(Resultado resultado) throws SQLException {
		try {
			StringBuilder qry = new StringBuilder();
			
			qry.append("UPDATE `soctest`.`paciente_exame` ");
			qry.append("SET `data` = ?,`resultado` = ? ");
			qry.append("WHERE `idpaciente_exame` = ?");

			stmt = conn.prepareStatement(qry.toString());
			
			stmt.setDate(1, new java.sql.Date(resultado.getDataExame().getTime()));
			stmt.setString(2, resultado.getResultado());
			stmt.setInt(3, resultado.getCodigo());
			
			stmt.executeUpdate();
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deletar(int id) throws SQLException {
		try {
		String sql = "DELETE FROM `soctest`.`paciente_exame` WHERE idpaciente_exame = ?";
				
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		}
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public int getIdResultado() throws SQLException {
		
		int codigoResultado = 0;
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT max(idpaciente_exame)+1 AS proximoID FROM paciente_exame");

		try {

			stmt = conn.prepareStatement(qry.toString());

			ResultSet rs = stmt.executeQuery();
			rs.next();

			codigoResultado = rs.getInt("proximoID");
			return codigoResultado;
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Boolean existeResultadoPorId(Resultado resultado) throws SQLException {
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT * FROM soctest.paciente_exame ");
		qry.append("where idpaciente_exame = ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, resultado.getCodigo());

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
