package br.edu.ifpb.pweb2.cashflowjsf.controller;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
	private Object model;
	private boolean erro;
	private List<String> mensagens;
	private String proximaPagina;
	private boolean redirect = false;
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Resultado() {
		this.mensagens = new ArrayList<String>();
	}

	public boolean isErro() {
		return erro;
	}
	
	public void setErro(boolean erro) {
		this.erro = erro;
	}
	
	public Object getModel() {
		return model;
	}
	
	public void setModel(Object model) {
		this.model = model;
	}
	
	public List<String> getMensagens(){
		return mensagens;
	}
	
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	public void addMensagens(String mensagensErro) {
		this.mensagens.addAll(mensagens);
	}

	public String getProximaPagina() {
		return proximaPagina;
	}
	
	public void setProximaPagina(String proximaPagina) {
		this.proximaPagina = proximaPagina;
	}

	public void setRedirect(boolean b) {
		this.redirect = b;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
}
