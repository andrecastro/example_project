package br.com.wemob.example_project.ws.util;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

import java.text.SimpleDateFormat;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.ParameterConverters.DateConverter;

import br.com.wemob.example_project.ws.resources.test.MyResourceStory;

public abstract class AbstractStoryConfiguration extends JUnitStory {

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		return new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromClasspath(MyResourceStory.class))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(
										CodeLocations
												.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withFormats(CONSOLE, HTML))
				.useParameterConverters(
						new ParameterConverters()
								.addConverters(new DateConverter(
										new SimpleDateFormat("yyyy-MM-dd"))))
				.useStepMonitor(new SilentStepMonitor())
				.usePendingStepStrategy(new FailingUponPendingStep());

	}

}
