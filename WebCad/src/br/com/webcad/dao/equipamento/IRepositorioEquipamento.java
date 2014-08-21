package br.com.webcad.dao.equipamento;

import java.util.ArrayList;

import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.equipamento.TipoEquipamento;

public interface IRepositorioEquipamento {
	
	public abstract void cadastrar(Equipamento equip);
	public abstract void editar(Equipamento equip);
	public abstract void desabilitar(Equipamento equip);
	public abstract ArrayList<Equipamento> buscarEquipamento(String nomeEquip);
	public abstract String manutencao(ArrayList<Equipamento> equipManutencao);
	public abstract void triagem(int quantidade, TipoEquipamento tipoequipamento, Curso curso);
	public abstract ArrayList<Equipamento> listar();
	public abstract ArrayList<TipoEquipamento> listarTipos();
	public abstract void cadastraTipo(Equipamento equip);
	public abstract ArrayList<Equipamento> equipamentosDisponiveis();
	public abstract ArrayList<Equipamento> equipamentosAlocados(int idCurso);
	public abstract void alocarEquipamento(int idCurso, int idEquip,boolean alocado);
}
