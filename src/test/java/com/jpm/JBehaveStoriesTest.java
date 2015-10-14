package com.jpm;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.jpm.storyrunners.DividendYieldStoriesRunnerApp;
import com.jpm.storyrunners.GBCEIndexPriceStoriesRunnerApp;
import com.jpm.storyrunners.VolumeTradedStockPriceStoriesRunnerApp;

public class JBehaveStoriesTest extends JUnitStories {
	 
    public JBehaveStoriesTest() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true).doVerboseFailures(true).useThreads(1).useStoryTimeouts("900");
    }
 
    @Override
    public Configuration configuration() {
       return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withDefaultFormats()
                .withFormats(Format.CONSOLE, Format.HTML)
                .withFailureTrace(true)
                .withFailureTraceCompression(true));                               
    }
 
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new DividendYieldStoriesRunnerApp(), 
        		new VolumeTradedStockPriceStoriesRunnerApp(),
        		new GBCEIndexPriceStoriesRunnerApp());
    }
     
    @Override
    protected List<String> storyPaths() {
        // Specify story paths as URLs
        return new StoryFinder().findPaths("src/test/resources/","**/*.story","");
    }
         
}
