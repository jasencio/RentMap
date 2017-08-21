package com.consejo.cdi.general.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import com.consejo.cdi.general.anotaciones.ITestGuardiaSession;
import com.consejo.cdi.general.interfaz.IGuardiaUsuarioSession;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.consejo.util.general.UtilCryptography;
import com.dragonfly.entities.Usuario;

@ITestGuardiaSession
@RequestScoped
public class TestGuardiaUsuarioSession implements IGuardiaUsuarioSession {
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	public boolean usuarioEnSession() {

		if (usuarioSession == null
				|| usuarioSession.getUsuarioSession() == null) {
			return false;
		}

		return true;

	}

	public boolean validarSemilla(String semilla) {
		try {
			String semillaReal = UtilCryptography.encriptar(usuarioSession
					.getUsuarioSession().getUserPass().toUpperCase()
					+ usuarioSession.getUsuarioSession().getUserName());
			return semillaReal.equals(semilla);
		} catch (Exception e) {

			return false;
		}
	}

	public String nombreUsuarioSession() {

		return (usuarioSession != null && usuarioSession.getUsuarioSession() != null) ? usuarioSession
				.getUsuarioSession().getUserName() : "";

	}

	
}
