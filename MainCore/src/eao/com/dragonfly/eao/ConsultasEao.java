package com.dragonfly.eao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dragonfly.ebusiness.Cliente;
import com.dragonfly.ebusiness.Producto;
import com.dragonfly.entities.Area;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;

@LocalBean
@Stateless
public class ConsultasEao  {
	
	@PersistenceContext(name="MainCore")
	private EntityManager ctx;
	


	
	
	
	
	
	public Usuario validaUsuario(Usuario Users){
		try{
		System.out.println("PRUEBAS"+Users.getUserName()+Users.getUserPass());
		Query q = ctx.createQuery("select a from Usuario a where a.userName = :usuario "
				+ "and a.userPass = :clave ", Usuario.class);
		q.setParameter("usuario", Users.getUserName());
		q.setParameter("clave", Users.getUserPass());
		
		
			
			return (Usuario) q.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}	
	}	
	
	@SuppressWarnings("unchecked")
	public List<Rol> traerRoles(Rol rol){
		try{
		
				
			Query q = ctx.createQuery("select b from Roles b "
									 + "where b.estado = ?1 " 
									 + "and   b.tipoRol = ?2 " );
			q.setParameter(1, "A");
			q.setParameter(2, rol.getTipoRol());
			
			
			return (List<Rol>) q.getResultList();
		}catch(NoResultException e){
			return null;
		}	
	}


	/**********************************************************/
	public List<Usuario> allUsers(){
		
			try{
				Query q = ctx.createQuery("select d from UsuariosJ d order by d.apellido");
				return q.getResultList();
			}catch(NoResultException e){
				e.printStackTrace();
				return null;
			}			
	}
	
	@SuppressWarnings("unchecked")
	public List<Rol> allRoles(){
		try{
			Query q = ctx.createQuery("select d from Roles d "
									 + "order by d.descripcion ");
			return (List<Rol>)q.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Opcion> allMenus(){
		
		try{
			Query q = ctx.createQuery("select d from Opcion d order by d.orden");
			return q.getResultList();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}			
}

	
	
public Rol findRol(Integer id) throws Exception{
	try{
	return ctx.find(Rol.class, id);
	}catch(NoResultException e){
		return null;
	}	
}


@SuppressWarnings("unchecked")
public List<Opcion> obtienePadres(Rol rol){
	Query q = ctx.createQuery("select a from Opcion a where a.idPadre = 0 and a.rol = :idRol order by a.orden", Opcion.class);
	q.setParameter("idRol", rol);
	
	try{	 
	return q.getResultList();
	}catch(NoResultException e) 
	{
		System.out.println(e.getMessage());
		return null;
	}	
}


@SuppressWarnings("unchecked")
public List<Opcion> obtieneHijos(Rol rol){
	
	Query q = ctx.createQuery("select a "
						      + "from Opcion a "
						     + "where a.idPadre != 0 "
						       + "and a.rol = :idRol "
						     + "order by a.orden");
	q.setParameter("idRol", rol);
	
	try{
	return q.getResultList();
	}catch(NoResultException e) 
	{
		System.out.println(e.getMessage());
		return null;
	}
	
}

public List<Producto> getProductos(){
	
	try{
		Query q = ctx.createQuery("select d from Producto d order by d.nombre");
		return q.getResultList();
	}catch(NoResultException e){
		e.printStackTrace();
		return null;
	}			
}

public List<Producto> getProductos(String nombreProducto){
	
	try{
		Query q = ctx.createQuery("select d from Producto d "
				+"where a.nombre like %:nombreProducto% "
				+ "order by d.nombre");
		q.setParameter("nombreProducto", nombreProducto);
		return q.getResultList();
	}catch(NoResultException e){
		e.printStackTrace();
		return null;
	}			
}

public Cliente getCliente(Cliente cliente){
	System.out.println("PRUEBAS"+cliente.getIdentificacion());
	try{
		Query q = ctx.createQuery("select d from Cliente d "
				+"where d.identificacion = :id");
		q.setParameter("id", cliente.getIdentificacion());
		return (Cliente)q.getSingleResult();
	}catch(NoResultException e){
		return null;
	}			
}


	
	
}
