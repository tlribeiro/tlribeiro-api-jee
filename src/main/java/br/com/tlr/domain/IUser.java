package br.com.tlr.domain;

import java.util.List;

import javax.ejb.Local;

/**
 * Interface para as operações do usuário.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019 23:54
 */
@Local
public interface IUser {
	public List<User> getList();

	public User save(User user);
}
