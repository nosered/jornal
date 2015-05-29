package br.ufc.quixada.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufc.quixada.model.Usuario;

public class UsuarioDAO implements IUsuarioDAO{
	
	@Inject
	private EntityManager manager;

	public void adicionar(Usuario usuario) {
		manager.persist(usuario);
	}

	public void atualizar(Usuario usuario) {
		
	}

	public void remover(Usuario usuario) {
		
	}

	public Usuario buscar(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public Usuario buscarByLogin(Usuario usuario){
		String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("login", usuario.getLogin());
		if(query.getResultList().isEmpty()) return null;
		return query.getSingleResult();
	}
	
	public Usuario autenticar(Usuario usuario){
		String jpql = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		if(query.getResultList().isEmpty()) return null;
		return query.getSingleResult();
	}

	public List<Usuario> listar() {
		return null;
	}
	
}
