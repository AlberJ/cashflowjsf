package br.edu.ifpb.pweb2.cashflowjsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class GenericBean
{
	protected FacesContext context = FacesContext.getCurrentInstance();
	protected HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
	
	protected void msgErro(String msg, String id) {
		FacesMessage.Severity nivel = FacesMessage.SEVERITY_ERROR;
		FacesMessage facesMsg = new FacesMessage(nivel, msg, null);
		context.addMessage(id, facesMsg);
	}

}
