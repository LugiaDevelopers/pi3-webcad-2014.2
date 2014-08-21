package br.com.webcad.negocio.administrador;

import java.util.ArrayList;

import br.com.webcad.dao.administrador.IRepositorioAdministrador;
import br.com.webcad.dao.administrador.RepositorioAdministrador;

public class ControladorAdministrador {

	private IRepositorioAdministrador repositorioAdministrador;

	public ControladorAdministrador() {
		this.repositorioAdministrador = new RepositorioAdministrador();
	}

	public String cadastrarAdministrador(Administrador adm) {
		String retorno = "";
		try {
			repositorioAdministrador.cadastrar(adm);
			retorno = "Cadastrado com Sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao Cadastrar" + e);
			retorno = "Erro ao cadastrar!";
		}

		return retorno;
	}

	public String editarAdministrador(Administrador adm) {
		String retorno = "";
		try {
			repositorioAdministrador.editar(adm);
			retorno = "Editado com Sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao Cadastrar!" + e);
			retorno = "Erro ao cadastrar!";
		}
		return retorno;
	}

	public String desabilitarAdministrador(Administrador adm) {
		String retorno = "";
		try {
			repositorioAdministrador.desabilitar(adm);
			retorno = "Desabilitado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao desabilitar!" + e);
			retorno = "Erro ao desabilita!";
		}
		return retorno;
	}

	public ArrayList<Administrador> buscarAdministrador(String nomeAdm) {

		return repositorioAdministrador.buscar(nomeAdm);
	}

	public ArrayList<Administrador> listar() {

		return repositorioAdministrador.listar();
	}

	public boolean fazerLogin(String email, String senha) {
		// chamar validação

		if (repositorioAdministrador.fazerLogin(email, senha) == true) {
			return true;
		}

		return false;
	}
}
