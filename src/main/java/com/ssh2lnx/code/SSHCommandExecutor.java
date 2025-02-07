package com.ssh2lnx.code;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

// Utility class for running commands
class SSHCommandExecutor {
    private final SSHConnection sshConnection;

    public SSHCommandExecutor(SSHConnection sshConnection) {
        this.sshConnection = sshConnection;
    }

    public String executeCommand(String command) throws JSchException, IOException {
        Session session = sshConnection.connectSession();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        InputStream inputStream = channel.getInputStream();
        channel.connect();

        StringBuilder output = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.append(new String(buffer, 0, bytesRead));
        }

        channel.disconnect();
        sshConnection.disconnectSession(session);
        return output.toString();
    }
}