package com.consejo.beans.business;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.dragonfly.ebusiness.Producto;

@FacesConverter("productoConverter")
public class ProductoConverter implements Converter{

	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                ProductoService service = (ProductoService) fc.getExternalContext().getApplicationMap().get("productoService");
                
                for(Producto p : service.getProductos()) 
                  {
                	if(p.getIdProducto()== Integer.parseInt(value))
                		return p;
                  }
                return null;
                }catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Producto."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Producto) object).getIdProducto());
        }
        else {
            return null;
        }
    }   
	
}
