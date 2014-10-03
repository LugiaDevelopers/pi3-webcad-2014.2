package br.com.webcad.negocio.equipamento;

import br.com.webcad.negocio.tipoEquipamento.TipoEquipamento;

public class Equipamento {

	private int id;
	private String descricao;
	private String numeroTombamento;
	private String numeroDeSerie;
	private boolean ativo;
	private boolean manutencao;
	private TipoEquipamento tipoEquipamento;
	private boolean disponivelParaLocacao;

	public Equipamento(int id, String descricao, int numeroTombamento,
			int numeroDeSerie, boolean ativo, boolean manutencao,
			boolean disponivelParaLocacao,TipoEquipamento tipoEquipamento) {
		
		this.id = id;
		this.descricao = descricao;
		this.numeroTombamento = ""+numeroTombamento;
		this.numeroDeSerie = ""+numeroDeSerie;
		this.ativo = ativo;
		this.manutencao = manutencao;
		this.disponivelParaLocacao = disponivelParaLocacao;
		this.tipoEquipamento = tipoEquipamento;
	}
	
	public Equipamento(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroTombamento() {
		return numeroTombamento;
	}

	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isManutencao() {
		return manutencao;
	}

	public void setManutencao(boolean manutencao) {
		this.manutencao = manutencao;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public boolean isDisponivelParaLocacao() {
		return disponivelParaLocacao;
	}

	public void setDisponivelParaLocacao(boolean disponivelParaLocacao) {
		this.disponivelParaLocacao = disponivelParaLocacao;
	}

	
}
