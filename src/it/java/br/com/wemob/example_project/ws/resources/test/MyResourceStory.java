package br.com.wemob.example_project.ws.resources.test;

import java.util.List;

import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import br.com.wemob.example_project.ws.util.AbstractStoryConfiguration;

public class MyResourceStory extends AbstractStoryConfiguration {

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), new MyResourceSteps()).createCandidateSteps();
	}
	
}
