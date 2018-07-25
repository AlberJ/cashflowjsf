package br.edu.ifpb.pweb2.cashflowjsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

public class UsuarioDAO extends GenericDAOJPAImpl<Usuario, Integer> 
{	
	private static Logger logger = Logger.getLogger(UsuarioDAO.class);
	
	public UsuarioDAO(){
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	public Usuario findByEmail(String email) throws DAOException {
		Query q = this.getEntityManager().createQuery("Select u from Usuario u where u.email = :email");
		q.setParameter("email", email);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
			
		} catch (NoResultException e) {
			throw new DAOException("Usuário não existe!", e);
		}
		return u;
	}
	
	public Usuario findByLogin(String login) throws DAOException {
		Query q = this.getEntityManager().createQuery("Select u from Usuario u where u.login = :login");
		q.setParameter("login", login);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
			
		} catch (Exception e) {
			throw new DAOException("Usuário não existe!", e);
		}
		return u;
	}
	
	// Revisar o metodo abaixo.
	// Esse metodo eh chamado na classe cashflow.controller.UsuarioController
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllFromUser(Usuario usuario) {
		Query q = this.getEntityManager().createQuery("from Usuario u where u.usuario = :user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
}