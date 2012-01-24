package br.com.wemob.example_project.ws.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("role")
public class UserRole {

	private String name;
	private String something;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSomething() {
		return something;
	}

	public void setSomething(String something) {
		this.something = something;
	}

}
