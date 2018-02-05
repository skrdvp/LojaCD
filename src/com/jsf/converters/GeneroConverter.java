package com.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.jsf.model.Genero;
import com.jsf.util.JPAUtil;

@FacesConverter(forClass = Genero.class)
public class GeneroConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		if(key == null || key.isEmpty())
			return null;
		Long id = Long.parseLong(key);
		EntityManager em = JPAUtil.getEntityManager();
		Genero genero = em.find(Genero.class, id);
		em.close();
		return genero;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		
		Genero genero = (Genero) obj;
		if(genero == null || genero.getId() == null)
			return null;
		return String.valueOf(genero.getId());
	}

}
