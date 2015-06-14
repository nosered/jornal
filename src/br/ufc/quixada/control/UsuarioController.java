package br.ufc.quixada.control;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufc.quixada.dao.PapelDAO;
import br.ufc.quixada.dao.UsuarioDAO;
import br.ufc.quixada.model.Papel;
import br.ufc.quixada.model.Usuario;

@Controller
public class UsuarioController {
	
	@Inject private UsuarioDAO udao;
	@Inject private PapelDAO pdao;
	@Inject private Result resultado;
	@Inject private UsuarioValidador usuarioValidador;
	
	public void formularioLeitor(){}
	
	public void formularioJornalista(){}
	
	@Post
	public void adicionarLeitor(Usuario usuario){
		usuarioValidador.validarFormulario(usuario);
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(pdao.buscar(1L));
		usuario.setPapeis(papeis);
		udao.adicionar(usuario);
		usuarioValidador.confirmar();
		resultado.redirectTo(IndexController.class).index();
	}
	
	@Post
	public void adicionarJornalista(Usuario usuario){
		Usuario carregado = udao.buscarByLogin(usuario);
		if(carregado!=null){
			usuarioValidador.validarLogin(carregado);
			carregado.getPapeis().add(pdao.buscar(2L));
			udao.adicionar(carregado);
		}else{
			usuarioValidador.validarFormulario(usuario);
			List<Papel> papeis = new ArrayList<Papel>();
			papeis.add(pdao.buscar(1L));
			papeis.add(pdao.buscar(2L));
			usuario.setPapeis(papeis);
			udao.adicionar(usuario);
		}
		usuarioValidador.confirmar();
		resultado.redirectTo(IndexController.class).index();
	}
}