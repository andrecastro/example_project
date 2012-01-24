package br.com.wemob.example_project.ws.util;

import java.net.UnknownHostException;

import org.jbehave.core.annotations.AfterScenario;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class AbstractSpringDataStep {

	@AfterScenario
	public void reset_database() throws UnknownHostException, MongoException {
		MongoOperations mongoOperations = new MongoTemplate(new Mongo(),"example_project_test");
		
		for (String name : mongoOperations.getCollectionNames()) {
			if (!name.equals("system.indexes"))
				mongoOperations.dropCollection(name);
		}

	}

}
