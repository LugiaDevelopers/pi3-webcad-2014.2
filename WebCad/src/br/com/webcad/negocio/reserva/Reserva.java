package br.com.webcad.negocio.reserva;

import java.util.Date;

import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.usuario.Usuario;

public class Reserva {

	private int id;
	private Equipamento equipamento;
	private Usuario usuario;
	private String data;

	public Reserva(int id, Equipamento equipamento, String data) {
		super();
		this.id = id;
		this.equipamento = equipamento;
		this.data = data;
	}

	public Reserva() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
