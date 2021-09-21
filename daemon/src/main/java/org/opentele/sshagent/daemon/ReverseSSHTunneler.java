package org.opentele.sshagent.daemon;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class ReverseSSHTunneler {
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
        }
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
