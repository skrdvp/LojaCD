package com.jsf.teste;

import javax.persistence.EntityManager;

import com.jsf.model.CD;
import com.jsf.model.Genero;
import com.jsf.util.JPAUtil;

public class InsersaoCD {
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CD cd = new CD();
		cd.setTitulo("Titulo");
		cd.setArtista("Artista");
		cd.setPreco(15.00f);
		
		Genero genero = em.find(Genero.class, 1L);
		cd.setGenero(genero);
		
		em.getTransaction().begin();
		em.persist(cd);
		em.getTransaction().commit();
		
		em.close();
		
		System.out.println(cd);
	}
}
