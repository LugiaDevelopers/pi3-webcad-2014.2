package br.com.webcad.negocio;

import java.util.ArrayList;
import java.util.Date;

import br.com.webcad.negocio.administrador.Administrador;
import br.com.webcad.negocio.coordenador.Coordenador;
import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.equipamento.TipoEquipamento;
import br.com.webcad.negocio.professor.Professor;
import br.com.webcad.negocio.reserva.Reserva;

public interface IFachada {

	// Administrador
	public abstract void cadastrar(Administrador adm);

	public abstract void editar(Administrador adm);

	public abstract void desabilitar(Administrador adm);

	public abstract ArrayList<Administrador> buscarAdministrador(String nomaAdm);

	public abstract boolean fazerLoginAdministrador(String email, String senha);
	
	public abstract ArrayList<Administrador> listar();

	// Cordenador
	public abstract String cadastrarCoordenador();

	public abstract boolean fazerLoginCordenador(String email, String senha);
	

	// Curso
	public abstract void cadastrar(Curso curso);

	public abstract void editar(Curso curso);

	public abstract void desabilitar(Curso curso);

	public abstract ArrayList<Curso> buscarCurso(String nomeCurso);
	
	public abstract ArrayList<Curso> listarCurso();
	

	// Equipamento
	public abstract void cadastrar(Equipamento equip);

	public abstract void editar(Equipamento equip);

	public abstract void desabilitar(Equipamento equip);

	public abstract ArrayList<Equipamento> buscarEquipamento(String nomeEquip);

	public abstract String manutencao(ArrayList<Equipamento> equipManutencao);

	public abstract void triagem(int quantidade,TipoEquipamento tipoequipamento, Curso curso);
	
	public abstract ArrayList<Equipamento> listarEquipamentos();

	public abstract ArrayList<TipoEquipamento> listarTipos();
	
	public abstract void cadastraTipo(Equipamento equip);
	
	public abstract ArrayList<Equipamento> equipamentosDisponiveis();
	
	public abstract ArrayList<Equipamento> equipamentosAlocados(int idCurso);
	
	public abstract void alocarEquipamento(int idCurso, int idEquip, boolean alocado);
	
	// Professor
	public abstract void cadastrar(Professor prof);

	public abstract void editar(Professor prof);

	public abstract void desabilitar(Professor prof);

	public abstract ArrayList<Professor> buscar(String nomeProfessor);

	public abstract boolean fazerLogin(String email, String senha);
	
	public abstract ArrayList<Professor> listarProf();

	// Reserva
	public abstract void cadastrar(Reserva reserva);

	public abstract String desisteReserva(ArrayList<Equipamento> equipamento);

	public abstract ArrayList<Reserva> buscarEquipamentosDisponiveis(String nome, String curso, String data);
	
	public abstract  void efetuarReserva(int idEquip, String email, String nome, String data);
	
	public abstract ArrayList<Reserva> verificarReservas(String email);
	
	public abstract void desistirDaReserva(int idReserva);
}
