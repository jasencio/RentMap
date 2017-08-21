package com.consejo.cdi.seguridad.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.dragonfly.entities.Usuario;


@ITestUsuarioSession
@SessionScoped
public class TestUsuarioSession implements IUsuarioSession<Usuario>, Serializable
{
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public void setUsuarioSession(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioSession() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

}
