package br.com.tlr.domain;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Componente de negócio para operações de usuário.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019 22:12
 */
@Stateless
public class UserImpl implements IUser {

	@Inject
	private UserRepository repository;

	public List<User> getList() {
		// Realizar a pesquisa.
		return this.repository.getList();
	}

	public User save(User user) {
		// Save o registro no banco de dados.
		return this.repository.save(user);
	}

}
