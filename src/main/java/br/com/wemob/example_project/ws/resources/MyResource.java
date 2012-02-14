
package br.com.wemob.example_project.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.wemob.example_project.ws.domain.model.User;
import br.com.wemob.example_project.ws.domain.model.UserRole;
import br.com.wemob.example_project.ws.domain.repository.UserRepository;

/** Example resource class hosted at the URI path "/myresource"
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Path("/myresource")
public class MyResource {
    
	private UserRepository userRepository;
	
   @Autowired
	public MyResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GET 
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save() {
		UserRole role =  new UserRole();
		role.setName("role1");
		role.setSomething("something");
		
		User user = new User();
		
		user.setName("teste");
		user.setPassword("123456");
		user.setSuperUser(true);
		user.setRoles(new ArrayList<UserRole>());
		user.getRoles().add(role);
		user.getRoles().add(role);
		user.getRoles().add(role);
		
		userRepository.save(user);
		return "OK";
    }
	
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public User getUser() {
		return userRepository.findByP("123456").get(0);
	}
	
	@Path("/users")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getUsers(){
		return userRepository.findByP("123456");
	}
	
	
}
