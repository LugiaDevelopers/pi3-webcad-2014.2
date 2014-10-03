package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.usuario.Usuario;

@ManagedBean
@RequestScoped
public class AutenticadorBean {

	private IFachada fachada;
	private Usuario usuario;
	private FacesContext fc;
	private ArrayList<Usuario> todosUsuarios;

	public AutenticadorBean() {
		usuario = new Usuario();
		fachada = Fachada.getInstancia();
		todosUsuarios = new ArrayList<Usuario>();

	}

	public String autentica() {

		System.out.println("entrou");
		if (fachada.fazerLogin(usuario.getEmail(), usuario.getSenha()) == true) {

			System.out.println("entrou professor");

			inserirUsuarioNaSessao();
			return "/home_professor?faces-redirect=true";

		} else if (fachada.fazerLoginAdministrador(usuario.getEmail(),
				usuario.getSenha()) == true) {

			System.out.println("entrou administrador");

			inserirUsuarioNaSessao();
			return "/home_administrador?faces-redirect=true";
		} else if (fachada.fazerLoginCordenador(usuario.getEmail(),
				usuario.getSenha()) == true) {

			System.out.println("entrou coordenador");

			inserirUsuarioNaSessao();
			return "/home_cordenador?faces-redirect=true";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ",
							"Email e/ou senha inválidos"));

			return "/index";
		}

	}

	public String registraSaida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "/index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// cria usuario cordenador padrao!
	public void cadastrarCoordenador() {
		System.out.println("entrou no bean");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta! ", "Coordenador Cadastrado!"));
		 
		fachada.cadastrarCoordenador();
	}

	private void inserirUsuarioNaSessao() {
		fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("usuario", this.usuario);

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("email", usuario.getEmail());

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("tipo", tipoUsuario());

	}

	private String tipoUsuario() {
		todosUsuarios.addAll(fachada.listar());
		todosUsuarios.addAll(fachada.listarProf());
		todosUsuarios.addAll(fachada.listarCoordenador());
		String tipo = " ";
		for (int i = 0; i < todosUsuarios.size(); i++) {
			if (todosUsuarios.get(i).getEmail().equals(usuario.getEmail())) {
				tipo = todosUsuarios.get(i).getTipo();
			}
		}

		return tipo;
	}

}
