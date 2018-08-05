package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.Flash;

import br.edu.ifpb.pweb2.cashflowjsf.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflowjsf.controller.Resultado;
import br.edu.ifpb.pweb2.cashflowjsf.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean(name="movBean")
public class MovimentacaoBean extends GenericBean 
{
	private String desc;
	private String valor;
	private boolean operacao;
	private Integer id; //APENAS PARA TESTE
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Usuario usuario;
	private MovimentacaoController movictrl;
	private Movimentacao movimentacao;
	private Resultado resultado = new Resultado();
	
	@PostConstruct
	public void init()
	{		
		this.movictrl = new MovimentacaoController(EM);		
		this.usuario = (Usuario) session.getAttribute("usuario");
		
		Flash flash = context.getExternalContext().getFlash();
		movimentacao = (Movimentacao) flash.get("movimentacao");
		if(movimentacao != null){
			System.out.println("Pegou movimentacao do flash.");
			this.id = movimentacao.getId();
			this.desc = movimentacao.getDescricao();
			this.valor = String.valueOf(movimentacao.getValor());
			this.operacao = movimentacao.isOperacao();
		}else{
			System.out.println("Movimentacao vazia!");
			this.movimentacao = new Movimentacao();
		}
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public boolean isOperacao() {
		return operacao;
	}
	public void setOperacao(boolean operacao) {
		this.operacao = operacao;
	}
	
	public String salvar()
	{
		this.movimentacao.setDescricao(this.desc);
		this.movimentacao.setValor(Double.valueOf(this.valor));
		this.movimentacao.setOperacao(this.operacao);
		resultado = movictrl.cadastre(usuario ,movimentacao);
		if (resultado.isErro()){
			msgErro(resultado.getMensagem(), "formMovimentacao");
			return null;
		}
		return "/usuario/home?faces-redirect=true";
	}

	public String apagar()
	{
		resultado = movictrl.apagar(movimentacao);
		if (resultado.isErro()){
			msgErro(resultado.getMensagem(), "formMovimentacao");
			return null;
		}
		return "/usuario/home?faces-redirect=true";
	}
	
}
