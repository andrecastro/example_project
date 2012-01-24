package br.com.wemob.example_project.ws.domain.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.wemob.example_project.ws.domain.model.User;
import br.com.wemob.example_project.ws.domain.repository.impl.UserCustomRepository;

public interface UserRepository extends CrudRepository<User, String> , UserCustomRepository{

}
