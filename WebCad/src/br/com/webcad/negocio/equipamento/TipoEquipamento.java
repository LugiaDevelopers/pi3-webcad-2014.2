package br.com.webcad.negocio.equipamento;

public class TipoEquipamento {

	private int id;
	private String nome;
	private int quantidade;
	private int quantidadeAlocada;

	public TipoEquipamento(int id, String nome, int quantidade,int quantidadeAlocada) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.quantidadeAlocada = quantidadeAlocada;
	}
	public TipoEquipamento(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getQuantidadeAlocada() {
		return quantidadeAlocada;
	}
	public void setQuantidadeAlocada(int quantidadeAlocada) {
		this.quantidadeAlocada = quantidadeAlocada;
	}
	
	

	
}
