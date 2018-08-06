package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflowjsf.controller.LoginController;
import br.edu.ifpb.pweb2.cashflowjsf.controller.Resultado;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean(name = "logBean")
//@SessionScoped
public class LogBean extends GenericBean
{
	private String lembrar;
	private Usuario usuario;
	private String senha;
	private String login;
	
	public String getLembrar() {
		return lembrar;
	}

	public void setLembrar(String lembrar) {
		this.lembrar = lembrar;
	}

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
		LoginController controller = new LoginController(EM);
		Resultado resultado = (Resultado) controller.isValido(this.login, this.senha);
		
		if(resultado.isErro()){
			msgErro(resultado.getMensagem(), "formLogin");
			return null;
		}else{
			usuario = (Usuario) resultado.getModel();
			session.setAttribute("usuario", usuario);
		}
		return "/usuario/home?faces-redirect=true";
	}

	public String realizalogout() {
		session.removeAttribute("usuario");
		return "/login/logon?faces-redirect=true";
	}

}