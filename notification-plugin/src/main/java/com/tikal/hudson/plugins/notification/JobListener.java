/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tikal.hudson.plugins.notification;

import hudson.Extension;
import hudson.model.TaskListener;
import hudson.model.Run;
import hudson.model.Executor;
import hudson.model.listeners.RunListener;

@Extension
@SuppressWarnings("rawtypes")
public class JobListener extends RunListener<Run> {

    public JobListener() {
        super(Run.class);
    }

    @Override
    public void onStarted(Run r, TaskListener listener) {
        Executor e = r.getExecutor();
        Phase.QUEUED.handle(r, TaskListener.NULL, e != null ? System.currentTimeMillis() - e.getTimeSpentInQueue() : 0L);
        Phase.STARTED.handle(r, listener, r.getTimeInMillis());
    }

    @Override
    public void onCompleted(Run r, TaskListener listener) {
        Phase.COMPLETED.handle(r, listener, r.getTimeInMillis() + r.getDuration());
    }

    @Override
    public void onFinalized(Run r) {
        Phase.FINALIZED.handle(r, TaskListener.NULL, System.currentTimeMillis());
    }

}
