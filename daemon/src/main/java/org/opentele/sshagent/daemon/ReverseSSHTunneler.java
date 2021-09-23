package org.opentele.sshagent.daemon;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReverseSSHTunneler {

    @Autowired
    public ReverseSSHTunneler(ApplicationConfig applicationConfig) {
        execute(applicationConfig.getHost(), applicationConfig.getUser(), applicationConfig.getFile());
    }

    public void execute(String host, String user, String identityFile) {
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(identityFile);

            Session session = jsch.getSession(user, host, 22);
            int remotePort = 6040;
            String localHost = "127.0.0.1";
            int localPort = 6040;

            UserInfo ui = new JSchUserInfo();
            session.setUserInfo(ui);
            session.connect();
            session.setPortForwardingR(remotePort, localHost, localPort);
        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }

    public class JSchUserInfo implements UserInfo {
        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public boolean promptPassword(String message) {
            return false;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return false;
        }

        @Override
        public boolean promptYesNo(String message) {
            return true;
        }

        @Override
        public void showMessage(String message) {
            System.out.println(message);
        }
    }
}
