package br.ufc.quixada.control;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.AccessLevel;
import br.com.caelum.brutauth.auth.annotations.SimpleBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufc.quixada.dao.SecaoDAO;
import br.ufc.quixada.model.Secao;
import br.ufc.quixada.util.Autorizacao;

@Controller
public class SecaoController {
	
	@Inject
	private SecaoDAO dao;
	
	@Inject
	private Result result;
	
	@SimpleBrutauthRules(Autorizacao.class)
	@AccessLevel(3000)
	public void formulario(){
	}
	
	@SimpleBrutauthRules(Autorizacao.class)
	@AccessLevel(3000)
	public void adicionar(Secao secao){
		dao.adicionar(secao);
		result.redirectTo(IndexController.class).index();
	}
	
	public List<Secao> listar(){
		return dao.listar();
	}
}