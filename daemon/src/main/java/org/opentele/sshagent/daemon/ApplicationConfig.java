package org.opentele.sshagent.daemon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Value("${temp.host}")
    private String host;
    @Value("${temp.user}")
    private String user;
    @Value("${temp.id.file}")
    private String file;

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getFile() {
        return file;
    }
}
