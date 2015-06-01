package br.ufc.quixada.control;

import javax.inject.Inject;

import br.com.caelum.vraptor.simplevalidator.CustomValidationStrategy;
import br.com.caelum.vraptor.simplevalidator.ValidationStrategyHelper;
import br.ufc.quixada.model.Usuario;

public class UsuarioValidator implements CustomValidationStrategy<Usuario>{

	private final int MAX_NOME = 255;
	private final int MIN_NOME = 4;
	@Inject
	private ValidationStrategyHelper helper;
	
	public void addErrors(Usuario usuario) {
		if(usuario.getNome().length() > MAX_NOME || usuario.getNome().length() < MIN_NOME)
			helper.addError("usuario.invalido", usuario);
	}
}
