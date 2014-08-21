package br.com.webcad.dao.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.professor.Professor;

public class RepositorioProfessor implements IRepositorioProfessor {

	@Override
	public void cadastrar(Professor prof) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into usuario (Nome, Email, Tipo, Senha, Matricula, Ativo) values (?,?,?,?,?,?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, prof.getNome());
			comando.setString(2, prof.getEmail());
			comando.setString(3, "professor");
			comando.setString(4, prof.getSenha());
			comando.setInt(5, Integer.parseInt(prof.getMatricula()));
			comando.setBoolean(6, true);

			comando.execute();

			sql = "select * from usuario where tipo = 'professor';";
			comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.last()) {
				sql = "insert into professor (Id_user, pontos) values(?,?);";
				comando = conexao.prepareStatement(sql);
				comando.setInt(1, resultado.getInt("Id_user"));
				comando.setInt(2, 30);
				break;
			}

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de professor!!!");
		}

	}

	@Override
	public void editar(Professor prof) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update usuario set Nome = '" + prof.getNome()
					+ "', Email = '" + prof.getEmail() + "', Tipo = '"
					+ prof.getTipo() + "', Senha = '" + prof.getSenha()
					+ "', Matricula = " + prof.getSenha() + " where Id_user = "
					+ prof.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			sql = "update professor set pontos = " + prof.getPontos()
					+ " where Id_user = " + prof.getId() + ";";

			comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar editar o professor!!! ");
		}

	}

	@Override
	public void desabilitar(Professor prof) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update usuario set Ativo = false where Id_user = "
					+ prof.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar desabilitar!!! ");
		}

	}

	@Override
	public ArrayList<Professor> buscar(String nomeProfessor) {
		ArrayList<Professor> profEncontrado = new ArrayList<Professor>();
		Professor prof;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from professor, usuario where nome = '"
					+ nomeProfessor + "';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				prof = new Professor(resultado.getInt("Id_user"),
						resultado.getString("Nome"),
						resultado.getString("Senha"),
						resultado.getString("Email"),
						resultado.getString("Tipo"), ""
								+ resultado.getInt("Matricula"),
						resultado.getInt("pontos"));

				profEncontrado.add(prof);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar professor !!!!");
		}
		return profEncontrado;
	}

	@Override
	public boolean fazerLogin(String email, String senha) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Email = '" + email
					+ "' and Senha = '" + senha + "' and tipo = 'professor';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				if (email.equals(resultado.getString("Email"))
						&& senha.equals(resultado.getString("Senha"))) {
					return true;
				} else {
					return false;
				}

			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no metodo de fazer login!!! ");
		}
		return false;
	}

	@Override
	public ArrayList<Professor> listar() {

		ArrayList<Professor> profEncontrado = new ArrayList<Professor>();
		Professor prof;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from professor join usuario where professor.Id_user = usuario.Id_user ;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				prof = new Professor(resultado.getInt("Id_user"),
						resultado.getString("Nome"),
						resultado.getString("Senha"),
						resultado.getString("Email"),
						resultado.getString("Tipo"), ""
								+ resultado.getInt("Matricula"),
						resultado.getInt("pontos"));

				profEncontrado.add(prof);

			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar professor !!!!");
		}
		return profEncontrado;
	}

}
