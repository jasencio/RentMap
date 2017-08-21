package com.consejo.beans.mantenimiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.consejo.beans.generales.BeanGeneral;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.dragonfly.eao.ConsultasEao;
import com.dragonfly.eao.InsertsEao;
import com.dragonfly.eao.UpdatesEao;
import com.dragonfly.ebusiness.Producto;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.OpcionController;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;

@ManagedBean
@ViewScoped
public class ProductosMB extends BeanGeneral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConsultasEao iConsultasEao;
	
	@Inject
	private InsertsEao iInsertsEao;
	
	@Inject
	private UpdatesEao iUpdatesEao;
	
	
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	private Producto producto = new Producto();
	
	private List<Producto> productoList;
	
	
	@PostConstruct
	private void init(){
		productoList = iConsultasEao.getProductos();
		
	}
	
	public void guardarProducto()
	{
		try {
			iInsertsEao.insertProducto(producto);
			producto = new Producto();
			init();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public void onRowEditProducto(RowEditEvent event)
	{
		
		Producto producto = (Producto)event.getObject();
		iUpdatesEao.updateProducto(producto);
		init();
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public List<Producto> getProductoList() {
		return productoList;
	}



	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}
	



	
	

	
	
	
}
