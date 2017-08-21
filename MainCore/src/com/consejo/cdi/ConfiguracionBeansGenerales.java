package com.consejo.cdi;

import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ConfiguracionBeansGenerales 
{
	public static final String PAGINA_INICIAL = "paginaInicial";	
	public static final String PAGINA_LOGIN = "paginaLogin";
	public static final String SERVLET_CIERRE_SESSION = "servletCierreSession";

	@Produces Properties datosLectura()
	{
		Properties propiedades = new Properties();
		propiedades.put(PAGINA_INICIAL, "/generales/principal.jsf?faces-redirect=true");
		propiedades.put(PAGINA_LOGIN, "/login.jsf?faces-redirect=true");
		propiedades.put(SERVLET_CIERRE_SESSION, ((HttpServletRequest)(FacesContext.getCurrentInstance().getExternalContext().getRequest())).getContextPath()+"/ServletCierreSession.do");
		return propiedades;
	}
	
	
}
