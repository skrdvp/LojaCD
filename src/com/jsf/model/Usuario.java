package com.jsf.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@NamedQueries({
		@NamedQuery(name = Usuario.FIND_ALL, query = "SELECT c FROM Usuario c"),
		@NamedQuery(name = Usuario.FIND_BY_LOGIN_AND_SENHA, query = "SELECT c FROM Usuario c WHERE c.login = :login AND c.senha = :senha"),
		@NamedQuery(name = Usuario.FIND_BY_LOGIN, query = "SELECT c FROM Usuario c WHERE c.login = :login") })
public class Usuario implements Serializable {

	public static final String FIND_ALL = "Usuario.findAll",
			FIND_BY_LOGIN_AND_SENHA = "Usuario.findByUsuarioAndSenha",
			FIND_BY_LOGIN = "Usuario.findByLogin";

	@Id
	private String login;
	private String nome;
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
		setRole(Role.USUARIO);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}



	public enum Role {
		USUARIO, ADMIM;
	}
}
