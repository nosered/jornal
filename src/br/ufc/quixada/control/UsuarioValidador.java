package br.ufc.quixada.control;

import java.util.regex.Pattern;

import javax.inject.Inject;

import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.ufc.quixada.model.Usuario;

public class UsuarioValidador {
	private final String NOME_REGEX = "^[a-zA-Z]+\\s*[a-zA-Z]*\\s*[a-zA-Z]*\\s*[a-zA-Z]*\\s*[a-zA-Z]*\\s*[a-zA-Z]*\\s*[a-zA-Z]*$";
	private final String EMAIL_REGEX = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+$";
	private final String LOGIN_REGEX = "^([a-zA-Z]+[a-zA-Z0-9]*){4,20}$";
	@Inject private Validator validador;
	
	public void validarFormulario(Usuario usuario){
		String erroNome = "deve conter apenas letras";
		String erroEmail = "deve ter um formato válido";
		String erroLogin = "deve iniciar com letras e possuir no mínimo 4 caracteres";
		
		boolean nomeValido = Pattern.compile(NOME_REGEX).matcher(usuario.getNome()).matches();
		boolean emailValido = Pattern.compile(EMAIL_REGEX).matcher(usuario.getNome()).matches();
		boolean loginValido = Pattern.compile(LOGIN_REGEX).matcher(usuario.getNome()).matches();
		
		validador.ensure(nomeValido, new SimpleMessage("usuario.nome.invalido", erroNome, Severity.ERROR));
		validador.ensure(emailValido, new SimpleMessage("usuario.email.invalido", erroEmail, Severity.ERROR));
		validador.ensure(loginValido, new SimpleMessage("usuario.login.invalido", erroLogin, Severity.ERROR));
		
		validador.onErrorUsePageOf(UsuarioController.class).formularioLeitor();
	}
	
	public void validarLogin(Usuario usuario){
		String erroLogin = "deve iniciar com letras e possuir no mínimo 4 caracteres";
		
		boolean loginValido = Pattern.compile(LOGIN_REGEX).matcher(usuario.getNome()).matches();
		
		validador.ensure(loginValido, new SimpleMessage("usuario.nome.invalido", erroLogin, Severity.ERROR));
		validador.onErrorUsePageOf(UsuarioController.class).formularioLeitor();
	}
	
	public void confirmar(){
		validador.add(new SimpleMessage("usuario.sucesso", "Usuário foi adicionado com sucesso!", Severity.SUCCESS));
	}
}