package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Exame;

public class ExameDao {

	private Connection conn;
	PreparedStatement stmt = null;
	
	public ExameDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void criar(Exame exame) throws SQLException {
		try {
			String sql = "INSERT INTO `soctest`.`exame`(`idexame`,`nome`) VALUES(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, exame.getCodigo());
			stmt.setString(2, exame.getNome());
			stmt.executeUpdate();
			}
			finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		}
	
	public List<Exame> consultarExames() throws SQLException {

		List<Exame> exames = new ArrayList<>();

		StringBuilder qry = new StringBuilder();
		qry.append("SELECT * FROM exame");

		try {

			stmt = conn.prepareStatement(qry.toString());

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int codigoExame = rs.getInt("idexame");
				String nome = rs.getString("nome");
				Exame ex = new Exame(codigoExame, nome);
				exames.add(ex);
			}
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return exames;
	}
	
	public void atualizar(Exame exame) throws SQLException {
		try {
		String sql = "UPDATE `soctest`.`exame` SET `nome` = ? WHERE `idexame` = ?" ;
				
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, exame.getNome());
		stmt.setInt(2, exame.getCodigo());
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
		String sql = "DELETE FROM `soctest`.`exame` WHERE idexame = ?";
				
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
	
	public int getIdExame() throws SQLException {

		int codigoExame = 0;
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT max(idexame)+1 AS proximoID FROM exame");

		try {

			stmt = conn.prepareStatement(qry.toString());

			ResultSet rs = stmt.executeQuery();
			rs.next();

			codigoExame = rs.getInt("proximoID");
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return codigoExame;
	}
	
	public Boolean existeExamePorId(Exame exame) throws SQLException {
		StringBuilder qry = new StringBuilder();
		qry.append("SELECT idexame FROM exame ");
		qry.append("where idexame = ?");

		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, exame.getCodigo());

			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} 
		finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Boolean exameExistemEmResultado(Exame exame) throws SQLException {
		StringBuilder qry = new StringBuilder() ;
		qry.append("SELECT * FROM soctest.paciente_exame where cd_exame = ?");
		
		try {
			stmt = conn.prepareStatement(qry.toString());
			stmt.setInt(1, exame.getCodigo());

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
