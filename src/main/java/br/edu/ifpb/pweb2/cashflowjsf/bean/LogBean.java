package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflowjsf.controller.LoginController;
import br.edu.ifpb.pweb2.cashflowjsf.controller.Resultado;
import br.edu.ifpb.pweb2.cashflowjsf.model.Usuario;

@ManagedBean(name = "logBean")
@RequestScoped
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
		EntityManager EM = (EntityManager) session.getAttribute("currentEntityManager");
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		LoginController controller = new LoginController(EM);
		Resultado resultado = (Resultado) controller.isValido(this.login, this.senha);
		
		if(resultado.isErro()){
			msgErro(resultado.getMensagem(), "formLogin");
			return null;
		}else{
			usuario = (Usuario) resultado.getModel();
			session.setAttribute("usuario", usuario);
			
			if (lembrar != null) {
				Cookie c = new Cookie("loginCookie", usuario.getEmail());
				c.setMaxAge(-1);
				response.addCookie(c);
			} else {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals("loginCookie")) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return "/usuario/home?faces-redirect=true";
	}

	
//	FALTA O INVALIDADE NA SESSÃO
	public String realizalogout() {
		session.removeAttribute("usuario");
		return "/login/logon?faces-redirect=true";
	}

}