package br.com.wemob.example_project.ws.domain.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.wemob.example_project.ws.domain.model.User;
import br.com.wemob.example_project.ws.domain.repository.UserRepository;
import br.com.wemob.example_project.ws.util.AbstractSpringDataJUnitTest;

public class UserRepositoryTest extends AbstractSpringDataJUnitTest{

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(String name, String password, Boolean superUser){
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setSuperUser(superUser);
		
		return user;
	}
	
	@Test
	public void should_insert_a_user(){
		User user = createUser("André", "123456", true);
		user = userRepository.save(user);
		
		Assert.assertNotNull(user.getId());
	}
	
	
}
