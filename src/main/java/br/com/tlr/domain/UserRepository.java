package br.com.tlr.domain;

import javax.enterprise.context.Dependent;

import br.com.tlr.AbstractRepository;

@Dependent
public class UserRepository extends AbstractRepository<User> {

	/**
	 * Construtor padrão.
	 */
	public UserRepository() {
		super(User.class);
	}
}
