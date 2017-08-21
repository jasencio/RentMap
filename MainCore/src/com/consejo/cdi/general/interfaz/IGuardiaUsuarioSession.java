package com.consejo.cdi.general.interfaz;


public interface IGuardiaUsuarioSession 
{
	public static final String PAGINA_LOGIN="/login.jsf";
	public static final String PAGINA_PRINCIPAL="home.jsf";
	public static final String SEMILLA="SEMILLA";
	
	public boolean usuarioEnSession ();	
	public boolean validarSemilla(String semilla);
	public String nombreUsuarioSession();
	
}
