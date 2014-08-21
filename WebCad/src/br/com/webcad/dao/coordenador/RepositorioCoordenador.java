package br.com.webcad.dao.coordenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.webcad.dao.ConnectionFactory;
import br.com.webcad.negocio.coordenador.Coordenador;

public class RepositorioCoordenador implements IRepositorioCoordenador{

	@Override
	public String cadastrarCoordenador() {
		try {
			Connection conexao = ConnectionFactory.createConnection();

			
			// esse script sql deve ser alterado posteriormente apos tirar a
			// duvida do id altoincrementavel e da senha varchar
			String sql = "select * from usuario where Tipo = 'coordenador';";
			System.out.println("entrou no repositorio");
			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();
				
			if(resultado.first() == false){
				System.out.println("passou aqui");
				sql = "insert into usuario (Nome, Email, Tipo, Senha, Matricula, Ativo) values ('Dota','dota@bol.com','coordenador','1234', 666999666, true);";
				comando = conexao.prepareStatement(sql);
				comando.execute();
			}
		
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no cadastro de administrador!!!");
		}
		return "Use Email: dota@bol.com & Senha: 1234 para logar no sistema";
	}

	@Override
	public boolean fazerLogin(String email, String senha) {
		try {
			System.out.println("entrou no repositorio fazer login coordenador");
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "select * from usuario where Email = '" + email
					+ "' and Senha = '" + senha + "' and tipo = 'coordenador';";

			PreparedStatement comando = conexao.prepareStatement(sql);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				if (email.equals(resultado.getString("Email"))
						&& senha.equals(resultado.getString("Senha"))) {
					System.out.println("entrou no validador true");
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

}
