package org.opentele.sshagent.daemon.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class MainJob implements Job {
    @Value("${temp.host}")
    private String host;
    @Value("${temp.user}")
    private String user;
    @Value("${temp.id.file}")
    private String file;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    }
}
