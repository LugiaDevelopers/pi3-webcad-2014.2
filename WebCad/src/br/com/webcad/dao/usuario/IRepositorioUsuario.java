package br.com.webcad.dao.usuario;

public interface IRepositorioUsuario {

	public abstract boolean verificaEmail(String email);

	public abstract boolean verificaMatricula(int matricula);

	public abstract boolean verificaEmailDeterminado(String email, int id);
	
	public abstract boolean verificaMatriculaDeterminada(int matricula, int id);
}
