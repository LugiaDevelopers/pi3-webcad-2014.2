package br.com.webcad.dao.coordenador;

import br.com.webcad.negocio.coordenador.Coordenador;

public interface IRepositorioCoordenador {
	
	public abstract String cadastrarCoordenador();
	public abstract boolean fazerLogin(String email, String senha);

}
