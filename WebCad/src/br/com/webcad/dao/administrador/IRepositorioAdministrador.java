package br.com.webcad.dao.administrador;

import java.util.ArrayList;

import br.com.webcad.negocio.administrador.Administrador;

public interface IRepositorioAdministrador {
	
	public abstract void cadastrar(Administrador adm);
	public abstract void editar(Administrador adm);
	public abstract void desabilitar(Administrador adm);
	public abstract  ArrayList<Administrador> buscar(String nomaAdm);
	public abstract boolean fazerLogin(String email, String senha);
	public abstract ArrayList<Administrador> listar();
}
