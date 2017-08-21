package com.dragonfly.bo.impl;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.dragonfly.eao.ConsultasEao;
import com.dragonfly.entities.Usuario;





@LocalBean
@Stateless
public class ServicioAutenticacion {

	
	@Inject
	private ConsultasEao uvalidar;



	public Usuario autenticar(String ps_usuario, String ps_clave
			) throws Exception {
		

			System.out.println(ps_usuario + ps_clave);
			Usuario u = new Usuario();
			u.setUserName(ps_usuario);
			u.setUserPass(ps_clave);
			System.out.println(u.getUserName() + u.getUserPass());
			
			Usuario usuarioAuth = uvalidar.validaUsuario(u);

				if (usuarioAuth==null) {
					throw new Exception("Error de Autenticacion"); 
				}
				

				System.out.println("AUTENTICADO");
		return usuarioAuth;
	}
	

}
