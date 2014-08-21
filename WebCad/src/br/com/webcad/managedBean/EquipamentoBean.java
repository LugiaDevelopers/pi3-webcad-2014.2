package br.com.webcad.managedBean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.equipamento.TipoEquipamento;

@ManagedBean(name = "equipamentoBM")
@SessionScoped
public class EquipamentoBean {

	private Equipamento equipamento;
	private TipoEquipamento tipoEquipamento;
	private IFachada fachada;
	private ArrayList<Equipamento> equipamentos;
	private ArrayList<TipoEquipamento> tipoEquipamentos;
	private ArrayList<Equipamento> equipamentosDisponiveis;
	private ArrayList<Equipamento> equipamentosAlocados;

	private int chaveControleQuantidadeAlocada = 0;

	public EquipamentoBean() {
		equipamento = new Equipamento();
		tipoEquipamento = new TipoEquipamento();
		fachada = Fachada.getInstancia();
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public ArrayList<Equipamento> getEquipamentos() {

		return fachada.listarEquipamentos();

	}

	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	// aqui!
	public ArrayList<TipoEquipamento> getTipoEquipamentos() {
		if (chaveControleQuantidadeAlocada == 0) {
			
			return fachada.listarTipos();
		} else {
			return tipoEquipamentos;
		}
		

	}

	public void setTipoEquipamentos(ArrayList<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}

	public ArrayList<Equipamento> getEquipamentosDisponiveis() {

		return fachada.equipamentosDisponiveis();
	}

	public void setEquipamentosDisponiveis(
			ArrayList<Equipamento> equipamentosDisponiveis) {
		this.equipamentosDisponiveis = equipamentosDisponiveis;
	}

	public ArrayList<Equipamento> getEquipamentosAlocados() {
		return equipamentosAlocados;
	}

	public void setEquipamentosAlocados(
			ArrayList<Equipamento> equipamentosAlocados) {
		this.equipamentosAlocados = equipamentosAlocados;
	}

	public String salvar() {
		if (equipamento.getId() > 0) {
			editarEquipamento();
		} else {
			System.out.println("entrou no salvar");
			inserirEquipamento();
		}

		return "cadastro_equipamento";
	}

	public void desabilitar(Equipamento equipamento) {

		fachada.desabilitar(equipamento);
		equipamentos = null;
		equipamento = new Equipamento();
		System.out.println("test");
	}

	public String editar(Equipamento equipamento) {
		this.equipamento = equipamento;
		return "cadastro_equipamento";

	}

	private void inserirEquipamento() {
		equipamento.setTipoEquipamento(tipoEquipamento);
		fachada.cadastrar(equipamento);

		equipamentos = null;
		equipamento = new Equipamento();
		tipoEquipamento = new TipoEquipamento();

	}

	private void editarEquipamento() {

		equipamento.setTipoEquipamento(tipoEquipamento);
		fachada.editar(equipamento);
		equipamentos = null;
		equipamento = new Equipamento();
		tipoEquipamento = new TipoEquipamento();
	}

	public void cadastraTipo() {

		System.out.println(tipoEquipamento.getNome());
		equipamento.setTipoEquipamento(tipoEquipamento);
		fachada.cadastraTipo(equipamento);

		equipamentos = null;
		equipamento = new Equipamento();
		tipoEquipamento = new TipoEquipamento();

	}

	public String equipamentosAlocados(int idCurso) {
		chaveControleQuantidadeAlocada = 1;
		System.out.println("entrou e o id é " + idCurso + " blablabla");
		equipamentosAlocados = fachada.equipamentosAlocados(idCurso);
		tipoEquipamentos = fachada.listarTipos();
		
		equipamentos = null;
		return "triagem_equipamento";

	}

	public String alocarEquipamento(int idCurso, String nomeEquip, boolean alocado) {
	System.out.println("entrou n"+alocado);	
		equipamentos = fachada.listarEquipamentos();
		if(alocado== false){
			for (int i = 0; i < equipamentos.size(); i++) {
				if(equipamentos.get(i).getTipoEquipamento().getNome().equals(nomeEquip)&&equipamentos.get(i).isDisponivelParaLocacao()== true){
					fachada.alocarEquipamento(idCurso, equipamentos.get(i).getId(), alocado);
					equipamentosAlocados(idCurso);
					equipamentos = null;
					break;
				}
			}

		}
		
		System.out.println("entrou n0"+alocado);
		if(alocado != false){
			System.out.println("entrou n1");
			for (int i = 0; i < equipamentos.size(); i++) {
				System.out.println("entrou n2");
				if(equipamentos.get(i).getTipoEquipamento().getNome().equals(nomeEquip)&&equipamentos.get(i).isDisponivelParaLocacao()!= true){
					System.out.println("entrou n3");
					fachada.alocarEquipamento(0, equipamentos.get(i).getId(), alocado);
					equipamentosAlocados(idCurso);
					equipamentos = null;
					break;
				}
			}
		}

		return "triagem_equipamento";
	}

}
