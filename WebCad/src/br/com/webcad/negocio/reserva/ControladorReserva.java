package br.com.webcad.negocio.reserva;

import java.util.ArrayList;
import java.util.Date;

import br.com.webcad.dao.reserva.IRepositorioReserva;
import br.com.webcad.dao.reserva.RepositorioReserva;
import br.com.webcad.negocio.equipamento.Equipamento;

public class ControladorReserva {

	private IRepositorioReserva repositorioReserva;

	public ControladorReserva() {
		this.repositorioReserva = new RepositorioReserva();
	}

	public String efetuarReserva(Reserva reservaEquipamento) {
		String retorno = "";
		try {
			repositorioReserva.cadastrar(reservaEquipamento);
			retorno = "Reserva realizada com sucesso!";
		} catch (Exception e) {
			System.out.println("Erro ao realizar reserva! " + e);
			retorno = "Erro ao realizar reserva!";
		}
		return retorno;
	}

	public String desistirReserva(ArrayList<Equipamento> equipamentos) {
		return repositorioReserva.desisteReserva(equipamentos);
	}
	public  ArrayList<Reserva> buscarEquipamentosDisponiveis(String nome, String curso, String data){
		return repositorioReserva.buscarEquipamentosDisponiveis(nome, curso, data);
	}
	public void efetuarReserva(int idEquip, String email, String nome, String data){
		repositorioReserva.efetuarReserva(idEquip, email, nome, data);
	}
	public ArrayList<Reserva> verificarReservas(String email){
		return repositorioReserva.verificarReservas(email);
	}
	public void dessistirDaReserva(int idReserva){
		repositorioReserva.desistirDaReserva(idReserva);
	}
}
