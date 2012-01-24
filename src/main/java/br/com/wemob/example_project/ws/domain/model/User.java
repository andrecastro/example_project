package br.com.wemob.example_project.ws.domain.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
@Document(collection = "users")
public class User {

	private String id;
	private String name;
	private String password;
	private Boolean superUser;
	private List<UserRole> roles;

	public User(String id, String name, String password, Boolean superUser) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.superUser = superUser;
	}

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

}
