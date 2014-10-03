package br.com.webcad.negocio.usuario;

import br.com.webcad.dao.administrador.IRepositorioAdministrador;
import br.com.webcad.dao.administrador.RepositorioAdministrador;
import br.com.webcad.dao.coordenador.IRepositorioCoordenador;
import br.com.webcad.dao.coordenador.RepositorioCoordenador;
import br.com.webcad.dao.professor.IRepositorioProfessor;
import br.com.webcad.dao.professor.RepositorioProfessor;
import br.com.webcad.dao.usuario.IRepositorioUsuario;
import br.com.webcad.dao.usuario.RepositorioUsuario;

public class ControladorUsuario {

	private IRepositorioProfessor repositorioProfessor;
	private IRepositorioAdministrador repositorioAdministrador;
	private IRepositorioCoordenador repositorioCoordenador;
	private IRepositorioUsuario repositorioUsuario;
	
	public ControladorUsuario(){
		this.repositorioAdministrador = new RepositorioAdministrador();
		this.repositorioCoordenador = new RepositorioCoordenador();
		this.repositorioProfessor = new RepositorioProfessor();
		this.repositorioUsuario = new RepositorioUsuario();
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
	
	

	public  boolean verificaEmail(String email){
		
		return repositorioUsuario.verificaEmail(email);
	}

	public  boolean verificaMatricula(int matricula){
		return repositorioUsuario.verificaMatricula(matricula);
	}
	
	public  boolean verificaEmailDeterminado(String email, int id){
		return repositorioUsuario.verificaEmailDeterminado(email, id);
	}
	
	public  boolean verificaMatriculaDeterminada(int matricula, int id){
		return repositorioUsuario.verificaMatriculaDeterminada(matricula, id);
	}
}
