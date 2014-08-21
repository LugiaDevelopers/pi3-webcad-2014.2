package br.com.webcad.negocio.professor;

import java.util.ArrayList;

import br.com.webcad.dao.professor.IRepositorioProfessor;
import br.com.webcad.dao.professor.RepositorioProfessor;
import br.com.webcad.negocio.administrador.Administrador;

public class ControladorProfessor {

	private IRepositorioProfessor repositorioProfessor;

	public ControladorProfessor() {
		this.repositorioProfessor = new RepositorioProfessor();
	}

	public String cadastraProfessor(Professor prof) {
		String retorno = "";
		try {
			repositorioProfessor.cadastrar(prof);
			retorno = "Professor cadastrado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar! " + e);
			retorno = "Erro ao cadastrar!";
		}

		return retorno;
	}

	public String editarProfessor(Professor prof) {
		String retorno = "";
		try {
			repositorioProfessor.editar(prof);
			retorno = "Professor editado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao editar! " + e);
			retorno = "Erro ao cadastrar!";
		}
		return retorno;
	}

	public String desabilitarProfessor(Professor prof) {
		String retorno = "";
		try {
			repositorioProfessor.desabilitar(prof);
			retorno = "Desabilitado com sucesso!";
		} catch (Exception e) {
			System.out.println("erro ao desabilitar! " + e);
			retorno = "Erro ao desabilitar";

		}
		return retorno;
	}

	public ArrayList<Professor> buscarProfessor(String nomeProfessor) {

		return repositorioProfessor.buscar(nomeProfessor);
	}
	
	public boolean fazerLogin(String email, String senha) {
		
	if(repositorioProfessor.fazerLogin(email, senha)){
			return true;
		}
		return false;
	}
	
	
	public ArrayList<Professor> listarProf() {
		
		return repositorioProfessor.listar();
	}
}
