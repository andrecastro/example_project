package br.com.wemob.example_project.ws.domain.repository.impl;

import java.util.List;

import br.com.wemob.example_project.ws.domain.model.User;

public interface UserCustomRepository {
	
	List<User> findByP(String password);
	
}
