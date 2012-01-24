package br.com.wemob.example_project.ws.resources.test;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.Restfulie;
import br.com.wemob.example_project.ws.util.AbstractSpringDataStep;

public class MyResourceSteps extends AbstractSpringDataStep {

	private Response response;
	
	@When("I access the myresource")
	public void access_myresource() {
		response = Restfulie
				.at("http://localhost:8081/example_project/webresources/myresource")
				.accept("text/plain").get();
	}

	@Then("the response should be ok")
	public void response_should_br_ok(){
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getCode(), 200);
	}
	
}
