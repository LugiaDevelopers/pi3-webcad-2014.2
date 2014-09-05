import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames = { "Faces Servlet" })
public class ControleDeAcesso implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("usuario") != null	|| req.getRequestURI().endsWith("index.xhtml")) {
			
			String tipo = "" + session.getAttribute("tipo");

			if ((req.getRequestURI().endsWith("realizar_reserva.xhtml") 
					|| req.getRequestURI().endsWith("home_professor.xhtml"))
					&& (tipo.equals("coordenador") 
					|| tipo	.equals("administrador"))) {
				System.out.println("entrou na condição");
				redireciona("pagina_erro.xhtml", response);
			}

			
			if ((req.getRequestURI().endsWith("home_cordenador.xhtml") 
					|| req.getRequestURI().endsWith("cadastro_administrador.xhtml"))
					&& (tipo.equals("professor") || tipo.equals("administrador"))) {
				redireciona("pagina_erro.xhtml", response);
			}

			
			if ((req.getRequestURI().endsWith("home_administrador.xhtml")
					|| req.getRequestURI().endsWith("cadastro_professor.xhtml")
					|| req.getRequestURI().endsWith("cadastro_equipamento.xhtml")
					|| req.getRequestURI().endsWith("cadastro_curso.xhtml")
					|| req.getRequestURI().endsWith("manutencao.xhtml")
					|| req.getRequestURI().endsWith("triagem_equipamento.xhtml") 
					|| req.getRequestURI().endsWith("cadastro_tipo_equipamento.xhtml"))
					&& (tipo.equals("professor") || tipo.equals("coordenador"))) {
				
				redireciona("pagina_erro.xhtml", response);
				
			}

			chain.doFilter(request, response);

		} else {
			redireciona("index.xhtml", response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	private void redireciona(String url, ServletResponse response)
			throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect("" + url);

	}
}
