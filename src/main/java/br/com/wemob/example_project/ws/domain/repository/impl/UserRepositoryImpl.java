package br.com.wemob.example_project.ws.domain.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import br.com.wemob.example_project.ws.domain.model.User;

public class UserRepositoryImpl implements UserCustomRepository{
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public List<User> findByP(String password) {
		Query query = query(where("password").is(password));
		List<User> users = mongoOperations.find(query, User.class);
		return users;
	}

	
	
}
