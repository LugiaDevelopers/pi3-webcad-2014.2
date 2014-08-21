package br.com.webcad.negocio.usuario;

import br.com.webcad.dao.administrador.IRepositorioAdministrador;
import br.com.webcad.dao.administrador.RepositorioAdministrador;
import br.com.webcad.dao.coordenador.IRepositorioCoordenador;
import br.com.webcad.dao.coordenador.RepositorioCoordenador;
import br.com.webcad.dao.professor.IRepositorioProfessor;
import br.com.webcad.dao.professor.RepositorioProfessor;

public class ControladorUsuario {

	private IRepositorioProfessor repositorioProfessor;
	private IRepositorioAdministrador repositorioAdministrador;
	private IRepositorioCoordenador repositorioCoordenador;
	
	public ControladorUsuario(){
		this.repositorioAdministrador = new RepositorioAdministrador();
		this.repositorioCoordenador = new RepositorioCoordenador();
		this.repositorioProfessor = new RepositorioProfessor();
	}
	
	public boolean fazerLogin(String email, String senha) {
		// chamar validação
		
		if(repositorioAdministrador.fazerLogin(email, senha) == true){
			return true;
		}
		if(repositorioCoordenador.fazerLogin(email, senha) == true){
			return true;
		}
		if(repositorioProfessor.fazerLogin(email, senha)){
			return true;
		}
		return false;
	}
}
