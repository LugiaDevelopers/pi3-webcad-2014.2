package br.com.webcad.negocio.curso;

public class Curso {

	private int id;
	private String nome;

	public Curso(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Curso(){
		
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

}
