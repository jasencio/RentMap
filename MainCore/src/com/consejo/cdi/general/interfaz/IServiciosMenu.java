package com.consejo.cdi.general.interfaz;

import java.io.Serializable;
import java.util.List;


public interface IServiciosMenu<T extends Serializable> 
{
	public List<T>	getMenuOpciones ();
}
