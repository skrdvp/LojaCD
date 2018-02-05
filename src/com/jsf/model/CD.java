package com.jsf.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cds")
@NamedQueries({ 
	@NamedQuery(name = CD.FIND_ALL, query = "SELECT c FROM CD c"), 
	@NamedQuery(name = CD.FIND_BY_GENERO, query = "SELECT c FROM CD c WHERE c.genero = :genero"),
	@NamedQuery(name = CD.FIND_BETWEEN_PRECOMIN_AND_PRECOMAX, query = "SELECT c FROM CD c WHERE c.preco BETWEEN :precoMin AND :precoMax"),
	@NamedQuery(name = CD.FIND_ALL_WITH_GENERO, query = "SELECT c FROM CD c JOIN FETCH c.genero")
})
public class CD implements Serializable {

	public static final String FIND_ALL = "CD.findAll",
							   FIND_BY_GENERO = "CD.findByGenero",
							   FIND_BETWEEN_PRECOMIN_AND_PRECOMAX = "CD.findBetweenPrecoMinAndPrecoMax",
							   FIND_ALL_WITH_GENERO = "Cd.findAllWithGenero";
							   
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String artista;
	private Float preco;
	
	@ManyToOne
	private Genero genero;
	private static final long serialVersionUID = 1L;

	public CD() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
	public String getArtista() {
		return this.artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}   
	public Float getPreco() {
		return this.preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	@Override
	public String toString() {
		return "CD [id=" + id + ", titulo=" + titulo + ", artista=" + artista
				+ ", preco=" + preco + ", genero=" + genero + "]";
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
		CD other = (CD) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
   
}
