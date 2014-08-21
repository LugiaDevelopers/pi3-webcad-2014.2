package br.com.webcad.dao.professor;

import java.util.ArrayList;

import br.com.webcad.negocio.professor.Professor;


public interface IRepositorioProfessor {

	public abstract void cadastrar(Professor prof);
	public abstract void editar(Professor prof);
	public abstract void desabilitar(Professor prof);
	public abstract ArrayList<Professor> buscar(String nomeProfessor);
	public abstract boolean fazerLogin(String email, String senha);
	public abstract ArrayList<Professor> listar();
}
