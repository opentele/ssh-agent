package org.opentele.sshagent.daemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Daemon {
    @Autowired
	public Daemon(Environment environment) {
	}

	public static void main(String[] args) {
		SpringApplication.run(Daemon.class, args);
	}
}
