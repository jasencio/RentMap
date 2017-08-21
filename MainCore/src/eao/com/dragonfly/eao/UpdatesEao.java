package com.dragonfly.eao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dragonfly.ebusiness.Producto;
import com.dragonfly.entities.Area;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;
@LocalBean
@Stateless
public class UpdatesEao  {
	
	@PersistenceContext(name="MainCore")
	private EntityManager ctx;
	
	
	
	

	
	public void updateRoles(Rol rol)// 2
	{
		Rol rolUpD = ctx.find(Rol.class, rol.getIdRol());
		if ( rol.getDescripcion() != null)
			rolUpD.setDescripcion(rol.getDescripcion());
		if ( rol.getEstado() != null)
			rolUpD.setEstado(rol.getEstado());
		if ( rol.getIdRol() != null)
			rolUpD.setIdRol(rol.getIdRol());
		if ( rol.getTipoRol() != null)
			rolUpD.setTipoRol(rol.getTipoRol());
		if(rolUpD!=null)
		ctx.merge(rolUpD);
	}
	
	
	
	
	public void updateUsuario(Usuario usuario)// 3
	{

		Usuario userJUpD = ctx.find(Usuario.class, usuario.getIdUsuario());
		if (usuario.getApellido() != null)
			userJUpD.setApellido(usuario.getApellido());
		if (usuario.getArea() != null)
			userJUpD.setArea(usuario.getArea());
		if (usuario.getArea() != null)
			userJUpD.setArea(usuario.getArea());
		if (usuario.getIdUsuario() != null)
			userJUpD.setIdUsuario(usuario.getIdUsuario());
		if (usuario.getNombre() != null)
			userJUpD.setNombre(usuario.getNombre());
		if (usuario.getUserName() != null)
			userJUpD.setUserName(usuario.getUserName());
		if (usuario.getUserPass() != null)
			userJUpD.setUserPass(usuario.getUserPass());
		if (usuario.getEstado() != null)
			userJUpD.setEstado(usuario.getEstado());
		if(userJUpD!=null)
		ctx.merge(userJUpD);

	}
	
	
	public void updateArea(Area area)// 4
	{

		Area areaUpD = ctx.find(Area.class, area.getIdArea());
		
		if (area.getEstado() != null)
			areaUpD.setEstado(area.getEstado());
		if (area.getDescripArea() != null)
			areaUpD.setDescripArea(area.getDescripArea());
		if (area.getNombreArea() != null)
			areaUpD.setNombreArea(area.getNombreArea());
		if (area.getIdArea() != null)
			areaUpD.setIdArea(area.getIdArea());
		
		if(areaUpD!=null)
		ctx.merge(areaUpD);

	}

	
	public void updateOpcion(Opcion opcion) // 6
	{
		Opcion opcionUpD = ctx.find(Opcion.class, opcion.getId());
		
		if (opcion.getId() != null)
			opcionUpD.setId(opcion.getId());
		if (opcion.getDescripcion() != null)
			opcionUpD.setDescripcion(opcion.getDescripcion());
		if (opcion.getIdPadre() != null)
			opcionUpD.setIdPadre(opcion.getIdPadre());
		if (opcion.getRol() != null)
			opcionUpD.setRol(opcion.getRol());
		if (opcion.getOrden() != null)
			opcionUpD.setOrden(opcion.getOrden());
		if (opcion.getUrl() != null)
			opcionUpD.setUrl(opcion.getUrl());

		if(opcionUpD!=null)
		ctx.merge(opcionUpD);
	}

	
	
	

	
	public boolean updateUsuarioPass(Usuario user, String newPassWord) {
		Usuario userToUp = ctx.find(Usuario.class, user.getIdUsuario());

		if (userToUp.getUserPass().equalsIgnoreCase(user.getUserPass())) {
			userToUp.setUserPass(newPassWord);
			ctx.merge(userToUp);
			return true;
		} else {
			return false;
		}

	}
	
	public void updateProducto(Producto producto)// 2
	{
		Producto productoUpD = ctx.find(Producto.class, producto.getIdProducto());
		if ( producto.getNombre() != null)
			productoUpD.setNombre(producto.getNombre());
		if ( producto.getPrecio() != null)
			productoUpD.setPrecio(producto.getPrecio() );
		if ( producto.getStock() != null)
			productoUpD.setStock(producto.getStock());
		if ( producto.getEstado() != null)
			productoUpD.setEstado(producto.getEstado());
		if(productoUpD!=null)
		ctx.merge(producto);
	}

	
	
}


