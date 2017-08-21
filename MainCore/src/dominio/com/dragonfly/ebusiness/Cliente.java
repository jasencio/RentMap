package com.dragonfly.ebusiness;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="H_CLIENTE")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDENTIFICACION")
	private String identificacion;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	@Column(name="DIRECCION")
	private String direccion;
	@Column(name="TELEFONO")
	private String telefono;
	@Column(name="MAIL")
	private String mail;
	
	@OneToMany(targetEntity=Factura.class)
	private List<Factura> facturaList;
	
	

	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<Factura> getFacturaList() {
		return facturaList;
	}
	public void setFacturaList(List<Factura> facturaList) {
		this.facturaList = facturaList;
	}
	
	



	
	
	
	
	
	
}
