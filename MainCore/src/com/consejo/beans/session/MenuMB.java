package com.consejo.beans.session;

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
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.consejo.beans.generales.BeanGeneral;
import com.consejo.cdi.seguridad.anotaciones.ITestUsuarioSession;
import com.consejo.cdi.seguridad.interfaz.IUsuarioSession;
import com.dragonfly.eao.ConsultasEao;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.OpcionController;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;

@ManagedBean
@SessionScoped
public class MenuMB extends BeanGeneral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuModel model;
	private DefaultSubMenu subMenu;
	private DefaultMenuItem item;
	
	private String pageName ="/generales/bienvenida.xhtml";
	
	@Inject
	private ConsultasEao iConsultasEao;
	
	
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	
	@PostConstruct
	private void init(){
		loadMenu();
		
	}
	



	
	
	/*	
	public static DefaultMenuModel GenerarMenu(List<Opcion> secObjetos)
	{
		DefaultMenuModel menuList = new DefaultMenuModel();
		for (Opcion sObj : secObjetos)
		{
			//0 indica que se trata de un nodo padre
			if (sObj.getIdPadre() == 0) 
			{
				//uri para accesar una view bajo el patron mvc
			//	String urlAction = sObj.Controller + "/" + sObj.Action;
				//menuList.add(new MenuList(urlAction, sObj.NombreModulo, GenerarSubMenu(secObjetos, sObj)));
				
				List<OpcionController> opcionController = GenerarSubMenu(secObjetos, sObj);
				
				if(opcionController.getSubMenu()!=null)
				menuList.addElement(opcionController.getSubMenu());
				else
			    menuList.addElement(opcionController.getItem());	
			}
		}
		return menuList;
	}
	
	public static List<OpcionController> GenerarSubMenu(List<Opcion> secObjetos, Opcion sObj)
	{
		//OpcionController menuList = new OpcionController(new DefaultSubMenu(), new DefaultMenuItem() );
		
		List<OpcionController> opcionControllerList = new ArrayList<OpcionController>();
		
		
		for (Opcion objeto : secObjetos)
		{
			//la igualdad indica que objeto es hijo de sObj
			if (objeto.getIdPadre() == sObj.getId() && objeto.getTipo().equals("-"))
			{
				DefaultSubMenu defaultSubMenu = new DefaultSubMenu();
				//String urlAction = objeto.Controller + "/" + objeto.Action;		
				//OpcionController opcionController = GenerarSubMenu(secObjetos, objeto);
				defaultSubMenu.setLabel(objeto.getDescripcion());
				
				opcionControllerList.add(new OpcionController(defaultSubMenu,null)); 
				
				List<OpcionController> opcionController2 = GenerarSubMenu(secObjetos, objeto);
				if(opcionController2.getSubMenu()!=null)
					opcionController.setSubMenu(opcionController2.getSubMenu());
				else
					opcionController.setItem(opcionController2.getItem());
				
			
				
			}else{
				//OpcionController opcionController = GenerarSubMenu(secObjetos, objeto);
				DefaultMenuItem defaultMenuItem = new DefaultMenuItem();
				defaultMenuItem = new DefaultMenuItem(objeto.getDescripcion());		
				defaultMenuItem.setCommand("#{menuMB.navegar}");
				defaultMenuItem.setParam("PAGINA",objeto.getUrl());
				defaultMenuItem.setUpdate(":main-content");
				opcionController.setItem(defaultMenuItem);
			}
			
			
		}
		return opcionController;
	}
*/
	
	
	
	public void loadMenu(){
		System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEE");
		
		
			
	try{
		Rol rol = usuarioSession.getUsuarioSession().getRol();
		
		model = new DefaultMenuModel();
		System.err.println("-------0----------");
		
		List<Opcion> menuP = iConsultasEao.obtienePadres(rol);
		
		for (Opcion mP : menuP )
		{	
			System.err.println("--------1---------");
			subMenu = null;
			subMenu = new DefaultSubMenu(mP.getDescripcion());
			System.err.println("--------2---------");
				for (Opcion mH : iConsultasEao.obtieneHijos(rol) )
				{
					System.err.println("--------3---------");
					System.out.println("IdPadre"+mP.getId() + "IdHijo"+mH.getIdPadre());
					if(mH.getIdPadre().equals(mP.getId()))
					{
						System.err.println("--------4---------");
						item = new DefaultMenuItem(mH.getDescripcion());		
						item.setCommand("#{menuMB.navegar}");
						item.setParam("PAGINA",mH.getUrl());
						item.setUpdate(":main-content");
						
						subMenu.addElement(item);
					}
			 
			 		
				}
			model.addElement(subMenu);
			
		}
		pageName ="/generales/bienvenida.xhtml";
		
		 	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("Error en el MB "+e.toString());}
		
	
	}
	
	public void navegar(ActionEvent event){
		MenuItem menuItem = ((MenuActionEvent)event).getMenuItem();
		System.out.println("Devuelve:  "+menuItem.getParams().get("PAGINA").get(0));
		String urlStr = menuItem.getParams().get("PAGINA").get(0);
		System.out.println("Devuelve:  "+menuItem.getParams().get("PAGINA").get(0));
        this.pageName = urlStr;
	fullUpdate();
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	
	
	
}
