package br.com.tlr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Entidade de usuário.
 * @author Thiago Ribeiro
 * @since 31.07.2019 23:45
 */
@Entity
@Table(name="tlr_user")
@JsonPropertyOrder(value = { "userId", "name", "email"})
public class User {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@NotNull
	@Column(name="tlr_user_name", length=50)
	private String name;
	
	@NotNull
	@Column(name="tlr_user_email", length=50)
	private String email;
	
	@NotNull
	@JsonIgnore
	@Column(name="tlr_user_password", length=150)
	private String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
