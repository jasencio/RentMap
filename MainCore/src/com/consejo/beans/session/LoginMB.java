package com.consejo.beans.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;//faces
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.consejo.beans.generales.BeanGeneral;
import com.consejo.cdi.ConfiguracionBeansGenerales;
import com.consejo.cdi.seguridad.anotaciones.ITestAutenticacion;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IServiciosAutenticacion;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.consejo.util.general.UtilCryptography;
import com.dragonfly.eao.ConsultasEao;
import com.dragonfly.eao.UpdatesEao;
import com.dragonfly.entities.Area;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;
import com.consejo.cdi.general.interfaz.IGuardiaUsuarioSession;

@ManagedBean
@SessionScoped
public class LoginMB extends BeanGeneral implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String usuario;
	private String clave;
	
	private Usuario mainuser;
	
	@PostConstruct
	public void init() {
		usuario = "";
		clave = "";
		}
	
	
	@Inject
	private UpdatesEao updateDatos;

	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	@Inject
	@ITestAutenticacion
	private IServiciosAutenticacion<Usuario, LoginMB> autenticador;
	
	@Inject
	private ConsultasEao traerDatos;
	
	private List<Rol>   listaRoles;
	private List<Area>  listaArea;
	
	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public String autenticar() {
		try {

			Usuario usuarioToAuth = autenticador.autenticar(this);
			setUsuario(usuarioToAuth.getUserName());
			setClave(usuarioToAuth.getUserPass());
			usuarioSession.setUsuarioSession(usuarioToAuth);
			generarSemillaSegura(usuarioToAuth);
			System.out.println("/*****/"+usuarioSession.getUsuarioSession().getUserName());
		//	mostrarJuzgados();
			
			mainuser = usuarioSession.getUsuarioSession();
			return getPropiedadesGenerales().getProperty(
					ConfiguracionBeansGenerales.PAGINA_INICIAL);
		} catch (Exception e) {
			addMensaje("Error Autenticaci\u00f3n:",
					"USUARIO/CLAVE Inv\u00e1lidos");
		}

		return "";
	}

	private void generarSemillaSegura(Usuario usuario) {
		try {
			
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).setAttribute(
					IGuardiaUsuarioSession.SEMILLA, UtilCryptography
							.encriptar(usuario.getUserName()));
		} catch (Throwable e) {
			//e.printStackTrace();
		}
	}

	public void incioSeguro() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object session = externalContext.getSession(false);
		HttpSession httpSession = (HttpSession) session;
		httpSession.invalidate();

	}

	
	
	
	
	public void logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).invalidate();
		// loggedIn = false;
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/dragonfly/login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public void mostrarRoles() throws Exception{
		
		
	 setListaRoles(traerDatos.traerRoles(traerDatos.findRol(cargo)));
	}*/
	
	
/*	public void mostrarJuzgados(){
		 setListaJuzgados(traerDatos.allJusgados());
		}*/
	
/*	public void cambiarRol() {

		usuarioSession.getUsuarioSession().setIdCargo(cargo);
		
		
	}*/

/*	public void cambiarJuzgado() {
		usuarioSession.getUsuarioSession().setIdJuzgado(juzgado);
		Usuario userJ = new  Usuario();
		userJ.setIdUsuario(usuarioSession.getUsuarioSession().getIduser());
		userJ.setIdJuzgado(juzgado);
		updateDatos.updateUsuarioJ(userJ);
	
	}*/

	public String reset() {
		usuario = "";
		clave = "";
		return "";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getMainuser() {
		return mainuser;
	}

	public void setMainuser(Usuario mainuser) {
		this.mainuser = mainuser;
	}

	

}