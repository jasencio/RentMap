package com.consejo.cdi.seguridad.interfaz;

import java.io.Serializable;

public interface IUsuarioSession<T extends Serializable>
{

	public void setUsuarioSession(T usuario);
	
	public T getUsuarioSession();
	
}
