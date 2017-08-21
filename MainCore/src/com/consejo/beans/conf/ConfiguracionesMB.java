package com.consejo.beans.conf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;//faces
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.consejo.beans.generales.BeanGeneral;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.dragonfly.eao.UpdatesEao;
import com.dragonfly.entities.Usuario;

@ManagedBean
@RequestScoped
public class ConfiguracionesMB extends BeanGeneral implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oldPass;
	private String newPass;
	private String newPass2;

	@Inject
	private UpdatesEao updateDatos;

	
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	
	public void ChangePass() {



		if (oldPass.isEmpty() || newPass.isEmpty() || newPass2.isEmpty()) {
			mostrarAdvertenciaDialog("Info", "Llenar todos los campos.");
			
		} else {
			if (newPass.equals(newPass2)) {
				Usuario user = new Usuario();
				user.setUserName(usuarioSession.getUsuarioSession().getUserName());
				user.setIdUsuario(usuarioSession.getUsuarioSession().getIdUsuario());
				user.setUserPass(oldPass);
				if (updateDatos.updateUsuarioPass(user, newPass)) {
					mostrarErrorDialog("Info",
							"Contrase�a cambiada exitosamente.");
				} else {
					mostrarAdvertenciaDialog("Info",
							"Ah ingresado mal la contrase�a anterior.");
				}
			} else {
				mostrarAdvertenciaDialog("Info",
						"No ah vuelto a ingresar la contrase�a correctamente.");
			}

		}
		oldPass = null;
		newPass = null;
		newPass2 = null;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewPass2() {
		return newPass2;
	}

	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}

}