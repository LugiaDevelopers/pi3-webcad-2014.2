package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.curso.Curso;

@ManagedBean(name = "cursoBean")
@SessionScoped
public class CursoBean {

	
	private Curso curso;
	private IFachada fachada;
	private ArrayList<Curso> cursos;
	
	public CursoBean(){
		curso = new Curso();
		fachada = Fachada.getInstancia();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public ArrayList<Curso> getCursos() {
		return fachada.listarCurso();
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
	public String salvar(){
		if(curso.getId() >0){
			editarCurso();
		}else{
			inserirCurso();
		}
		return "cadastro_curso";
		
	}
	
	public void desabilitar(Curso curso){
		fachada.desabilitar(curso);
		cursos = null;
		curso = new Curso();
		
	}
	
	public String editar(Curso curso){
		this.curso = curso;
		return "cadastro_curso";
	}
	
	private void inserirCurso(){
		fachada.cadastrar(curso);
		
		cursos = null;
		curso = new Curso();
	}
	
	private void editarCurso(){
		fachada.editar(curso);
		
		cursos = null;
		curso = new Curso();
	}
	
	
	
	
}
