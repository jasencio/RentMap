package com.dragonfly.entities;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;

public class OpcionController {

	private DefaultSubMenu subMenu;
	private DefaultMenuItem item;
	
	
	
	public OpcionController() {
		super();
	}


	public OpcionController(DefaultSubMenu subMenu, DefaultMenuItem item) {
		super();
		this.subMenu = subMenu;
		this.item = item;
	}
	
	
	public DefaultSubMenu getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(DefaultSubMenu subMenu) {
		this.subMenu = subMenu;
	}
	public DefaultMenuItem getItem() {
		return item;
	}
	public void setItem(DefaultMenuItem item) {
		this.item = item;
	}
	
	
	
	
	
}
