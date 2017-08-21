package com.consejo.cdi.seguridad.interfaz;

import java.io.Serializable;



public interface IServiciosAutenticacion <T extends Serializable, F extends Serializable> 
{
	public T autenticar (F datosAutenticar) throws Exception;
}
