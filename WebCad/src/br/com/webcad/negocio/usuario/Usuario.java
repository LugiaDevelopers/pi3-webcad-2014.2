package br.com.webcad.negocio.usuario;

public class Usuario {

	private int id;
	private String nome;
	private String senha;
	private String email;
	private String tipo;
	private String matricula;

	public Usuario(int id, String nome, String senha, String email,
			String tipo, String matricula) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
		this.matricula = matricula;
	}

	public Usuario(){
		
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
