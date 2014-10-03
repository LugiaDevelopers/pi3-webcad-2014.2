package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.administrador.Administrador;

@ManagedBean(name = "administradorMB")
@SessionScoped
public class AdministradorBean {

	private Administrador administrador;
	private IFachada fachada;
	private ArrayList<Administrador> administradores;

	public AdministradorBean() {
		administrador = new Administrador();
		fachada = Fachada.getInstancia();
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	@PostConstruct  
	public ArrayList<Administrador> getAdministradores() {
		return fachada.listar();
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores = administradores;
	}

	
	
	public String salvar() {
		boolean emailExiste = fachada.verificaEmail(administrador.getEmail());
		boolean matriculaExiste = fachada.verificaMatricula(Integer.parseInt(administrador.getMatricula()));
		boolean emailDeterminadoExiste = fachada.verificaEmailDeterminado(administrador.getEmail(), administrador.getId());
		boolean matriculaDeterminadaExiste = fachada.verificaMatriculaDeterminada(Integer.parseInt(administrador.getMatricula()), administrador.getId());

		System.out.println("clicou em salvar "+administrador.getMatricula());
		
		if(administrador.getId() >0){
			System.out.println("clicou em savar a edição N1 "+ administrador.getMatricula());
			
			if((emailExiste == false && matriculaExiste == false) 
			||(emailDeterminadoExiste == true && matriculaExiste == false)
			||(matriculaDeterminadaExiste == true && emailExiste == false)
			||(matriculaDeterminadaExiste == true && emailDeterminadoExiste == true))
			
			{
				System.out.println("clicou em savar a edição N2 "+ administrador.getMatricula());
					
				editarAdministrador();
			
			}else if ((emailExiste == true && matriculaExiste == true) 
					&& (emailDeterminadoExiste == false && matriculaDeterminadaExiste == false)) {
				System.out.println("Email e matricula já existem! "+ administrador.getMatricula());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email e matricula já existem!!"));	
			
			}else if((emailExiste == true && emailDeterminadoExiste == false )
					 ){
				System.out.println("Esse email já existe! "+ administrador.getMatricula());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Esse email já existe!"));	
			
			}else if((matriculaExiste == true && matriculaDeterminadaExiste == false) 
					){
				System.out.println("Esse matricula já existe! "+ administrador.getMatricula());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Esse matricula já existe!"));
			}
			
			
		}else{
			if(emailExiste != true && matriculaExiste != true){
				inserirAdministrador();
			}else if(emailExiste == true && matriculaExiste == true){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email e matricula já existem!!"));	
			}else if(matriculaExiste == true && emailExiste != true){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Esse matricula já existe!"));	
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Esse email já existe!"));	
				
				}
			
			
			
		}
						
	return "cadastro_administrador";
		
	}

	
	public String desabilitar(Administrador administrador) {
		
		fachada.desabilitar(administrador);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Administrador deletedo com sucesso."));

		administradores = null;
		administrador = new Administrador();

		getAdministradores();
		
		return "cadastro_administrador?faces-redirect=true";
		
	}

	public String editar(Administrador administrador){
		System.out.println("botao editar clicado "+ administrador.getMatricula());
		this.administrador = administrador;
		return "cadastro_administrador";
				
		
	}
	
	private void inserirAdministrador() {

		fachada.cadastrar(administrador);

		administradores = null;
		administrador = new Administrador();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastro efetuado com sucesso."));

	}

	private void editarAdministrador(){
		System.out.println(""+administrador.getMatricula());
		fachada.editar(administrador);
		administradores = null;
		administrador = new Administrador();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Edição efetuada com sucesso."));
	}
	
	
	 

}
