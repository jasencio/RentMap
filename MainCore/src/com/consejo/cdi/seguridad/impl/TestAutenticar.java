package com.consejo.cdi.seguridad.impl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.consejo.cdi.seguridad.anotaciones.ITestAutenticacion;
import com.consejo.cdi.seguridad.interfaz.IServiciosAutenticacion;
import com.dragonfly.bo.impl.ServicioAutenticacion;
import com.dragonfly.entities.Usuario;
import com.consejo.beans.session.LoginMB;

@ITestAutenticacion
@RequestScoped
public class TestAutenticar implements
		IServiciosAutenticacion<Usuario, LoginMB>, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ServicioAutenticacion autenticar;
	
	public Usuario autenticar(LoginMB datosAutenticar) throws Exception {
		Usuario usuario = null;
		
		try {
			usuario = autenticar
					.autenticar(
							datosAutenticar.getUsuario(),
									datosAutenticar.getClave());
		} catch (Throwable e) {
			throw new Exception(e);
		}
		return usuario;
	}

	
}
