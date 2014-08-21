package br.com.webcad.dao.curso;

import java.util.ArrayList;

import br.com.webcad.negocio.curso.Curso;

public interface IRepositorioCurso {
	public abstract void cadastrar(Curso curso);
	public abstract void editar(Curso curso);
	public abstract void desabilitar(Curso curso);
	public abstract ArrayList<Curso> buscar(String nomeCurso);
	public abstract ArrayList<Curso> listarCurso();
}
