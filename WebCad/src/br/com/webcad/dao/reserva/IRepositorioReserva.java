package br.com.webcad.dao.reserva;

import java.util.ArrayList;
import java.util.Date;

import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.reserva.Reserva;


public interface IRepositorioReserva {

	public abstract void cadastrar(Reserva reserva);
	public abstract String desisteReserva(ArrayList<Equipamento> equipamento);
	public abstract ArrayList<Reserva> buscarEquipamentosDisponiveis(String nome, String curso, String data);
	public abstract void efetuarReserva(int idEquip ,String email, String nome, String data );
	public abstract ArrayList<Reserva> verificarReservas(String email);
	public abstract void desistirDaReserva(int idReserva);
}
