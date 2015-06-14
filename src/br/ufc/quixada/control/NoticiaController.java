package br.ufc.quixada.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.caelum.brutauth.auth.annotations.AccessLevel;
import br.com.caelum.brutauth.auth.annotations.SimpleBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.ufc.quixada.dao.NoticiaDAO;
import br.ufc.quixada.model.Noticia;
import br.ufc.quixada.model.Secao;
import br.ufc.quixada.model.Usuario;
import br.ufc.quixada.util.Autorizacao;

@Controller
public class NoticiaController {
	
	@Inject
	private NoticiaDAO dao;
	@Inject
	private Result resultado;
	@Inject
	private ServletContext context;
	@Inject
	private NoticiaValidador validador;
	
	@SimpleBrutauthRules(Autorizacao.class)
	@AccessLevel(2000)
	public void formulario(){
	}
	
	@Post
	@SimpleBrutauthRules(Autorizacao.class)
	@AccessLevel(2000)
	public void adicionar(Noticia noticia, Secao secao,Usuario autor, UploadedFile imagem){
		File arquivo = new File(context.getRealPath("uploads/"), imagem.getFileName());
		try {
			imagem.writeTo(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		noticia.setImagem(arquivo.getName());
		noticia.setSecao(secao);
		noticia.setAutor(autor);
		validador.validarFormulario(noticia);
		dao.adicionar(noticia);
		validador.confirmaValidacao();
		resultado.redirectTo(IndexController.class).index();
	}
	
	@Get
	@Path("/noticia/remover/{noticia.id}")
	public void remover(Noticia noticia){
		dao.remover(dao.buscar(noticia.getId()));
		validador.confirmaRemocao();
		resultado.redirectTo(IndexController.class).index();
	}
	
	@Get
	@Path("/noticia/listar/{secao.id}")
	public List<Noticia> listar(Secao secao){
		List<Noticia> noticiaList = dao.listar(secao);
		if(!noticiaList.isEmpty()) secao = noticiaList.get(0).getSecao();
		resultado.include("secao", secao);
		return noticiaList;
	}
	
	@Get
	@Path("/noticia/ler/{noticia.id}")
	public Noticia ler(Noticia noticia){
		return dao.buscar(noticia.getId());
	}
}
