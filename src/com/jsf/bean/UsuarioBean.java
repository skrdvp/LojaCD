package com.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.jsf.model.Usuario;
import com.jsf.util.JPAUtil;
import com.jsf.util.JSFUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public boolean isLogado() {
		return usuario != null;
	}

	public String logar(String login, String senha) {

		EntityManager em = JPAUtil.getEntityManager();

		TypedQuery<Usuario> query = em.createNamedQuery(
				Usuario.FIND_BY_LOGIN_AND_SENHA, Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);

		try {
			usuario = query.getSingleResult();
			
			JSFUtil.addMessageInfo("Logado", "");
		} catch (javax.persistence.NoResultException e) {
			JSFUtil.addMessageError("Login não encontrado", "");
		}

		em.close();

		return usuario == null ? null : "menu";
	}

	public String newUsuario(String nome, String login, String senha) {
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setLogin(login);
		u.setSenha(senha);

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(u);
			trans.commit();
			JSFUtil.addMessageInfo("Cadastrado com sucesso!", "");
		} catch (javax.persistence.PersistenceException e) {
			JSFUtil.addMessageError("Erro",
					"Ja existe um usuário com esse login!");
		}
		em.close();

		return "login";
	}

	public String logout(){
		usuario = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
}
