package br.com.webcad.dao.tipoEquipamento;

import java.util.ArrayList;

import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

public interface IRepositorioTipoEquipamento {

	public abstract void cadastrar(TipoEquipamento tipoEquip);

	public abstract void editar(TipoEquipamento tipoEquip);

	public abstract void deletar(TipoEquipamento tipoEquip);

	public abstract ArrayList<TipoEquipamento> listar();
}
