package br.com.webcad.negocio;

import java.util.ArrayList;

import br.com.webcad.negocio.administrador.Administrador;
import br.com.webcad.negocio.administrador.ControladorAdministrador;
import br.com.webcad.negocio.coordenador.ControladorCoordenador;
import br.com.webcad.negocio.coordenador.Coordenador;
import br.com.webcad.negocio.curso.ControladorCurso;
import br.com.webcad.negocio.curso.Curso;
import br.com.webcad.negocio.equipamento.ControladorEquipamento;
import br.com.webcad.negocio.equipamento.Equipamento;
import br.com.webcad.negocio.professor.ControladorProfessor;
import br.com.webcad.negocio.professor.Professor;
import br.com.webcad.negocio.reserva.ControladorReserva;
import br.com.webcad.negocio.reserva.Reserva;
import br.com.webcad.negocio.tipoEquipamento.ControladorTipoEquipamento;
import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;
import br.com.webcad.negocio.usuario.ControladorUsuario;

public class Fachada implements IFachada{
	
	private static IFachada instancia;
	private ControladorUsuario controladorUsuario;
	private ControladorReserva controladorReserva;
	private ControladorProfessor controladorProfessor;
	private ControladorCoordenador controladorCordenador;
	private ControladorAdministrador controladorAdmnistrador;
	private ControladorEquipamento controladorEquipamento;
	private ControladorCurso controladorCurso;
	private ControladorTipoEquipamento controladorTipoEquipamento;
	
	
	public Fachada() {
		this.controladorUsuario = new ControladorUsuario();
		this.controladorCordenador = new ControladorCoordenador();
		this.controladorAdmnistrador = new ControladorAdministrador();
		this.controladorProfessor = new ControladorProfessor();
		this.controladorCurso = new ControladorCurso();
		this.controladorEquipamento = new ControladorEquipamento();
		this.controladorReserva = new ControladorReserva();
		this.controladorTipoEquipamento = new ControladorTipoEquipamento();
	}
	
	public static IFachada getInstancia(){
		if(instancia ==null){
			instancia = new Fachada();
		}
			return instancia;
	}
	


	@Override
	public void cadastrar(Administrador adm) {
		this.controladorAdmnistrador.cadastrarAdministrador(adm);
		
	}

	@Override
	public void editar(Administrador adm) {
		this.controladorAdmnistrador.editarAdministrador(adm);
		
	}

	@Override
	public void desabilitar(Administrador adm) {
		this.controladorAdmnistrador.desabilitarAdministrador(adm);
		
	}

	@Override
	public ArrayList<Administrador> buscarAdministrador(String nomaAdm) {
		return this.controladorAdmnistrador.buscarAdministrador(nomaAdm);
	}
	
	@Override
	public ArrayList<Administrador> listar() {
		return this.controladorAdmnistrador.listar();
	}
	
	/*
	 *Usar esse m�todo para fazer os logins 
	 */
	@Override
	public boolean fazerLogin(String email, String senha) {
		
		return this.controladorProfessor.fazerLogin(email, senha);
	}
	
	/*
	 *N�o usar esse m�todo 
	 */
	@Override
	public boolean fazerLoginAdministrador(String email, String senha) {
		// TODO Auto-generated method stub
		return this.controladorAdmnistrador.fazerLogin(email, senha);
	}

	
	/*
	 * M�todos do Cordenador
	 * 
	 */
	
	@Override
	public String cadastrarCoordenador() {
		System.out.println("entrou na fachada");
		this.controladorCordenador.cadastrarCoordenador();
		return null;
	}
	
	/*
	 *N�o usar esse m�todo 
	 */
	@Override
	public boolean fazerLoginCordenador(String email, String senha) {
		// TODO Auto-generated method stub
		return this.controladorCordenador.fazerLogin(email, senha);
	}

	/*
	 * M�todos do Curso
	 * 
	 */
	
	@Override
	public void cadastrar(Curso curso) {
		this.controladorCurso.cadastroCurso(curso);
		
	}

	@Override
	public void editar(Curso curso) {
		this.controladorCurso.editarCurso(curso);
		
	}

	@Override
	public void desabilitar(Curso curso) {
		this.controladorCurso.desabilitarCurso(curso);
		
	}

	@Override
	public ArrayList<Curso> buscarCurso(String nomeCurso) {
		return this.controladorCurso.buscarCurso(nomeCurso);
	}

	/*
	 * M�todos do Equipamento
	 * 
	 */
	
	@Override
	public void cadastrar(Equipamento equip) {
		this.controladorEquipamento.cadastraEquipamento(equip);
		
	}

	@Override
	public void editar(Equipamento equip) {
		this.controladorEquipamento.editarEquipamento(equip);
		
	}

	@Override
	public void desabilitar(Equipamento equip) {
		this.controladorEquipamento.desabilitarEquipamento(equip);
		
	}

	@Override
	public ArrayList<Equipamento> buscarEquipamento(String nomeEquip) {
		return this.controladorEquipamento.buscarEquipamento(nomeEquip);
	}

	@Override
	public String manutencao(Equipamento equipManutencao) {
		return this.controladorEquipamento.equipamentoManutencao(equipManutencao);
	}

	@Override
	public void triagem(int quantidade, TipoEquipamento tipoequipamento, Curso curso) {
		this.controladorEquipamento.triagemEquipamento(quantidade, tipoequipamento, curso);
		
	}

	/*
	 * M�todos do Professor
	 * 
	 */
	
	
	@Override
	public void cadastrar(Professor prof) {
		this.controladorProfessor.cadastraProfessor(prof);
		
	}

	@Override
	public void editar(Professor prof) {
		this.controladorProfessor.editarProfessor(prof);
		
	}

	@Override
	public void desabilitar(Professor prof) {
		this.controladorProfessor.desabilitarProfessor(prof);
		
	}

	@Override
	public ArrayList<Professor> buscar(String nomeProfessor) {
		return this.controladorProfessor.buscarProfessor(nomeProfessor);
	}
	
	@Override
	public ArrayList<Professor> listarProf() {
		// TODO Auto-generated method stub
		 return controladorProfessor.listarProf();
	}

	/*
	 * M�todos do Reserva
	 * 
	 */
	
	@Override
	public void cadastrar(Reserva reserva) {
		this.controladorReserva.efetuarReserva(reserva);
		
	}

	@Override
	public String desisteReserva(ArrayList<Equipamento> equipamento) {
		return this.desisteReserva(equipamento);
	}

	@Override
	public ArrayList<Equipamento> listarEquipamentos() {
		// TODO Auto-generated method stub
		return  this.controladorEquipamento.listarEquipamentos();
	}

	@Override
	public ArrayList<TipoEquipamento> listarTipos() {
		// TODO Auto-generated method stub
		return this.controladorEquipamento.listarTipos();
	}

	@Override
	public void cadastraTipo(Equipamento equip) {
		this.controladorEquipamento.cadastraTipo(equip);
		
	}

	@Override
	public ArrayList<Curso> listarCurso() {
		// TODO Auto-generated method stub
		return this.controladorCurso.listarCurso();
	}

	@Override
	public ArrayList<Equipamento> equipamentosDisponiveis() {
		// TODO Auto-generated method stub
		return this.controladorEquipamento.equipamentosDisponiveis();
	}

	@Override
	public ArrayList<Equipamento> equipamentosAlocados(int idCurso) {
		// TODO Auto-generated method stub
		return this.controladorEquipamento.equipamentosAlocados(idCurso);
	}

	@Override
	public void alocarEquipamento(int idCurso, int idEquip,boolean alocado) {
		this.controladorEquipamento.alocarEquipamento(idCurso, idEquip, alocado);
		
	}

	@Override
	public ArrayList<Reserva> buscarEquipamentosDisponiveis(String nome,
			String curso, String data) {
		// TODO Auto-generated method stub
		return this.controladorReserva.buscarEquipamentosDisponiveis(nome, curso, data);
	}

	@Override
	public void efetuarReserva(int idEquip, String email, String nome, String data) {
		this.controladorReserva.efetuarReserva(idEquip, email, nome, data);
		
	}

	@Override
	public ArrayList<Reserva> verificarReservas(String email) {
		// TODO Auto-generated method stub
		return this.controladorReserva.verificarReservas(email);
	}

	@Override
	public void desistirDaReserva(int idReserva) {
		controladorReserva.dessistirDaReserva(idReserva);
		
	}

//tipo equipamento


	@Override
	public void cadastrar(TipoEquipamento tipoEquip) {
		controladorTipoEquipamento.cadastrar(tipoEquip);
		
	}

	@Override
	public void editar(TipoEquipamento tipoEquip) {
		controladorTipoEquipamento.editar(tipoEquip);
		
	}

	@Override
	public void deletar(TipoEquipamento tipoEquip) {
		controladorTipoEquipamento.deletar(tipoEquip);
		
	}

	@Override
	public ArrayList<TipoEquipamento> listarTipoEquipamento() {
		
		return controladorTipoEquipamento.listar();
	}

	@Override
	public ArrayList<Coordenador> listarCoordenador() {
		
		return controladorCordenador.listarCoordenador();
	}

	//usuario
	@Override
	public boolean verificaEmail(String email) {
		// TODO Auto-generated method stub
		return controladorUsuario.verificaEmail(email);
	}

	@Override
	public boolean verificaMatricula(int matricula) {
		// TODO Auto-generated method stub
		return controladorUsuario.verificaMatricula(matricula);
	}

	@Override
	public boolean verificaEmailDeterminado(String email, int id) {
		// TODO Auto-generated method stub
		return controladorUsuario.verificaEmailDeterminado(email, id);
	}

	@Override
	public boolean verificaMatriculaDeterminada(int matricula, int id) {
		// TODO Auto-generated method stub
		return controladorUsuario.verificaMatriculaDeterminada(matricula, id);
	}

	@Override
	public ArrayList<Reserva> listarReserva() {
		return controladorReserva.listarReserva();
	}

	@Override
	public Equipamento buscarEquipamentoPorNumeroDeTombo(String numeroDeSerie) {
		String[] tokens = numeroDeSerie.split(":");
		
		return controladorEquipamento.buscarEquipamentoPorNumeroDeTombo(tokens[tokens.length - 1]);
	}
	

	

}
