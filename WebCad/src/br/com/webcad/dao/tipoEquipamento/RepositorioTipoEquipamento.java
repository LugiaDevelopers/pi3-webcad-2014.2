package br.com.webcad.dao.tipoEquipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

public class RepositorioTipoEquipamento implements IRepositorioTipoEquipamento {

	@Override
	public void cadastrar(TipoEquipamento tipoEquip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into tipo_equipamento (nome) values (?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, tipoEquip.getNome());

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de tipo de equipamento!!!");
		}
	}

	@Override
	public void editar(TipoEquipamento tipoEquip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update tipo_equipamento set nome = '"
					+ tipoEquip.getNome() + "' where id_equip_tipo = "
					+ tipoEquip.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("erro ao tentar editar o tipo do equipamento!!! ");
		}

	}

	@Override
	public void deletar(TipoEquipamento tipoEquip) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "delete from tipo_equipamento where id_equip_tipo = "
					+ tipoEquip.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("erro ao tentar excluir tipo do equipamento!!! ");
		}
	}

	@Override
	public ArrayList<TipoEquipamento> listar() {

		ArrayList<TipoEquipamento> equipEncontrado = new ArrayList<TipoEquipamento>();
		TipoEquipamento tipoEquip;

		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from tipo_equipamento ;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				tipoEquip = new TipoEquipamento(
						resultado.getInt("id_equip_tipo"),
						resultado.getString("nome"), 0, 0);
				
				equipEncontrado.add(tipoEquip);
			}
			
			conexao.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return equipEncontrado;
	}
}
