package com.dragonfly.ebusiness;

import java.io.Serializable;
import java.util.Objects;

public class IdDetalleFactura implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idDetalleFactura;  

	private Integer idFactura;  
	
	 public IdDetalleFactura() {
	 }

	
	 public IdDetalleFactura(Integer idDetalleFactura, Integer idFactura) {
		super();
		this.idDetalleFactura = idDetalleFactura;
		this.idFactura = idFactura;
	}
	 
	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 59 * hash + Objects.hashCode(this.idDetalleFactura);
	        hash = 59 * hash + Objects.hashCode(this.idFactura);
	        return hash;
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final IdDetalleFactura other = (IdDetalleFactura) obj;
	        if (!Objects.equals(this.idDetalleFactura, other.idDetalleFactura)) {
	            return false;
	        }
	        if (!Objects.equals(this.idFactura, other.idFactura)) {
	            return false;
	        }
	        return true;
	    }

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
	 
	 

	 
	 
}
