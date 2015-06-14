package br.ufc.quixada.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.SimpleBrutauthRule;

@RequestScoped
@HandledBy(AutorizacaoHandler.class)
public class Autorizacao implements SimpleBrutauthRule{
	
	@Inject
	private UsuarioSessao usuarioAutenticado;

	public boolean isAllowed(long nivel) {
		if(usuarioAutenticado==null) return false;
		return usuarioAutenticado.isPermitido(nivel);
	}

}
