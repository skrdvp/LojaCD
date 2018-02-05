package com.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.jsf.model.CD;
import com.jsf.util.JPAUtil;
import com.jsf.util.JSFUtil;

@ManagedBean(name = "cdBean")
@ViewScoped
public class CDBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CD cd;
	private List<CD> cds;
	
	public CD getCd() {
		if(cd == null)
			cd = new CD();
		return cd;
	}
	
	public void setCd(CD cd) {
		this.cd = cd;
	}
	
	public List<CD> getCds() {
		if(cds == null){
			EntityManager em = JPAUtil.getEntityManager();
			cds = em.createNamedQuery(CD.FIND_ALL,CD.class).getResultList();
			em.close();
		}
		return cds;
	}
	
	public List<CD> getCdsWithGenero(){
		if(cds == null){
			EntityManager em = JPAUtil.getEntityManager();
			cds = em.createNamedQuery(CD.FIND_ALL_WITH_GENERO,CD.class).getResultList();
			em.close();
		}
		return cds;
	}
	
	public void setCds(List<CD> cds) {
		this.cds = cds;
	}
	
	public String salvar(){
		
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try {
			em.persist(cd);
			trans.commit();
			
			JSFUtil.addMessageInfo("Salvo com sucesso!", null);
		} catch (PersistenceException e) {
			e.printStackTrace();
			JSFUtil.addMessageError("Erro ao salvar:", e.getMessage());
			trans.rollback();
		}
		em.close();
		return "listar-cds?faces-redirect=true";
	}
	
	public String deletar(CD cd){
		System.out.println("Deletar: "+cd);
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			cd = em.getReference(CD.class, cd.getId());
			em.remove(cd);
			trans.commit();
			
			if(cds != null)
				cds.remove(cd);
			
			JSFUtil.addMessageInfo("CD deletado com sucesso!", null);
		} catch (PersistenceException e) {
			trans.rollback();
			JSFUtil.addMessageError("Erro ao deletar CD:", e.getMessage());
		}
		em.close();
		
		return null;
	}
	
	public String deletar(){
		return deletar(cd);
	}
	
	public String atualizar(CD cd){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(cd);
			em.getTransaction().commit();
			JSFUtil.addMessageInfo("Atualizado com sucesso!", null);
		} catch (PersistenceException e) {
			JSFUtil.addMessageInfo("Erro ao atualizar CD!", e.getMessage());
		}
		em.close();
		return null;
	}
	
	public String atualizar(){
		return atualizar(cd);
	}
	
}
