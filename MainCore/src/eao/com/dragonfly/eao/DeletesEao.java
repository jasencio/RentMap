package com.dragonfly.eao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@LocalBean
@Stateless
public class DeletesEao   {
	
	@PersistenceContext(name="MainCore")
	private EntityManager ctx;	
	
	
	/*
	public void deleteExpBox(ExpBox expBox)
	{   ExpBox expBoxForDel =
		ctx.find(ExpBox.class,expBox.getNumProceso() );
		System.out.println(expBoxForDel.equals(null));
	ctx.remove(expBoxForDel);
		
	}*/

			
}
