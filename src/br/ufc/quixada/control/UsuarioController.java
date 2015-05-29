package br.ufc.quixada.control;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.ufc.quixada.dao.PapelDAO;
import br.ufc.quixada.dao.UsuarioDAO;
import br.ufc.quixada.model.Papel;
import br.ufc.quixada.model.Usuario;
import br.ufc.quixada.util.UsuarioAutenticado;

@Controller
public class UsuarioController {
	
	@Inject private UsuarioDAO udao;
	@Inject private PapelDAO pdao;
	@Inject private Result resultado;
	@Inject private Validator validador;
	@Inject private UsuarioAutenticado usuarioAutenticado;
	
	public void formularioLeitor(){}
	
	public void formularioJornalista(){}
	
	@Post
	public void adicionarLeitor(Usuario usuario){
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(pdao.buscar(1L));
		usuario.setPapeis(papeis);
		udao.adicionar(usuario);
		validador.add(new SimpleMessage("usuario.adicionado","Usuario foi cadastrado com sucesso!",Severity.SUCCESS));
		resultado.redirectTo(IndexController.class).index();
	}
	
	@Post
	public void adicionarJornalista(Usuario usuario){
		Usuario carregado = udao.buscarByLogin(usuario);
		if(carregado!=null){
			carregado.getPapeis().add(pdao.buscar(2L));
			udao.adicionar(carregado);
		}else{
			List<Papel> papeis = new ArrayList<Papel>();
			papeis.add(pdao.buscar(1L));
			papeis.add(pdao.buscar(2L));
			usuario.setPapeis(papeis);
			udao.adicionar(usuario);
		}
		validador.add(new SimpleMessage("usuario.adicionado","Usuario foi cadastrado com sucesso!",Severity.SUCCESS));
		resultado.redirectTo(IndexController.class).index();
	}
	
	@Post
	public void login(Usuario usuario, Long papel){
		Usuario usuarioCarregado = udao.autenticar(usuario);
		Papel papelCarregado = pdao.buscar(papel);
		validador.addIf(usuarioCarregado==null || papelCarregado==null, new SimpleMessage("usuario.negado","Usuario e/ou senha inválidos."));
		validador.onErrorUsePageOf(IndexController.class).index();
		validador.addIf(!usuarioCarregado.getPapeis().contains(papelCarregado), new SimpleMessage("papel.negado", "Tipo de usuário não autorizado para este usuário"));
		validador.onErrorUsePageOf(IndexController.class).index();
		usuarioAutenticado.autenticar(usuarioCarregado, papelCarregado);
		validador.add(new SimpleMessage("usuario.autenticado","Usuario foi autenticado com sucesso!",Severity.SUCCESS));
		resultado.redirectTo(IndexController.class).index();
	};
	
	public void logout(){
		usuarioAutenticado.desautenticar();
		resultado.redirectTo(IndexController.class).index();
	}
}
