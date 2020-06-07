package org.jenkinsci.plugins.graven;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Descriptor;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;

public class GravenBuilder extends Builder {
    private final String message;

    private boolean enableCloud;

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        listener.getLogger().println("this is Graven build logger" + message);
        return true;
    }

    @DataBoundConstructor
    public GravenBuilder(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Extension
    public static class GravenDescriptor extends BuildStepDescriptor<Builder>{

        private boolean enableCloud;

        public GravenDescriptor(){
            load();
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
            req.bindJSON(this,json.getJSONObject("graven"));
            save();
            return true;
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "execute Graven task";
        }

        public boolean isEnableCloud() {
            return enableCloud;
        }

    }

    public boolean isEnableCloud() {
        return getDescriptor().isEnableCloud();
    }

    @Override
    public GravenDescriptor getDescriptor() {
        return (GravenDescriptor) super.getDescriptor();
    }

}
