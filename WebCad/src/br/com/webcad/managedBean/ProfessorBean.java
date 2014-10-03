package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		boolean emailExiste = fachada.verificaEmail(professor.getEmail());
		boolean matriculaExiste = fachada.verificaMatricula(Integer
				.parseInt(professor.getMatricula()));
		boolean emailDeterminadoExiste = fachada.verificaEmailDeterminado(
				professor.getEmail(), professor.getId());
		boolean matriculaDeterminadaExiste = fachada
				.verificaMatriculaDeterminada(
						Integer.parseInt(professor.getMatricula()),
						professor.getId());

		if (professor.getId() > 0) {
			if ((emailExiste == false && matriculaExiste == false)
					|| (emailDeterminadoExiste == true && matriculaExiste == false)
					|| (matriculaDeterminadaExiste == true && emailExiste == false)
					|| (matriculaDeterminadaExiste == true && emailDeterminadoExiste == true))

			{

				editarProfessor();

			} else if ((emailExiste == true && matriculaExiste == true)
					&& (emailDeterminadoExiste == false && matriculaDeterminadaExiste == false)) {
				System.out.println("Email e matricula já existem! "
						+ professor.getMatricula());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Email e matricula já existem!!"));

			} else if ((emailExiste == true && emailDeterminadoExiste == false)) {
				System.out.println("Esse email já existe! "
						+ professor.getMatricula());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Esse email já existe!"));

			} else if ((matriculaExiste == true && matriculaDeterminadaExiste == false)) {
				System.out.println("Esse matricula já existe! "
						+ professor.getMatricula());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Esse matricula já existe!"));
			}
		} else {
			if (emailExiste != true && matriculaExiste != true) {
				inserirProfessor();
			} else if (emailExiste == true && matriculaExiste == true) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Email e matricula já existem!!"));
			} else if (matriculaExiste == true && emailExiste != true) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Esse matricula já existe!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Esse email já existe!"));

			}
		}

		return "cadastro_professor";
	}

	public String desabilitar(Professor professor) {

		fachada.desabilitar(professor);
		professores = null;
		professor = new Professor();
		System.out.println("test");
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Professor deletado com sucesso."));

		return "cadastro_professor?faces-redirect=true";
	}

	public String editar(Professor professor) {
		this.professor = professor;
		return "cadastro_professor";

	}

	private void inserirProfessor() {

		fachada.cadastrar(professor);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Cadastro efetuado com sucesso."));

		professores = null;
		professor = new Professor();

	}

	private void editarProfessor() {

		fachada.editar(professor);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Edição efetuada com sucesso."));

		professores = null;
		professor = new Professor();
	}

}
