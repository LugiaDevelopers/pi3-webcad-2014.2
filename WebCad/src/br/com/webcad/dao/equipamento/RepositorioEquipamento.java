package br.com.webcad.dao.equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.equipamento.TipoEquipamento;

public class RepositorioEquipamento implements IRepositorioEquipamento {

	private ArrayList<TipoEquipamento> tipoEquipamento = null;

	@Override
	public void cadastrar(Equipamento equip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into equipamento (Id_Curso, Ativo, Nome, Manutencao, Serial, NTombamento, Descricao, DisponivelParaLocacao) value (?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setInt(1, 0);
			comando.setBoolean(2, true);
			comando.setString(3, equip.getTipoEquipamento().getNome());
			comando.setBoolean(4, false);
			comando.setInt(5, equip.getNumeroDeSerie());
			comando.setInt(6, equip.getNumeroTombamento());
			comando.setString(7, equip.getDescricao());
			comando.setBoolean(8, true);

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de equipamento!!!");
		}
	}

	@Override
	public void editar(Equipamento equip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update equipamento set Nome = '"
					+ equip.getTipoEquipamento().getNome() + "', Serial = "
					+ equip.getNumeroDeSerie() + ", NTombamento = "
					+ equip.getNumeroTombamento() + ", Descricao = '"
					+ equip.getDescricao() + "' where Id_Equip = "
					+ equip.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar editar o equipamento!!! ");
		}

	}

	@Override
	public void desabilitar(Equipamento equip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update equipamento set Ativo = " + equip.isAtivo()
					+ " where Id_equip = " + equip.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar desabilitar o equipamento!!! ");
		}

	}

	@Override
	public ArrayList<Equipamento> buscarEquipamento(String nomeEquip) {
		ArrayList<Equipamento> equipEncontrado = new ArrayList<Equipamento>();
		Equipamento equip;
		TipoEquipamento tipoEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento where Nome = '" + nomeEquip
					+ "';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				tipoEquip = new TipoEquipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Nome"), 0, 0);

				equip = new Equipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Descricao"),
						resultado.getInt("NTombamento"),
						resultado.getInt("Serial"),
						resultado.getBoolean("Ativo"),
						resultado.getBoolean("Manutencao"),
						resultado.getBoolean("DisponivelParaLocacao"),
						tipoEquip);

				equipEncontrado.add(equip);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}
		return equipEncontrado;
	}

	@Override
	public String manutencao(ArrayList<Equipamento> equipManutencao) {
		try {
			Connection conexao = ConnectionFactory.createConnection();
			// eventuais alterações poderam ser feitas nesse sql apos falar com
			// o dba!!!!
			for (int i = 0; i < equipManutencao.size(); i++) {
				String sql = "update equipamento set Manutencao = "
						+ equipManutencao.get(i).isAtivo()
						+ " where Id_equip = " + equipManutencao.get(i).getId()
						+ ";";

				PreparedStatement comando = conexao.prepareStatement(sql);

				comando.execute();
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("erro ao tentar inserrir o equipamento equipamento em manutenção!!! ");
		}
		return null;
	}

	@Override
	public void triagem(int quantidade, TipoEquipamento tipoequipamento,
			Curso curso) {
		try {
			Connection conexao = ConnectionFactory.createConnection();
			for (int i = 0; i < quantidade; i++) {
				String sql = "update equipamento set Id_Curso = "
						+ curso.getId() + " where Id_Equip = "
						+ tipoequipamento.getId() + ";";

				PreparedStatement comando = conexao.prepareStatement(sql);

				comando.execute();
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar editar o equipamento!!! ");
		}

	}

	@Override
	public ArrayList<Equipamento> listar() {
		ArrayList<Equipamento> equipEncontrado = new ArrayList<Equipamento>();
		Equipamento equip;
		TipoEquipamento tipoEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				tipoEquip = new TipoEquipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Nome"),
						quantidade(resultado.getString("Nome")),
						quantidadeAlocada(resultado.getString("Nome")));

				equip = new Equipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Descricao"),
						resultado.getInt("NTombamento"),
						resultado.getInt("Serial"),
						resultado.getBoolean("Ativo"),
						resultado.getBoolean("Manutencao"),
						resultado.getBoolean("DisponivelParaLocacao"),
						tipoEquip);

				equipEncontrado.add(equip);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}
		return equipEncontrado;
	}

	@Override
	public ArrayList<TipoEquipamento> listarTipos() {
		ArrayList<TipoEquipamento> equipEncontrado = new ArrayList<TipoEquipamento>();

		TipoEquipamento tipoEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from tipo_equipamento;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				tipoEquip = new TipoEquipamento(
						resultado.getInt("Id_Equip_Tipo"),
						resultado.getString("Nome"),
						quantidade(resultado.getString("Nome")),
						quantidadeAlocada(resultado.getString("Nome")));

				equipEncontrado.add(tipoEquip);
			}

			conexao.close();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		} finally {
			tipoEquipamento = null;
		}

		return equipEncontrado;
	}

	// retornando a quantidade disponivel

	private int quantidade(String nome) {
		int aux = 0;
		for (int i = 0; i < equipamentosDisponiveis().size(); i++) {
			if (equipamentosDisponiveis().get(i).getTipoEquipamento().getNome()
					.equals(nome)) {
				aux++;
			}
		}
		return aux;
	}

	private int quantidadeAlocada(String nome){
		int aux = 0;
		if(tipoEquipamento == null){
			return 0;
		}else{	
		for (int i = 0; i < tipoEquipamento.size(); i++) {
			if(tipoEquipamento.get(i).getNome().equals(nome)){
				aux++;
			}
		}
		}
		return aux;
	}

	@Override
	public void cadastraTipo(Equipamento equip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into tipo_equipamento (nome) value (?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, equip.getTipoEquipamento().getNome());

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de tipo de equipamento!!!");
		}

	}

	@Override
	public ArrayList<Equipamento> equipamentosDisponiveis() {
		ArrayList<Equipamento> equipEncontrado = new ArrayList<Equipamento>();
		Equipamento equip;
		TipoEquipamento tipoEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento where id_curso = 0;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				tipoEquip = new TipoEquipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Nome"), 0, 0);

				equip = new Equipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Descricao"),
						resultado.getInt("NTombamento"),
						resultado.getInt("Serial"),
						resultado.getBoolean("Ativo"),
						resultado.getBoolean("Manutencao"),
						resultado.getBoolean("DisponivelParaLocacao"),
						tipoEquip);

				equipEncontrado.add(equip);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}
		return equipEncontrado;
	}

	@Override
	public ArrayList<Equipamento> equipamentosAlocados(int idCurso) {
		ArrayList<Equipamento> equipEncontrado = new ArrayList<Equipamento>();
		Equipamento equip;
		TipoEquipamento tipoEquip;

		tipoEquipamento = new ArrayList<TipoEquipamento>();
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento where id_curso = "
					+ idCurso + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				tipoEquip = new TipoEquipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Nome"), 0, 0);

				equip = new Equipamento(resultado.getInt("Id_Equip"),
						resultado.getString("Descricao"),
						resultado.getInt("NTombamento"),
						resultado.getInt("Serial"),
						resultado.getBoolean("Ativo"),
						resultado.getBoolean("Manutencao"),
						resultado.getBoolean("DisponivelParaLocacao"),
						tipoEquip);

				tipoEquipamento.add(tipoEquip);
				equipEncontrado.add(equip);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}

		return equipEncontrado;
	}

	@Override
	public void alocarEquipamento(int idCurso, int idEquip, boolean alocado) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update equipamento set Id_Curso = " + idCurso
					+ ",DisponivelParaLocacao = " + alocado
					+ " where Id_Equip = " + idEquip + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar alocar o equipamento!!! ");
		}

	}

	//chamadas para reserva de equipamento!
	
	
}
