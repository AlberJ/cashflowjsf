package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.pweb2.cashflowjsf.controller.Resultado;
import br.edu.ifpb.pweb2.cashflowjsf.controller.UsuarioController;

@ManagedBean(name="newUser")
public class CadastroUserBean extends GenericBean
{	
	private String login;
	private String email;
	private String senha;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String cadastro() 
	{
		UsuarioController controller = new UsuarioController(EM);
		Resultado resultado = controller.cadastre(this.email, this.login, this.senha);
		
		if (resultado.isErro()) {
			msgErro(resultado.getMensagem(), "formCadastro:login");
			return null;
		}
		
		session.setAttribute("usuario", resultado.getModel());
		return "/usuario/home?faces-redirect=true";
	}

}
