package org.jenkinsci.plugins.graven;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.junit.Assert.*;

public class GravenBuilderTest {
    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void checkGravenBuilder() throws Exception{
        String message = "abc";
        GravenBuilder builder = new GravenBuilder(message);
        FreeStyleProject p = r.createFreeStyleProject();
        p.getBuildersList().add(builder);

        FreeStyleBuild freeStyleBuild = p.scheduleBuild2(0).get();

        System.out.println((freeStyleBuild.getLog().contains("this is Graven build logger" + message)));

    }

}