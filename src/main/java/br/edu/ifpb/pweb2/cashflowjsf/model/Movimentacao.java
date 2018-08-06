package br.edu.ifpb.pweb2.cashflowjsf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MOVIMENTACAO")
public class Movimentacao 
{
	@Id
	@Column(name="ID_MOVIMENTACAO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_movimentacao;
	
	private String descricao;
	private Double valor;
	private boolean operacao; // FALSE SE SAIDA - TRUE SE ENTRADA
	
	@ManyToOne()
	private Usuario usuario;
		
	public Movimentacao(String descricao, Double valor, boolean tipo) {
		this.id_movimentacao = null;
		this.descricao = descricao;
		this.valor = valor;
		this.operacao = tipo;
	}
	
	public Movimentacao() {
//		PARA PRESISTENCIA
	}
	
	public Integer getId() {
		return this.id_movimentacao;
	}
	
	public void setId(Integer id){
		this.id_movimentacao = id;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario u) {
		this.usuario = u;
		opera();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public boolean getOperacao() {
		return this.operacao;
	}
	
	public void setOperacao(boolean tipo) {
		this.operacao = tipo;
	}

	public void opera() {
		if(this.operacao) { // ENTRADA
			this.usuario.setSaldo(this.usuario.getSaldo() + this.valor);		
		} else { // SAIDA
			this.usuario.setSaldo(this.usuario.getSaldo() - this.valor);
		}
	}
	
	@Override
	public String toString() {
		if(this.operacao){
			return "Entrada [descricao=" + descricao + ", valor=" + valor + "]";
		}else{
			return "Saida [descricao=" + descricao + ", valor=" + valor + "]";
		}
	}		
}