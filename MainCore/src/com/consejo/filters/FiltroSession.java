package com.consejo.filters;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.consejo.cdi.general.anotaciones.ITestGuardiaSession;
import com.consejo.cdi.general.interfaz.IGuardiaUsuarioSession;
import com.consejo.cdi.seguridad.impl.TestAccesos;


@WebFilter(dispatcherTypes = {DispatcherType.ERROR,DispatcherType.REQUEST}, 
		urlPatterns = {"/imagenes/*","/administracion/*","/generales/*","/opciones/*","/template/*","/reportes/*"})  
public class FiltroSession implements Filter {

	@Inject
	@ITestGuardiaSession
	private IGuardiaUsuarioSession guardiaUsuarioSession;
	
	@Inject
	private TestAccesos accesosSistema;

    
    
	public FiltroSession() {
		
	}

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		

	try{	
		
		String dato = null;
		

			if (req.getSession(false) != null) {
				
				//MDC.put("username", guardiaUsuarioSession.nombreUsuarioSession());
				//MDC.put("sesion",guardiaUsuarioSession.obtenerSessionFSS());
				
				System.out.println("(URL)--> "+req.getRequestURI());
				dato = (String) req.getSession(false).getAttribute(
						IGuardiaUsuarioSession.SEMILLA);
			}

			if (req.getSession(false) == null
					|| !guardiaUsuarioSession.usuarioEnSession()					
					|| (req.getMethod().toUpperCase().equals("GET")
							&& req.getRequestURI().endsWith(
									IGuardiaUsuarioSession.PAGINA_PRINCIPAL) && (dato == null && !guardiaUsuarioSession
							.validarSemilla(dato)))

			) {
				System.out.println("--> No se encontro usuario en session");
				System.out.println("*  S E   R E D I R E C C I O N A R A   A   L O G I N  *");
								res.sendRedirect(req.getContextPath() + "/login.jsf");

			} else {
				
				
			/*	
				if (!accesosSistema.getHashMapAccesos().containsKey(
						FilenameUtils.getName(req.getRequestURI().toString())))
					res.sendRedirect(req.getContextPath() + "/generales/home.jsf");
				else
					chain.doFilter(request, response);
				*/
				chain.doFilter(request, response);
			}

		
	}catch(Exception e) {e.printStackTrace();}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
