package br.com.webcad.negocio.equipamento;

import java.util.ArrayList;

import br.com.webcad.dao.equipamento.IRepositorioEquipamento;
import br.com.webcad.dao.equipamento.RepositorioEquipamento;
import br.com.webcad.dao.reserva.IRepositorioReserva;
import br.com.webcad.dao.reserva.RepositorioReserva;
import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.reserva.Reserva;
import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

public class ControladorEquipamento {

	private IRepositorioEquipamento repositorioEquipamento;
	private IRepositorioReserva repositorioReserva;
	
	public ControladorEquipamento(){
		this.repositorioEquipamento = new RepositorioEquipamento();
		this.repositorioReserva = new RepositorioReserva();
	}
	
	public String cadastraEquipamento(Equipamento equip) {
		String retorno = "";
		try {
			repositorioEquipamento.cadastrar(equip);
			retorno = "Equipamento cadastrado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar! " + e);
			retorno = "Erro ao cadastrar!";
		}
		return retorno;
	}

	public String desabilitarEquipamento(Equipamento equip) {
		String retorno = "";
		try {
			repositorioEquipamento.desabilitar(equip);
			retorno = "Equipamento desabilitado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao desabilitar! " + e);
			retorno = "Erro ao desabilitar!";
		}
		return retorno;
	}

	public String editarEquipamento(Equipamento equip) {
		String retorno = "";
		try {
			repositorioEquipamento.editar(equip);
			retorno = "Equipamento editado com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao editar! " + e);
			retorno = "Erro ao editar!";
		}
		return retorno;
	}

	public ArrayList<Equipamento> buscarEquipamento(String numeroDeSerie) {
		return null;
	}

	public String equipamentoManutencao(Equipamento equipManutencao) {
		return repositorioEquipamento.manutencao(equipManutencao);
	}

	public String triagemEquipamento(int quantidade,
			TipoEquipamento tipoEquipamento, Curso curso) {
		return null;
	}
	
	public ArrayList<Equipamento> listarEquipamentos() {
		return repositorioEquipamento.listar();
	} 

	public ArrayList<TipoEquipamento> listarTipos() {
		return repositorioEquipamento.listarTipos();
	}
	
	public  void cadastraTipo(Equipamento equip){
		repositorioEquipamento.cadastraTipo(equip);
	}
	
	public ArrayList<Equipamento> equipamentosDisponiveis() {
		return repositorioEquipamento.equipamentosDisponiveis();
	}
	
	public ArrayList<Equipamento> equipamentosAlocados(int idCurso){
		return repositorioEquipamento.equipamentosAlocados(idCurso);
	}
	
	public void alocarEquipamento(int idCurso, int idEquip, boolean alocado){
		repositorioEquipamento.alocarEquipamento(idCurso, idEquip, alocado);
	}

	public Equipamento buscarEquipamentoPorNumeroDeTombo(String numeroDeSerie) {
		Equipamento equip = new Equipamento();
		equip = repositorioEquipamento.buscarEquipamentoPorNumeroDeTombo(numeroDeSerie);
	
		
		ArrayList<Reserva> minhasReservas =  repositorioReserva.listarReserva();
		
		for (int i = 0; i < minhasReservas.size(); i++) {
			if(minhasReservas.get(i).getEquipamento().getId()== equip.getId() ){
				equip.setId(minhasReservas.get(i).getId());
			}
			 
		}
		
		return equip;
	}

	
}
