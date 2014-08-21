package br.com.webcad.negocio.curso;

import java.util.ArrayList;

import br.com.webcad.dao.curso.IRepositorioCurso;
import br.com.webcad.dao.curso.RepositorioCurso;

public class ControladorCurso {
	
	private IRepositorioCurso repositorioCurso;
	
	public ControladorCurso(){
		repositorioCurso = new RepositorioCurso();
	}
	
	public String cadastroCurso(Curso curso) {
		String retorno = "";
		try {
			repositorioCurso.cadastrar(curso);
			retorno = "Curso cadastrado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao realizar cadastro! " + e);
			retorno = "Erro ao realizar cadastro!";	
		}
		return retorno;
	}

	public String desabilitarCurso(Curso curso) {
		String retorno = "";
		try {
			repositorioCurso.desabilitar(curso);
			retorno = "Curso desabilitado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao desabilitar curso! " + e);
			retorno = "Erro ao desabilitar curso!";
		}
		return retorno;
	}

	public String editarCurso(Curso curso) {
		String retorno = "";
		try {
			repositorioCurso.editar(curso);
			retorno = "Curso editado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao editar! " + e);
		}
		return retorno;
	}

	public ArrayList<Curso> buscarCurso(String nomeCurso) {
		return repositorioCurso.buscar(nomeCurso);
	}
	
	public ArrayList<Curso> listarCurso(){
		return repositorioCurso.listarCurso();
	}
}
