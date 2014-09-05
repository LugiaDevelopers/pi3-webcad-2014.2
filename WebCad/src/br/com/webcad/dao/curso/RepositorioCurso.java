package br.com.webcad.dao.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

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

			String sql = "update curso set NomeCurso = '" + curso.getNome()
					+ "' where Id_Curso = " + curso.getId() + ";";

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
		
		// preencher uma lista de equipamentos com os que estao vinculados ao
		// curso!
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		Equipamento equipamento;
		TipoEquipamento tipoEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento where id_curso = "
					+ curso.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				tipoEquip = new TipoEquipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Nome"), 0, 0);

				equipamento = new Equipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Descricao"),
						resultado.getInt("NTombamento"),
						resultado.getInt("Serial"),
						resultado.getBoolean("Ativo"),
						resultado.getBoolean("Manutencao"),
						resultado.getBoolean("DisponivelParaLocacao"),
						tipoEquip);

				equipamentos.add(equipamento);
			}

			// apagando reservas referente ao curso a ser deletado
			
			for (int i = 0; i < equipamentos.size(); i++) {
				sql = "delete from reserva where id_equip = "
						+ equipamentos.get(i).getId() + ";";
				comando = conexao.prepareStatement(sql);
				comando.execute();
			}

			// desalocar equipamento para aquele curso
			for (int i = 0; i < equipamentos.size(); i++) {
				sql = "update equipamento set id_curso = 0, DisponivelParaLocacao = 1 where id_equip = "
						+ equipamentos.get(i).getId() + ";";
				comando = conexao.prepareStatement(sql);
				comando.execute();
			}
			
			// deletando curso
			sql = "delete from curso where id_curso = " + curso.getId() + ";";
			comando = conexao.prepareStatement(sql);
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
