package br.com.webcad.dao.reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.equipamento.TipoEquipamento;
import br.com.webcad.negocio.reserva.Reserva;

public class RepositorioReserva implements IRepositorioReserva{

	@Override
	public void cadastrar(Reserva reserva) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into reserva (Id_Equip, Id_user, Equipamento, Datareserva) values (?,?,?,?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setInt(1, reserva.getEquipamento().getId());
			comando.setInt(2, reserva.getUsuario().getId());
			comando.setString(3, reserva.getEquipamento().getTipoEquipamento().getNome());
			comando.setString(4,  reserva.getData());	
			
			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro a reserva!!!");
		}		
	}

	@Override
	public String desisteReserva(ArrayList<Equipamento> equipamento) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			for (int i = 0; i < equipamento.size(); i++) {
				String sql = "update reserva set Id_user = null where Id_equip = "+equipamento.get(i).getId()+";";

				PreparedStatement comando = conexao.prepareStatement(sql);

				comando.execute();
			}
			
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar dessistir da reserva!!! ");
		}

		return null;
	}

	@Override
	public ArrayList<Reserva> buscarEquipamentosDisponiveis(String nome,
			String curso, String data) {
		
		ArrayList<Reserva> equipamentos = new ArrayList<Reserva>();
		ArrayList<Reserva> equipamentoDisponiveis = new ArrayList<Reserva>();
		
		Reserva reserva;
		Equipamento equip;
		TipoEquipamento tEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from equipamento where id_curso = "+curso+" and nome = '"+nome+"';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();
			
			while (resultado.next()) {
				tEquip = new TipoEquipamento(resultado.getInt("Id_Equip"), resultado.getString("nome"), 0, 0);
				equip = new Equipamento(resultado.getInt("Id_Equip"), resultado.getString("Descricao"), resultado.getInt("NTombamento"), resultado.getInt("Serial"), resultado.getBoolean("Ativo"), resultado.getBoolean("Manutencao"), resultado.getBoolean("DisponivelParaLocacao"), tEquip);
				reserva = new Reserva(0, equip, null);
				
				equipamentos.add(reserva);

			}
			
			sql = "select * from reserva where Datareserva = '"+data+"';";

			comando = conexao.prepareStatement(sql);

			resultado = comando.executeQuery();
			
			while (resultado.next()) {
				tEquip = new TipoEquipamento(resultado.getInt("Id_Equip"), resultado.getString("Equipamento"), 0, 0);
				equip = new Equipamento(resultado.getInt("Id_Equip"), null, 0, 0, false, false, false, tEquip);
				reserva = new Reserva(0, equip, null);
				
				equipamentoDisponiveis.add(reserva);

			}

			for (int j = 0; j < equipamentoDisponiveis.size(); j++) {
				
				for (int i = 0; i < equipamentos.size(); i++){
					if(equipamentos.get(i).getEquipamento().getId() == equipamentoDisponiveis.get(j).getEquipamento().getId()){
						equipamentos.remove(i);
					}
				
				}
			}
			
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}
		return equipamentos;
	}
	@Override
	public void efetuarReserva(int idEquip, String email, String nome, String data) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into reserva (Id_Equip, Id_user, Equipamento, Datareserva) values (?,?,?,?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setInt(1, idEquip);
			comando.setInt(2, idUser(email));
			comando.setString(3, nome);
			comando.setString(4,  data);	
			
			comando.execute();
			
			sql = "update equipamento set ativo = 0 where Id_Equip = "+idEquip+";";

			comando = conexao.prepareStatement(sql);
			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro a reserva!!!");
		}		

	}
	
	public int idUser(String email){
		int numero = 0;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Email = '"+email+"';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				numero = resultado.getInt("Id_user");
						}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar id user !!!!");
		}
		
		return numero;
	}

	@Override
	public ArrayList<Reserva> verificarReservas(String email) {
		ArrayList<Reserva> equipamentoReservados = new ArrayList<Reserva>();
		Reserva reserva;
		Equipamento equip;
		TipoEquipamento tEquip;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from reserva, Equipamento where reserva.Id_user = "+idUser(email)+" and equipamento.Id_Equip = reserva.Id_Equip;";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				tEquip = new TipoEquipamento(resultado.getInt("Id_Equip"), resultado.getString("nome"), 0, 0);
				System.out.println(resultado.getString("nome"));
				equip = new Equipamento(resultado.getInt("Id_Equip"), resultado.getString("Descricao"), resultado.getInt("NTombamento"), resultado.getInt("Serial"), resultado.getBoolean("Ativo"), resultado.getBoolean("Manutencao"), resultado.getBoolean("DisponivelParaLocacao"), tEquip);
				reserva = new Reserva(resultado.getInt("Id_reserva"), equip, resultado.getString("Datareserva"));
				
				equipamentoReservados.add(reserva);

			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar equipamento !!!!");
		}
		return equipamentoReservados;
	}

	@Override
	public void desistirDaReserva(int idReserva) {
		int numero = 0;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "delete from reserva where Id_reserva = "+idReserva+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			
			
			sql = "select * from reserva where Id_reserva = "+idReserva+";";

			comando = conexao.prepareStatement(sql);
			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {

				numero = resultado.getInt("Id_equip");
						}

			
			
			sql = "update equipamento set ativo = 1 where Id_Equip = "+numero+";";

			comando = conexao.prepareStatement(sql);

			comando.execute();
			
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao desistir da reserva !!!!");
		}
	}

	
	
	
}
