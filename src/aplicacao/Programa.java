package aplicacao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import business.ExameBo;
import business.PacienteBo;
import business.ResultadoBo;
import entidades.Exame;
import entidades.Paciente;
import entidades.Resultado;
import exceptions.DomainException;

public class Programa {

	public static void main(String[] args) throws SQLException {

		PacienteBo pBo = new PacienteBo();
		ExameBo exBo = new ExameBo();
		ResultadoBo reBo = new ResultadoBo();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		try {

			// Criar paciente

			System.out.print("Informe o nome do paciente: ");
			String nomePaciente = sc.nextLine();
			Paciente p = new Paciente();
			p.setNome(nomePaciente);
			pBo.criar(p);

			// Consultar paciente
			for (Paciente paciente : pBo.consultarPacientes()) {
				System.out.println(paciente.toString());
			}

			// Criar exame
			System.out.println();
			System.out.print("Informe o nome do exame: ");
			String nomeExame = sc.nextLine();
			Exame ex = new Exame();
			ex.setNome(nomeExame);
			exBo.criar(ex);

			System.out.println();

			// Consultar exames
			for (Exame exame : exBo.consultarExames()) {
				System.out.println(exame.toString());
			}

			System.out.print("Informe a data do exame(dd/MM/yyyy): ");
			Date dataExame = sdf.parse(sc.nextLine());

			System.out.print("Informe o resultado: ");
			String resultado = sc.nextLine();

			Resultado re = new Resultado(p, ex, dataExame, resultado);
			reBo.criar(re); // cria resultado

			// Lista resultados
			System.out.println();
			for (Resultado result : reBo.consultarResultados()) {
				System.out.println(result.toString() + "| Código sequencial: " + result.getCodigo());
			}

			// Atualizar paciente
			System.out.println();
			System.out.print("Informe o código do paciente: ");
			int codigoPaciente = sc.nextInt();
			sc.nextLine();
			System.out.print("Informe o nome atualizado: ");
			String nomeAtualizadoPaciente = sc.nextLine();
			pBo.atualizar(new Paciente(codigoPaciente, nomeAtualizadoPaciente));

			// Consultar paciente
			for (Paciente paciente : pBo.consultarPacientes()) {
				System.out.println(paciente.toString());
			}

			// deletar paciente
			System.out.println();
			System.out.print("Informe o código do paciente para deletar: ");
			int codigo = sc.nextInt();
			sc.nextLine();
			Paciente pac = new Paciente(codigo);
			pBo.deletar(pac);

			// Consultar paciente

			System.out.println();
			for (Paciente paciente : pBo.consultarPacientes()) {
				System.out.println(paciente.toString());
			}

			System.out.println();

			// Consultar exames
			for (Exame exame : exBo.consultarExames()) {
				System.out.println(exame.toString());
			}

			// Atualizar exame
			System.out.println();
			System.out.print("Informe o código do exame: ");
			int codigoExame = sc.nextInt();
			sc.nextLine();
			System.out.print("Informe o nome do exame: ");
			String nomeAtualizadoExame = sc.nextLine();
			exBo.atualizar(new Exame(codigoExame, nomeAtualizadoExame));

			// Consultar exames
			System.out.println();
			for (Exame exame : exBo.consultarExames()) {
				System.out.println(exame.toString());
			}

			// deletar exame
			System.out.println();
			System.out.print("Informe o código do exame para deletar: ");
			codigo = sc.nextInt();
			sc.nextLine();
			ex = new Exame(codigo);
			exBo.deletar(ex);

			// Lista resultados
			System.out.println();
			for (Resultado result : reBo.consultarResultados()) {
				System.out.println(result.toString());
			}

			// Atualizar resultado
			System.out.println();
			System.out.print("Informe o código do resultado que será alterado: ");
			codigo = sc.nextInt();
			sc.nextLine();

			System.out.print("Informe a data do exame(dd/MM/yyyy): ");
			dataExame = sdf.parse(sc.nextLine());

			System.out.print("Informe o resultado: ");
			resultado = sc.nextLine();
			reBo.atualizar(new Resultado(codigo, dataExame, resultado));

			// Lista resultados
			System.out.println();
			for (Resultado result : reBo.consultarResultados()) {
				System.out.println(result.toString());
			}

			// deletar resultado
			System.out.println();
			System.out.print("Informe o código do resultado que deseja deletar: ");
			codigo = sc.nextInt();
			sc.nextLine();
			reBo.deletar(new Resultado(codigo));

			// Lista resultados
			System.out.println();
			for (Resultado result : reBo.consultarResultados()) {
				System.out.println(result.toString());
			}

		} catch (DomainException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Data digitada é inválida!");
		}
		sc.close();

	}
}
