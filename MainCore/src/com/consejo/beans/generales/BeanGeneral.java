package com.consejo.beans.generales;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class BeanGeneral implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static void fullUpdate(){
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		ViewHandler handler = context.getApplication().getViewHandler();
		UIViewRoot root = handler.createView(context, viewId);
		root.setViewId(viewId);
		context.setViewRoot(root);
	}
	
	/**
	 * 
	 */

	private Properties propiedadesGenerales;

	
	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	protected HttpSession getsession() {

		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getContext().getExternalContext()
				.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getContext().getExternalContext()
				.getResponse();
	}

	public void addMensaje(String msg) {
		addMensaje(msg, FacesMessage.SEVERITY_INFO);
	}

	public void addError(String msg) {
		addMensaje(msg, FacesMessage.SEVERITY_ERROR);
	}

	public void addMensaje(String msg, String detail) {
		addMensaje(msg, detail, FacesMessage.SEVERITY_INFO);
	}

	public void addError(String msg, String detail) {
		addMensaje(msg, detail, FacesMessage.SEVERITY_ERROR);
	}

	private void addMensaje(String msg, Severity severity) {
		FacesMessage message = new FacesMessage(msg);
		message.setSeverity(severity);
		FacesContext ctx = getContext();
		ctx.addMessage(null, message);
	}

	private void addMensaje(String msg, String detail, Severity severity) {
		FacesMessage message = new FacesMessage(msg, detail);
		message.setSeverity(severity);
		FacesContext ctx = getContext();
		ctx.addMessage(null, message);
	}



	protected HashMap<String, Object> getAtributosVisibles() {
		@SuppressWarnings("rawtypes")
		Class clase = this.getClass();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		try {

			Method[] metodos = clase.getMethods();
			for (Method metodo : metodos) {
				String nombreMetodo = metodo.getName();
				if (nombreMetodo.toUpperCase().indexOf("GET") != -1) {
					Object obj = metodo.invoke(this);
					if (obj != null) {
						parametros.put(nombreMetodo.toUpperCase(), obj);
					}
				}
			}
		} catch (Throwable e) {
			//e.printStackTrace();
		}
		return parametros;
	}

	protected Properties getPropiedadesGenerales() {
		return propiedadesGenerales;
	}

	@Inject
	protected void setPropiedadesGenerales(Properties propiedadesGenerales) {
		this.propiedadesGenerales = propiedadesGenerales;
	}

	
	protected String getIp() {
		String ls_ip = getRequest().getHeader("CLIENTIP");
			
		if (ls_ip == null) {
			ls_ip = getRequest().getRemoteAddr();
		}
		
		return ls_ip;
	}
	

	
	protected String getServerIp(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			
			return "127.0.0.1";
		}
	}

	/**
	 * Modificado por: CLS Joel Alvarado T. Fecha Modificacion: 09/07/2014
	 * Motivo : Se agregan metodos generales para su uso en managedbeans
	 */

	/**
	 * 
	 * @return
	 */
	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	/**
	 * 
	 * @param msg
	 * @param detail
	 */

	protected void mostrarMensajeDialog(String msg, String detail) {
		mostrarMensajeDialog(msg, detail, FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 
	 * @param msg
	 * @param detail
	 */
	protected void mostrarAdvertenciaDialog(String msg, String detail) {
		mostrarMensajeDialog(msg, detail, FacesMessage.SEVERITY_WARN);
	}

	/**
	 * 
	 * @param msg
	 * @param detail
	 */
	protected void mostrarErrorDialog(String msg, String detail) {
		mostrarMensajeDialog(msg, detail, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * 
	 * @param msg
	 * @param detail
	 * @param severity
	 */
	private void mostrarMensajeDialog(String msg, String detail,
			Severity severity) {
		FacesMessage message = new FacesMessage(severity, msg, detail);
		getRequestContext().showMessageInDialog(message);
	}
	
	/**
	 * 
	 * @param componentId
	 */
	protected void actualizarComponente(String componentId){
		getRequestContext().update(componentId);
	}
	
	/**
	 * 
	 * @param comando
	 */
	protected void ejecutarEnContexto(String comando){
		getRequestContext().execute(comando);
	}
	
	/**
	 * 
	 * @param url
	 */
	protected void mostrarEnDialog(String url){
		getRequestContext().openDialog(url);
	}
	
	/**
	 * 
	 * @param url
	 * @param propiedades
	 */
	protected void mostrarEnDialog(String url,Map< String, Object> propiedades){
		getRequestContext().openDialog(url, propiedades, null);
	}
	
	protected void cerrarDialog(Object arg0){
		getRequestContext().closeDialog(arg0);
	}

	
}
