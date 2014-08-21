import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.administrador.Administrador;


public class Start {

	public static void main(String[] args) {
		Administrador adm = new Administrador();
		adm.setNome("tom");
		adm.setMatricula("123");
		adm.setEmail("asdf3");
		adm.setSenha("1234");
		IFachada fachada = Fachada.getInstancia();
		
		fachada.cadastrar(adm);
	}

}
