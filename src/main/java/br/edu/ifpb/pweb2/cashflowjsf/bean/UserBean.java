package br.edu.ifpb.pweb2.cashflowjsf.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.edu.ifpb.pweb2.cashflowjsf.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflowjsf.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean
public class UserBean extends GenericBean
{
	private MovimentacaoController movictrl; 
	private Usuario usuario;
	private ArrayList<Movimentacao> movimentacoes;
	private DataModel<Movimentacao> model;
	
	
	@PostConstruct
	public void init(){
		this.movictrl = new MovimentacaoController(EM);
		
		this.usuario = (Usuario) session.getAttribute("usuario");
		if (usuario != null){
			this.movimentacoes = (ArrayList<Movimentacao>) movictrl.getMovimentacoes(usuario);
			model = new ListDataModel<Movimentacao>(movimentacoes);
		}
	}
	
	public DataModel<Movimentacao> getModel() {
		return model;
	}

	public void setModel(DataModel<Movimentacao> model) {
		this.model = model;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(ArrayList<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public String ver(Movimentacao movi)
	{
		Flash flash = context.getExternalContext().getFlash();
		flash.put("movimentacao", movi);
		return "/movimentacao/movimentacao?faces-redirect=true";
	}

	
}
