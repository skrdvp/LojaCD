package com.jsf.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Genero
 *
 */
@Entity
@Table(name="generos")
@NamedQuery(name = Genero.FIND_ALL, query = "SELECT g FROM Genero g")
public class Genero implements Serializable {

	public static final String FIND_ALL = "Genero.FIND_ALL";
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@OneToMany(mappedBy = "genero", cascade = ALL,orphanRemoval=true)
	private List<CD> cds;
	private static final long serialVersionUID = 1L;

	public Genero() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public List<CD> getCds() {
		if(cds == null)
			cds = new ArrayList<CD>();
		return this.cds;
	}

	public void setCds(List<CD> cds) {
		this.cds = cds;
	}
	@Override
	public String toString() {
		return "Genero [id=" + id + ", descricao=" + descricao + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
