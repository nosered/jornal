package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.model.Usuario;

public interface IUsuarioDAO {
	public void adicionar(Usuario usuario);
	public void atualizar(Usuario usuario);
	public void remover(Usuario usuario);
	public Usuario buscar(Long id);
	public List<Usuario> listar();
}
