package org.opentele.sshagent.daemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReverseSSHTunnelerTest {
    @Value("${temp.host}")
    private String host;
    @Value("${temp.user}")
    private String user;
    @Value("${temp.id.file}")
    private String file;

    @Test
    public void execute() {
        new ReverseSSHTunneler().execute(host, user, file);
    }
}
