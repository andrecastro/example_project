package br.com.wemob.example_project.ws.util;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContextTest.xml"})
public abstract class AbstractSpringDataJUnitTest {

	@Autowired
	protected MongoOperations mongoOperations;

	@After
	public void reset_database() {
		for (String name : mongoOperations.getCollectionNames()) {
			if(!name.equals("system.indexes"))
				mongoOperations.dropCollection(name);
		}
	}
	
}
