package br.com.webcad.negocio.coordenador;

import br.com.webcad.dao.coordenador.IRepositorioCoordenador;
import br.com.webcad.dao.coordenador.RepositorioCoordenador;

public class ControladorCoordenador {

	private IRepositorioCoordenador repositorioCordenador;
	
	public ControladorCoordenador(){
		this.repositorioCordenador = new RepositorioCoordenador();
	}
	
	
	public String cadastrarCoordenador(){
		System.out.println("entrou no controlador");
		String retorno = "";
		try {
			repositorioCordenador.cadastrarCoordenador();
			retorno = "Cordenador cadastrado com Sucesso";
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar! " + e);
			retorno = "Erro ao cadastrar";
		}
		return retorno;
	}
	
	
	public boolean fazerLogin(String email, String senha) {
		
		if(repositorioCordenador.fazerLogin(email, senha) == true){
			return true;
		}
		
		return false;
	}
	
}
