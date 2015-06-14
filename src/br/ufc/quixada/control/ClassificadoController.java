package br.ufc.quixada.control;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;


import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufc.quixada.dao.ClassificadoDAO;
import br.ufc.quixada.model.Classificado;
import br.ufc.quixada.model.Usuario;
import br.ufc.quixada.util.Oferta;

@Controller
public class ClassificadoController {
	
	@Inject private ClassificadoDAO dao;
	@Inject private ClassificadoValidador validador;
	@Inject private Result resultado;
	
	public void formulario(){}
	
	public void adicionar(Classificado classificado){
		validador.validarFormulario(classificado);
		classificado.setDataOferta(null);
		classificado.setAutorOferta(null);
		classificado.setValorOferta(0.0);
		dao.adicionar(classificado);
		validador.confirmarValidacao();
		resultado.redirectTo(ClassificadoController.class).listar();
	}
	
	public List<Classificado> listar(){
		return dao.listar();
	};
	
	public void atualizar(Classificado classificado, Oferta oferta, Usuario autor){
		classificado = dao.buscar(classificado.getId());
		oferta.setAutor(autor);
		oferta.setData(new Date());
		/*System.out.println("Valor: "+oferta.getValor());
		System.out.println("Data: "+oferta.getData());
		System.out.println("Autor: "+oferta.getAutor().getId());
		System.out.println("UltimaOferta: "+classificado.getValorOferta());*/
		validador.validarFormularioOferta(classificado, oferta);
		classificado.setAutorOferta(oferta.getAutor());
		classificado.setDataOferta(oferta.getData());
		classificado.setValorOferta(oferta.getValor());
		dao.atualizar(classificado);
		validador.confirmarOferta();
		resultado.redirectTo(ClassificadoController.class).listar();
	}
}