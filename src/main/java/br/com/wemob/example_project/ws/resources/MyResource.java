
package br.com.wemob.example_project.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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

	/** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
	@GET 
    @Produces("text/plain")
    public String getIt() {
        User user = new User();
        user.setName("André");
		user.setPassword("123456");
		user.setSuperUser(true);
		user.setRoles(new ArrayList<UserRole>());
		
		UserRole role = new UserRole();
		role.setName("Adimin");
		role.setSomething("something");
		
		user.getRoles().add(role);
		
		role = new UserRole();
		role.setName("Client");
		role.setSomething("something");
	
		user.getRoles().add(role);
			
		userRepository.save(user);
		
		return "OK";
    }
	
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public User getUser(){
		List<User> users = userRepository.findByP("123456");
		return users.get(0);
	}
	
	@Path("/users")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getUsers(){
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}
	
	
}
