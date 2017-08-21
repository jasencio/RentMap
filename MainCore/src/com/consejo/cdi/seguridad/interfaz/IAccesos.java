package com.consejo.cdi.seguridad.interfaz;

import java.io.Serializable;
import java.util.HashMap;

public interface IAccesos<T extends Serializable>
{

	public void setHashMapAccesos(HashMap<String,String> hashMapAccesos);
	
	public T getHashMapAccesos();
	
}
