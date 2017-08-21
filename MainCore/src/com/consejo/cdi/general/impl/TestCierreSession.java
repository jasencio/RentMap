package com.consejo.cdi.general.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.consejo.cdi.general.anotaciones.ITestCierreSession;
import com.consejo.cdi.general.interfaz.ICierreSession;
import com.dragonfly.bo.impl.ServicioAutenticacion;

@ITestCierreSession
@RequestScoped
public class TestCierreSession implements ICierreSession 
{
	@Inject
	private  ServicioAutenticacion servicioAutenticador;
	
	public void cerrarSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object session = externalContext.getSession(false);
		HttpSession httpSession = (HttpSession) session;
		httpSession.invalidate();
	}
	
}
