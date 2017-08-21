package com.dragonfly.ebusiness;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="H_PRODUCTO")
public class Producto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDPRODUCTO")
	private Integer idProducto;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="PRECIO")
	private Double precio;
	@Column(name="STOCK")
	private Integer stock;
	@Column(name="ESTADO")
	private String estado;
	
	@OneToMany(targetEntity=DetalleFactura.class)
	private List<DetalleFactura> detalleFacturaList;
	
	
	
	
	public Producto() {}
	
	
	
	public Producto(Integer idProducto, String nombre, Double precio, Integer stock, String estado) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
	}



	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public List<DetalleFactura> getDetalleFacturaList() {
		return detalleFacturaList;
	}
	public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
		this.detalleFacturaList = detalleFacturaList;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
    public String toString() {
        return nombre;
    }
	
}
