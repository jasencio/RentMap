package com.dragonfly.entities;

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
@Table(name="H_ROLES")
public class Rol implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROL")
	private Integer idRol;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="TIPO_ROL")
	private String tipoRol;
	@Column(name="ESTADO")
	private String estado;
	
	@OneToMany(targetEntity=Usuario.class)
	private List<Usuario> usarioList;
	
	@OneToMany(targetEntity=Opcion.class)
	private List<Opcion> opcionList;
	
	
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoRol() {
		return tipoRol;
	}
	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<Usuario> getUsarioList() {
		return usarioList;
	}
	public void setUsarioList(List<Usuario> usarioList) {
		this.usarioList = usarioList;
	}
	public List<Opcion> getOpcionList() {
		return opcionList;
	}
	public void setOpcionList(List<Opcion> opcionList) {
		this.opcionList = opcionList;
	}

	
	
	
}
