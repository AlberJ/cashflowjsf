package br.edu.ifpb.pweb2.cashflowjsf.controller;

import javax.persistence.EntityManager;

import br.edu.ifpb.pweb2.cashflowjsf.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

public class LoginController {
	private EntityManager entityManager;

	public LoginController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Resultado isValido(String login, String senha) {
		Resultado r = new Resultado();
		r.setErro(false);
		Usuario usuario = null;	
		UsuarioDAO udao = new UsuarioDAO(entityManager);
		try{
			usuario = udao.findByLogin(login);
			if (usuario != null) {
				if (usuario.getSenha().equals(senha)) {
					r.setModel(usuario);
				} else {
					r.setErro(true);
					r.setMensagem("Usuário ou senha inválido(a).");
				}
			}
		}catch (Exception e) {
			r.setErro(true);
			r.setMensagem("Usuário ou senha inválido(a).");
		}
		return r;
	}
}
