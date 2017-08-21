package com.dragonfly.eao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dragonfly.ebusiness.Cliente;
import com.dragonfly.ebusiness.DetalleFactura;
import com.dragonfly.ebusiness.Factura;
import com.dragonfly.ebusiness.Producto;
import com.dragonfly.entities.Area;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;
@LocalBean
@Stateless
public class InsertsEao    {
	
	@PersistenceContext(name="MainCore")
	private EntityManager ctx;	
	
	
	
	public void insertRol(Rol rol)throws Exception
	{
	
		if(rol!=null)
		ctx.persist(rol);
		
	}
	
	
	public void insertUsuario(Usuario userJ)throws Exception
	{
		
		if(userJ!=null)
		ctx.persist(userJ);
		
	}
	
	
	
	public void insertArea(Area juzgado)throws Exception
	{
		
		if(juzgado!=null)
		ctx.persist(juzgado);
		
	}
	
	
	
	public void insertOpciones(Opcion opcion)throws Exception
	{
	
		if(opcion!=null)
			ctx.persist(opcion);
		
		
		
	}
	

	public void insertProducto(Producto producto)throws Exception
	{
	
		if(producto!=null)
		ctx.persist(producto);
		
	}
	
	public Factura insertFactura(Factura factura)throws Exception
	{
	
		if(factura!=null)
		ctx.persist(factura);
		
		return factura;
		
	}
	
	public void insertDetalleFactura(DetalleFactura detalleFactura)throws Exception
	{
	
		if(detalleFactura!=null)
		ctx.persist(detalleFactura);
		
	}
	
	public Cliente insertCliente(Cliente cliente)throws Exception
	{
	
		if(cliente!=null)
		ctx.persist(cliente);
		return cliente;
	}
	
	
}
