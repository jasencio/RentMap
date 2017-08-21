package com.consejo.beans.business;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
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
import com.dragonfly.ebusiness.Cliente;
import com.dragonfly.ebusiness.DetalleFactura;
import com.dragonfly.ebusiness.Factura;
import com.dragonfly.ebusiness.Producto;
import com.dragonfly.entities.Opcion;
import com.dragonfly.entities.OpcionController;
import com.dragonfly.entities.Rol;
import com.dragonfly.entities.Usuario;

@ManagedBean
@ViewScoped
public class FacturacionMB extends BeanGeneral implements Serializable{

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
	
	@ManagedProperty("#{productoService}")
	private ProductoService service;
	
	
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	private Factura factura;
	
	private List<DetalleFactura> detalleFacturaList;
	
	private DetalleFactura detalleFactura;
	
	private List<Producto> productosList;
	
	private List<Producto> selectedProductos;
	
	private Producto selectedProducto;
	
	private List<Producto> productos;
	
	private Cliente cliente;
	
	private DetalleFactura selectedDetalleFac;
	
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	private String datosCliente;
	
	private Boolean habilitarCliente;
	private Boolean habilitarIdCliente;
	
	@PostConstruct
	private void init(){
		//detalleFacturaList = iConsultasEao.getProductos();
		selectedDetalleFac = new DetalleFactura();
		cliente = new Cliente();
		detalleFactura=new DetalleFactura();
		detalleFactura.setProducto(new Producto());
		detalleFacturaList =  new ArrayList<DetalleFactura>();
		selectedProductos= new ArrayList<Producto>(); 
		factura = new Factura();
		factura.setSubtotal(0.00);
		factura.setImpuestos(0.00);
		factura.setTotal(0.00);
		productos = service.getProductos();
		habilitarCliente= true;
		habilitarIdCliente= false;
		
	}
	
	public void eliminarDetalle(){
		factura.setSubtotal(Double.valueOf(df2.format(factura.getSubtotal()-selectedDetalleFac.getPrecioTotal()).replace(",", ".")));
		factura.setImpuestos(Double.valueOf(df2.format(factura.getSubtotal()*0.12).replace(",", ".")));
		factura.setTotal(Double.valueOf(df2.format(factura.getSubtotal()+factura.getImpuestos()).replace(",", ".")));
		detalleFacturaList.remove(selectedDetalleFac);
	}
	
	public void guardarDetalle()
	{
		
		
		try {
			factura.setSubtotal(Double.valueOf(df2.format(factura.getSubtotal()+detalleFactura.getPrecioTotal()).replace(",", ".")));
			factura.setImpuestos(Double.valueOf(df2.format(factura.getSubtotal()*0.12).replace(",", ".")));
			factura.setTotal(Double.valueOf(df2.format(factura.getSubtotal()+factura.getImpuestos()).replace(",", ".")));
			detalleFacturaList.add(detalleFactura);
			
			detalleFactura = new DetalleFactura();
			
			selectedProducto = new Producto();
			
		} catch (Exception e) {
			System.out.println("Control");
			e.printStackTrace();
		}
	};
	

	public List<Producto> completeProducto(String query) {
        List<Producto> allProductos = service.getProductos();
        List<Producto> filteredProductos = new ArrayList<Producto>();
         
        for (Producto p: allProductos) {
           // Producto producto = allProductos.get(i);
            if(p.getNombre().toUpperCase().startsWith(query)) {
            	filteredProductos.add(p);
            }
        }
         
        return filteredProductos;
    }
	
	public void onItemSelect(SelectEvent event)
	{
		Producto p = (Producto)event.getObject();
		detalleFactura.setPrecioUnitario(p.getPrecio());
		detalleFactura.setPrecioTotal(p.getPrecio());
		detalleFactura.setProducto(p);
		detalleFactura.setCantidad(1);
		
		
		
		System.out.println(p.getPrecio()+"   "+p.getNombre()+"  "+p.getIdProducto());
	}
	
	public void onChangeCantidad()
	{
	  if (detalleFactura.getPrecioUnitario()!= null && detalleFactura.getCantidad()!=null)
		  detalleFactura.setPrecioTotal(Double.valueOf(df2.format(detalleFactura.getPrecioUnitario()*detalleFactura.getCantidad()).replace(",", ".")));
	}
	
	public void grabarFactura()
	{   int i = 0;
		try {
			
			cliente = iInsertsEao.insertCliente(cliente);
			
			Factura factura = new Factura();
			factura.setCliente(cliente);
			factura.setFecha(new Date());
			
			factura = iInsertsEao.insertFactura(factura);
			
		for (DetalleFactura df : detalleFacturaList){
		        i++;
		        df.setIdFactura(factura.getIdFactura());
			    df.setIdDetalleFactura(i);
				iInsertsEao.insertDetalleFactura(df);
			
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tipoCliente(){
		if(datosCliente.equalsIgnoreCase("F")){	
			cliente.setIdentificacion("9999999999");
			cliente = iConsultasEao.getCliente(cliente);
			habilitarCliente = true;
			habilitarIdCliente = true;
		}else{
			cliente = new Cliente();
			habilitarCliente = true;
			habilitarIdCliente = false;
			}
		
	}
	
	public void buscarCliente(){
		Cliente cliente = iConsultasEao.getCliente(this.cliente);
		if (cliente != null)
			{this.cliente = cliente;
			habilitarCliente = true;
			habilitarIdCliente = false;
			addMensaje("Advertencia", "Cliente encontrado.");
			}
		else
			{habilitarCliente = false;
			habilitarIdCliente = true;
			addMensaje("Advertencia", "Ingrese datos del cliente.");
			}
		
	}
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<DetalleFactura> getDetalleFacturaList() {
		return detalleFacturaList;
	}

	public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
		this.detalleFacturaList = detalleFacturaList;
	}

	public DetalleFactura getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public List<Producto> getProductosList() {
		return productosList;
	}

	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}

	public List<Producto> getSelectedProductos() {
		return selectedProductos;
	}

	public void setSelectedProductos(List<Producto> selectedProductos) {
		this.selectedProductos = selectedProductos;
	}

	public ProductoService getService() {
		return service;
	}

	public void setService(ProductoService service) {
		this.service = service;
	}

	public Producto getSelectedProducto() {
		return selectedProducto;
	}

	public void setSelectedProducto(Producto selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DetalleFactura getSelectedDetalleFac() {
		return selectedDetalleFac;
	}

	public void setSelectedDetalleFac(DetalleFactura selectedDetalleFac) {
		this.selectedDetalleFac = selectedDetalleFac;
	}

	public String getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(String datosCliente) {
		this.datosCliente = datosCliente;
	}

	public Boolean getHabilitarCliente() {
		return habilitarCliente;
	}

	public void setHabilitarCliente(Boolean habilitarCliente) {
		this.habilitarCliente = habilitarCliente;
	}

	public Boolean getHabilitarIdCliente() {
		return habilitarIdCliente;
	}

	public void setHabilitarIdCliente(Boolean habilitarIdCliente) {
		this.habilitarIdCliente = habilitarIdCliente;
	}




	



	
	

	
	
	
}
