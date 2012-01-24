package br.com.wemob.example_project.ws.domain.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import br.com.wemob.example_project.ws.domain.model.User;

public class UserRepositoryImpl implements UserCustomRepository{
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public List<User> findByP(String password) {
		List<User> users = mongoOperations.find(query(where("password").is(password)), User.class);
		return users;
	}

	
	
}
