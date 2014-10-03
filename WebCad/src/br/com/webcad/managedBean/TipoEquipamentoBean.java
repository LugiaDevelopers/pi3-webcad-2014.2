package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

@ManagedBean(name = "tipoEquipamentoMB")
@SessionScoped
public class TipoEquipamentoBean {

	private TipoEquipamento tipoEquipamento;
	private IFachada fachada;
	private ArrayList<TipoEquipamento> tipoEquipamentos;

	public TipoEquipamentoBean() {
		tipoEquipamento = new TipoEquipamento();
		fachada = Fachada.getInstancia();
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	
	public ArrayList<TipoEquipamento> getTipoEquipamentos() {
		return fachada.listarTipoEquipamento();
	}

	public void setTipoEquipamentos(ArrayList<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}
	
	
	

	public String salvar() {
		if (tipoEquipamento.getId() > 0) {
			editarTipoEquipamento();
		} else {
			inserirTipoEquipamento();
		}
		return "cadastro_tipo_equipamento";
	}

	public String excluir(TipoEquipamento tipoEquip) {
		fachada.deletar(tipoEquip);;
		tipoEquipamentos = null;
		tipoEquipamento = new TipoEquipamento();
		
		getTipoEquipamentos();
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tipo de equipamento deletedo com sucesso."));

		return "cadastro_tipo_equipamento?faces-redirect=true";

	}

	public String editar(TipoEquipamento tipoEquip) {
		this.tipoEquipamento = tipoEquip;
		return "cadastro_tipo_equipamento";
	}

	public void inserirTipoEquipamento() {
		fachada.cadastrar(tipoEquipamento);
		
		tipoEquipamentos = null;
		tipoEquipamento = new TipoEquipamento();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastro efetuado com sucesso."));

	}

	public void editarTipoEquipamento() {
		fachada.editar(tipoEquipamento);
		tipoEquipamentos = null;
		tipoEquipamento = new TipoEquipamento();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Edição efetuada com sucesso."));
	}
}
