package dao;

public class DaoFactory {

	private static ExameDao exameDao = new ExameDao();
	private static ResultadoDao resultadoDao = new ResultadoDao();
	private static PacienteDao pacienteDao = new PacienteDao();
	
	public static ExameDao getExameDao() {
		return exameDao;
	}
	
	public static ResultadoDao getResultadoDao() {
		return resultadoDao;
	}
	
	public static PacienteDao getPacienteDao() {
		return pacienteDao;
	}
}
