package br.com.tlr.domain;

import java.util.List;

import javax.ejb.Local;

/**
 * Interface para as opera��es do usu�rio.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019 23:54
 */
@Local
public interface IUser {
	public List<User> getList();

	public User save(User user);
}
