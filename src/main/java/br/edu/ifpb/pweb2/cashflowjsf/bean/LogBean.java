package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb2.cashflowjsf.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean(name="logBean")
@RequestScoped
public class LogBean
{
	FacesContext context = FacesContext.getCurrentInstance();
	
	private Usuario usuario;
	private String senha;
	private String login;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String realizaLogin()
	{		
		UsuarioDAO dao = new UsuarioDAO();	
		try{
			Usuario u = dao.findByLogin(login);
			if(u != null){
				if(u.getSenha().equals(senha)){
					this.usuario = u;
					
//					COLOCAR USUARIO NA SESSÃO		
					HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
					session.setAttribute("usuario", this.usuario);
					
					return "/usuario/home?faces-redirect=true";
				}
			}
		}catch(Exception e){
			erro("Login e/ou Senha não confere(m)!");
			return null;
		}
	
		erro("Login e/ou Senha não confere(m)!");
		return null;	
		
	}
	
	private void erro(String msg){
		FacesMessage.Severity nivel = FacesMessage.SEVERITY_ERROR;
		FacesMessage facesMsg = new FacesMessage (nivel, msg, null);
		context.addMessage("formLogin", facesMsg);
	}
	
	public String realizalogout()
	{
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		return "/login/logon?faces-redirect=true";
	}
}