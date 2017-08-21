package com.dragonfly.ebusiness;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="H_FACTURA")
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUMFACTURA")
	private Integer idFactura;
	@ManyToOne
	private Cliente cliente;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="SUBTOTAL")
	private Double subtotal;
	@Column(name="IMPUESTOS")
	private Double impuestos;
	@Column(name="SUBTOTALIVA")
	private Double total;
	
	

	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	

	
}
