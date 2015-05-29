package br.ufc.quixada.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.rules.SimpleBrutauthRule;

@RequestScoped
public class Autorizacao implements SimpleBrutauthRule{
	
	@Inject
	private UsuarioAutenticado usuarioAutenticado;

	public boolean isAllowed(long nivel) {
		return usuarioAutenticado.isPermitido(nivel);
	}

}
