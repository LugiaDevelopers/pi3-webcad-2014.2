package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	public ArrayList<Administrador> getAdministradores() {
		return fachada.listar();
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores = administradores;
	}

	public String salvar() {
		if(administrador.getId() >0){
			editarAdministrador();
			
		}else{
			inserirAdministrador();
			
		}
						
	return "cadastro_administrador";
		
	}

	
	public void desabilitar(Administrador administrador) {
		
		fachada.desabilitar(administrador);
		administradores = null;
		administrador = new Administrador();
		System.out.println("test");
	}

	public String editar(Administrador administrador){
		this.administrador = administrador;
		return "cadastro_administrador";
				
		
	}
	
	private void inserirAdministrador() {

		fachada.cadastrar(administrador);

		administradores = null;
		administrador = new Administrador();

	}

	private void editarAdministrador(){
		
		fachada.editar(administrador);
		administradores = null;
		administrador = new Administrador();
	}
}
