package br.com.webcad.dao.administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.administrador.Administrador;

public class RepositorioAdministrador implements IRepositorioAdministrador {

	@Override
	public void cadastrar(Administrador adm) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "insert into usuario (Nome, Email, Tipo, Senha, Matricula, Ativo) values (?,?,?,?,?,?);";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, adm.getNome());
			comando.setString(2, adm.getEmail());
			comando.setString(3, "administrador");
			comando.setString(4, adm.getSenha());
			comando.setInt(5, Integer.parseInt(adm.getMatricula()));
			comando.setBoolean(6, true);

			comando.execute();

			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de administrador!!!");
		}

	}

	@Override
	public void editar(Administrador adm) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update usuario set Nome = '" + adm.getNome()
					+ "', Email = '" + adm.getEmail() + "', Tipo = '"
					+ adm.getTipo() + "', Senha = '" + adm.getSenha()
					+ "', Matricula = " + adm.getSenha() + " where Id_user = "
					+ adm.getId() + ";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar editar o administrador!!! ");
		}

	}

	@Override
	public void desabilitar(Administrador adm) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "update usuario set Ativo = false where Id_user = "+adm.getId()+";";

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.execute();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao tentar desabilitar!!! ");
		}

	}

	@Override
	public ArrayList<Administrador> buscar(String nomaAdm) {
		ArrayList<Administrador> admEncontrado = new ArrayList<Administrador>();
		Administrador adm;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Nome = '" + nomaAdm
					+ "';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				

				adm = new Administrador(resultado.getInt("Id_user"),
						resultado.getString("Nome"), 
						resultado.getString("Senha"),
						resultado.getString("Email"),
						resultado.getString("Tipo"), 
					""+ resultado.getInt("Matricula"));
				admEncontrado.add(adm);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar administrador !!!!");
		}
		return admEncontrado;
	}

	@Override
	public boolean fazerLogin(String email, String senha) {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Email = '" + email
					+ "' and Senha = '" + senha + "' and tipo = 'administrador';";

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
	public ArrayList<Administrador> listar() {
		ArrayList<Administrador> admEncontrado = new ArrayList<Administrador>();
		Administrador adm;
		try {
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where tipo = 'administrador';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				

				adm = new Administrador(resultado.getInt("Id_user"),
						resultado.getString("Nome"), 
						resultado.getString("Senha"),
						resultado.getString("Email"),
						resultado.getString("Tipo"), 
					""+ resultado.getInt("Matricula"));
				admEncontrado.add(adm);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar administrador !!!!");
		}
		return admEncontrado;
	}

}
