package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.Flash;

import br.edu.ifpb.pweb2.cashflowjsf.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflowjsf.controller.Resultado;
import br.edu.ifpb.pweb2.cashflowjsf.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean(name="movBean")
@ViewScoped
public class MovimentacaoBean extends GenericBean 
{
	private Usuario usuario;
	private Movimentacao movimentacao;
	
	private MovimentacaoController movictrl;
	private Resultado resultado = new Resultado();
	private Double valorAntigo;
	private boolean opAntigo;
	
	@PostConstruct
	public void init()
	{		
		this.movictrl = new MovimentacaoController(EM);		
		this.usuario = (Usuario) session.getAttribute("usuario");
		
		Flash flash = context.getExternalContext().getFlash();
		Movimentacao m = (Movimentacao) flash.get("movimentacao");		
		if(m != null){
			this.movimentacao = m;
			valorAntigo = m.getValor();
			opAntigo = m.getOperacao();
		}else {
			this.movimentacao = new Movimentacao();
		} 
	}
		
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public String salvar()
	{
		try{
			System.out.println("Id da movimentação: "+movimentacao.getId());
			resultado = movictrl.cadastre(usuario, movimentacao, valorAntigo, opAntigo);
		}catch(Exception e){
			msgErro(resultado.getMensagem(), "formMovimentacao");
			return null;
		}

		return "/usuario/home?faces-redirect=true";
	}

	public String apagar()
	{
		System.out.println("Id da movimentação: "+movimentacao.getId());
		resultado = movictrl.apagar(movimentacao, usuario);
		if (resultado.isErro()){
			msgErro(resultado.getMensagem(), "formMovimentacao");
			return null;
		}
		return "/usuario/home?faces-redirect=true";
	}
	
}
