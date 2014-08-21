package br.com.webcad.managedBean;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.administrador.Administrador;
import br.com.webcad.negocio.professor.Professor;

@ManagedBean(name = "professorBean")
@SessionScoped
public class ProfessorBean {

	
	
	private Professor professor;
	private IFachada fachada;
	private ArrayList<Professor> professores;

	public ProfessorBean() {
		professor = new Professor();
		fachada = Fachada.getInstancia();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ArrayList<Professor> getProfessores() {
		return fachada.listarProf();
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}
	
	
	public String salvar() {
		if(professor.getId() >0){
			editarProfessor();
		}else{
			inserirProfessor();
		}
		

		return "cadastro_professor";
	}
	
	
public void desabilitar(Professor professor) {
		
		fachada.desabilitar(professor);
		professores = null;
		professor = new Professor();
		System.out.println("test");
	}

	public String editar(Professor professor){
		this.professor = professor;
		return "cadastro_professor";
				
		
	}
	
	private void inserirProfessor() {

		fachada.cadastrar(professor);

		professores = null;
		professor = new Professor();

	}

	private void editarProfessor(){
		
		fachada.editar(professor);
		professores = null;
		professor = new Professor();
	}
	
	

}
