package br.com.webcad.negocio.tipoEquipamento;

import java.util.ArrayList;

import br.com.webcad.dao.tipoEquipamento.IRepositorioTipoEquipamento;
import br.com.webcad.dao.tipoEquipamento.RepositorioTipoEquipamento;

public class ControladorTipoEquipamento {

	private IRepositorioTipoEquipamento repositoriTipoEquipamento;

	public ControladorTipoEquipamento() {
		this.repositoriTipoEquipamento = new RepositorioTipoEquipamento();
	}

	public void cadastrar(TipoEquipamento tipoEquip) {
		repositoriTipoEquipamento.cadastrar(tipoEquip);
	}

	public void editar(TipoEquipamento tipoEquip) {
		repositoriTipoEquipamento.editar(tipoEquip);

	}

	public void deletar(TipoEquipamento tipoEquip) {
		repositoriTipoEquipamento.deletar(tipoEquip);

	}

	public ArrayList<TipoEquipamento> listar() {

		return repositoriTipoEquipamento.listar();
	}
}