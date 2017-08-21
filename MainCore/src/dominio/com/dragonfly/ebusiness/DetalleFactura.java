package com.dragonfly.ebusiness;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="H_DETALLE_FACTURA")
@IdClass (IdDetalleFactura.class)  
public class DetalleFactura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_DETALLE_FACTURA")
	private Integer idDetalleFactura;
	
	@Id
	@Column(name="ID_FACTURA")
	private Integer idFactura;

	@ManyToOne
	private Producto producto;
	
	@Column(name="CANTIDAD")
	private Integer cantidad;
	
	@Column(name="PRECIO_U")
	private Double precioUnitario;
	
	@Column(name="PRECIO_T")
	private Double precioTotal;
	
	@OneToMany(targetEntity=Factura.class)
	private List<Factura> facturaList;


	
	public Integer getIdDetalleFactura() {
		return idDetalleFactura;
	}

	public void setIdDetalleFactura(Integer idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public List<Factura> getFacturaList() {
		return facturaList;
	}

	public void setFacturaList(List<Factura> facturaList) {
		this.facturaList = facturaList;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}


	
	

	
}
