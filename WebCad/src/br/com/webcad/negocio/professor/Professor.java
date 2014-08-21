package br.com.webcad.negocio.professor;

import br.com.webcad.negocio.usuario.Usuario;

public class Professor extends Usuario {

	private int pontos;

	public Professor(int id, String nome, String senha, String email,
			String tipo, String matricula, int pontos) {
		super(id, nome, senha, email, tipo, matricula);
		this.pontos = pontos;

	}
	
	public Professor(){
		
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
