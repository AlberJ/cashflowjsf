package br.edu.ifpb.pweb2.cashflowjsf.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifpb.pweb2.cashflowjsf.dao.MovimentacaoDAO;
import br.edu.ifpb.pweb2.cashflowjsf.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflowjsf.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

public class MovimentacaoController {
	private List<String> mensagensErro;
	private EntityManager entityManager;

	public MovimentacaoController(EntityManager em) {
		this.entityManager = em;
	}
	
	public Resultado cadastre(Usuario usuario, //String desc, String valor, boolean operacao ) {
								Movimentacao movimentacao){
		Resultado resultado = new Resultado();
//		Movimentacao movimentacao = new Movimentacao(desc, Double.parseDouble(valor), operacao);
				
		try {
			MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
			dao.beginTransaction();
			UsuarioDAO udao = new UsuarioDAO(entityManager);
			usuario = udao.findByLogin(usuario.getLogin());
			movimentacao.setUsuario(usuario);
			if(movimentacao.getId() == null){
				dao.insert(movimentacao);
			}else{
				dao.update(movimentacao);
			}
			
			dao.commit();
			resultado.setErro(false);
			resultado.addMensagens("Movimentacao salvo com sucesso!");
		} catch(Exception e) {
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		return resultado;
	}
	
	public List<Movimentacao> consulte(Usuario usuario) {
		System.out.println("Chegou em consulte");
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		List<Movimentacao> movimentacoes = dao.findAllFromUser(usuario);
		System.out.println("Pegou as movimentacoes no controller.");
		return movimentacoes;
	}

	public Movimentacao busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Movimentacao movimentacao = dao.find(Integer.parseInt(id[0]));
		return movimentacao;
	}

	public List<Movimentacao> getMovimentacoes(Usuario u) {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		List<Movimentacao> lista = dao.findAllFromUser(u);
		return lista;
	}
	
	public Resultado exclua(Map<String, String[]> parameterMap) {
		String ids[] = parameterMap.get("delids");
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			for (String id : ids) {
				Movimentacao u = dao.find(Integer.parseInt(id));
				dao.delete(u);
			}
			dao.commit();
			r.setErro(false);
		} catch (PersistenceException e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir movimentacoes");
		}
		return r;
	}
	
	public Resultado apagar(Movimentacao movimentacao) {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			Movimentacao m = dao.find(movimentacao.getId());
			dao.delete(m);
			dao.commit();
			r.setErro(false);	
		} catch (PersistenceException e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir movimentacoes");
		}
		return r;
	}
}