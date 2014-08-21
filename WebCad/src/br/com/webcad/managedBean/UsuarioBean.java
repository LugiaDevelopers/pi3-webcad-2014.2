package br.com.webcad.managedBean;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.administrador.Administrador;
import br.com.webcad.negocio.usuario.Usuario;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioBean {

	private Usuario usuario;
	private IFachada fachada;
	
	public UsuarioBean(){
		usuario = new Usuario();
		fachada = Fachada.getInstancia();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String fazerLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(fachada.fazerLogin(usuario.getEmail(), usuario.getSenha()) == true){
			
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", usuario.getEmail());
			return "/home_professor?faces-redirect=true";
			
		}else if(fachada.fazerLoginAdministrador(usuario.getEmail(), usuario.getSenha()) == true){
			return "home_administrador";
		}else if(fachada.fazerLoginCordenador(usuario.getEmail(), usuario.getSenha()) == true){
			return "home_cordenador";
		}else{
			FacesMessage fm = new FacesMessage("usuário e/ou senha inválidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			fc.getExternalContext().getFlash().setKeepMessages(true);
			return "/index?faces-redirect=true";
		}
		
		
		
		
		
	}
	
	
	public String registraSaida() {
				FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "/home_professor?faces-redirect=true";
	}
	
	
	
	public void cadastrarCoordenador(){
		System.out.println("entrou no bean");
		fachada.cadastrarCoordenador();
	}
}
