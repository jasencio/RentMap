package com.consejo.beans.business;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.dragonfly.eao.ConsultasEao;
import com.dragonfly.ebusiness.Producto;

@ManagedBean(name="productoService", eager = true)
@ApplicationScoped
public class ProductoService {

    private List<Producto> productos;
    
	@Inject
	private ConsultasEao iConsultasEao;
    
    @PostConstruct
    public void init() {
    	productos = iConsultasEao.getProductos();
    }
     
    public List<Producto> getProductos() {
        return productos;
    } 
	
}
