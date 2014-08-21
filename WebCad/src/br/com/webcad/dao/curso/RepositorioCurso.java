package br.com.webcad.dao.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.curso.Curso;

public class RepositorioCurso implements IRepositorioCurso {

	@Override
	public void cadastrar(Curso curso) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			
			String sql = "insert into curso (NomeCurso, Ativo) value (?,?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, curso.getNome());
			comando.setBoolean(2, true);

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de curso!!!");
		}

	}

	@Override
	public void editar(Curso curso) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update curso set NomeCurso = '"+curso.getNome()+"' where Id_Curso = "+curso.getId()+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar editar o curso!!! ");
		}

	}

	@Override
	public void desabilitar(Curso curso) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update curso set Ativo = false where Id_Curso = "+curso.getId()+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar desabilitar!!! ");
		}

	}

	@Override
	public ArrayList<Curso> buscar(String nomeCurso) {
		ArrayList<Curso> cursoEncontrado = new ArrayList<Curso>();
		Curso curso;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from curso where NomeCurso = '" + nomeCurso
					+ "';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				// podera sofrer alterações de tipo apos falar com o dba!!

				curso = new Curso(resultado.getInt("Id_Curso"),
						resultado.getString("NomeCurso"));
				cursoEncontrado.add(curso);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar curso !!!!");
		}
		return cursoEncontrado;
	}

	@Override
	public ArrayList<Curso> listarCurso() {
		ArrayList<Curso> cursoEncontrado = new ArrayList<Curso>();
		Curso curso;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from curso;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				
				curso = new Curso(resultado.getInt("Id_Curso"),
						resultado.getString("NomeCurso"));
				cursoEncontrado.add(curso);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar curso !!!!");
		}
		return cursoEncontrado;
	}

}
