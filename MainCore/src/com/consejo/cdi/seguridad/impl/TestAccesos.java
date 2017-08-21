package com.consejo.cdi.seguridad.impl;


import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class TestAccesos implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,String> hashMapAccesos ;
	
	@PostConstruct
	private void init(){
		hashMapAccesos = new HashMap<String, String>();
		hashMapAccesos.put("home.jsf", "Home");
	}
	

	public HashMap<String, String> getHashMapAccesos() {
		return hashMapAccesos;
	}

	public void setHashMapAccesos(HashMap<String, String> hashMapAccesos) {
		this.hashMapAccesos = hashMapAccesos;
	}

	
	

}
