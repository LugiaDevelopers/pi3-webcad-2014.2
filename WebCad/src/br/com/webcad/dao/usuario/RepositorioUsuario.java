package br.com.webcad.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.webcad.dao.ConnectionFactory;

public class RepositorioUsuario implements IRepositorioUsuario {

	@Override
	public boolean verificaEmail(String email) {
		boolean existeEmail = false;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where email = '" + email + "';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				existeEmail = true;
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar email !!!!");
		}

		return existeEmail;
	}

	@Override
	public boolean verificaMatricula(int matricula) {
		boolean existeMatricula = false;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where matricula = " + matricula
					+ ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				existeMatricula = true;
			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar email !!!!");
		}

		return existeMatricula;
	}

	@Override
	public boolean verificaEmailDeterminado(String email, int id) {
		boolean existeEmailDeterminado = false;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Email = '"+email+"' and id_user = "+id+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				existeEmailDeterminado = true;
			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar existeEmailDeterminado !!!!");
		}

		return existeEmailDeterminado;
	}

	@Override
	public boolean verificaMatriculaDeterminada(int matricula, int id) {
		boolean existeMatriculaDeterminada = false;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where matricula = "+matricula+" and id_user = "+id+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				existeMatriculaDeterminada = true;
			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar email !!!!");
		}

		return existeMatriculaDeterminada;
	}

}
