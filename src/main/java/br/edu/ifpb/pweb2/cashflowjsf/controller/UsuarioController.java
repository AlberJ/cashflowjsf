package br.edu.ifpb.pweb2.cashflowjsf.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifpb.pweb2.cashflowjsf.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

public class UsuarioController 
{
	private EntityManager entityManager;

	public UsuarioController(EntityManager em) {
		this.entityManager = em;
	}

	public Resultado cadastre(String email, String login, String senha) 
	{	
		Resultado resultado = new Resultado();
		resultado.setErro(false);
		resultado.setModel(null);
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		
		try{
			if (dao.findByLogin(login) != null) {
				resultado.setErro(true);
				resultado.setMensagem("Login inválido(a).");
			}
		}catch (Exception e) 
		{
			Usuario usuario = new Usuario(email, login, senha);		
			dao.beginTransaction();
			dao.insert(usuario);
			dao.commit();
			resultado.setErro(false);	
			resultado.setModel(usuario);
		}
		
		return resultado;
	}
	
	public List<Usuario> consulte(Usuario usuario)
	{
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		List<Usuario> usuarios = dao.findAllFromUser(usuario);
		return usuarios;
	}

	public Usuario busque(Map<String, String[]> parameterMap)
	{
		String[] id = parameterMap.get("id");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.find(Integer.parseInt(id[0]));
		return usuario;
	}

	public Resultado exclua(Map<String, String[]> parameterMap)
	{
		String emails[] = parameterMap.get("delemails");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			for (String email : emails) {
				Usuario u = dao.findByEmail(email);
				dao.delete(u);
			}
			dao.commit();
			r.setErro(false);
		} catch (PersistenceException e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir usuarios");
		}
		return r;
	}
}