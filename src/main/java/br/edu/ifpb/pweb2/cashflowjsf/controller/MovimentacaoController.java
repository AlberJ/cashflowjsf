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
	
	public Resultado cadastre(Usuario usuario, Movimentacao movimentacao)
	{
		Resultado resultado = new Resultado();	
		try {
			MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
			UsuarioDAO udao = new UsuarioDAO(entityManager);
			dao.beginTransaction();
			
			movimentacao.setUsuario(usuario);			
//			atualiza movimentacao
			if(movimentacao.getId() != null){ 
				dao.update(movimentacao);
			}
			else{ // A MOVIMENTAÇÃO É NOVA
				dao.insert(movimentacao);
				if(movimentacao.getOperacao()){
					usuario.adicionarValor(movimentacao.getValor());
				}else{
					usuario.removerValor(movimentacao.getValor());
				}
			}	
			dao.commit();
			
			udao.beginTransaction();
			udao.update(usuario);
			udao.commit();
			
			resultado.setErro(false);
			resultado.addMensagens("Movimentacao salva com sucesso!");
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
	
	public Resultado apagar(Movimentacao movimentacao, Usuario usuario) {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		UsuarioDAO udao = new UsuarioDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			Movimentacao m = dao.find(movimentacao.getId());
			if(m.getOperacao()){ // É ENTRADA
				usuario.removerValor(m.getValor());
			}else { // É SAÍDA
				usuario.adicionarValor(m.getValor());
			}
			dao.delete(m);
			dao.commit();
			udao.beginTransaction();
			udao.update(usuario);
			udao.commit();
			r.setErro(false);	
		} catch (Exception e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir movimentação.");
		}
		return r;
	}
}