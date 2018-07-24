package br.edu.ifpb.pweb2.cashflowjsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.pweb2.cashflowjsf.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

public class MovimentacaoDAO extends GenericDAOJPAImpl<Movimentacao, Integer> 
{	
	public MovimentacaoDAO(){
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	public MovimentacaoDAO(EntityManager em) {
		super(em);
	}
	
//	@SuppressWarnings("unchecked")
	public List<Movimentacao> findAllFromMovimentacao(Movimentacao movimentacao){
		Query q = this.getEntityManager().createQuery("from Movimentacao m where m.id =: id");
		q.setParameter("movimentacao", movimentacao);
		return q.getResultList();
	}
	
//	@SuppressWarnings("unchecked")
	public List<Movimentacao> findAllFromUser(Usuario usuario) {
		Query q = this.getEntityManager().createQuery(
				"from Movimentacao m where m.usuario = :user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
	
//	@SuppressWarnings("unchecked")
	public List<Movimentacao> getMovimentacoes(Usuario usuario) {
		Query q = this.getEntityManager().createQuery("from Movimentacao m where m.usuario = :user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
}