package com.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.jsf.model.Genero;
import com.jsf.util.JPAUtil;
import com.jsf.util.JSFUtil;

@ManagedBean
@ViewScoped
public class GeneroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Genero genero;
	private List<Genero> generos;

	public List<Genero> getGeneros() {
		if (generos == null) {
			EntityManager em = JPAUtil.getEntityManager();
			generos = em.createNamedQuery(Genero.FIND_ALL, Genero.class)
					.getResultList();
			em.close();
		}
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public Genero getGenero() {
		if (genero == null)
			genero = new Genero();
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(genero);
			trans.commit();
			JSFUtil.addMessageInfo("Salvo com sucesso!", null);
		} catch (Exception e) {
			trans.rollback();
			JSFUtil.addMessageInfo("Erro ao salvar genero!", e.getMessage());
		}
		em.close();
		return "listar-generos?faces-redirect=true";
	}

	public String deletar(Genero genero) {

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			genero = em.getReference(Genero.class, genero.getId());
			em.remove(genero);
			em.getTransaction().commit();
			
			if(generos != null)
				generos.remove(genero);
			
			JSFUtil.addMessageInfo("Deletado com sucesso!", null);
		} catch (PersistenceException e) {
			JSFUtil.addMessageInfo("Erro ao deletar genero!", e.getMessage());
		}
		em.close();

		return null;
	}

	public String deletar() {
		return deletar(genero);
	}

	public String atualizar(Genero genero) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(genero);
			em.getTransaction().commit();
			JSFUtil.addMessageInfo("Atualizado com sucesso!", null);
		} catch (PersistenceException e) {
			JSFUtil.addMessageInfo("Erro ao atualizar genero!", e.getMessage());
		}
		em.close();
		return null;
	}
	public String atualizar() {
		return atualizar(genero);
	}
}
