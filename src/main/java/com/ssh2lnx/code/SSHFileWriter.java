package com.ssh2lnx.code;

import com.jcraft.jsch.*;

import java.io.*;

import static com.ssh2lnx.code.SSHConstants.*;

/**
 * Title: Simple SSH File Writer to Linux Server
 * Author: Jovi Van Shannon Belnas
 */

public class SSHFileWriter {

    public static void main(String[] args) {

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            try (OutputStream out = sftpChannel.put(remoteDirectory + fileName)) {
                out.write("Hello World\n".getBytes());
                out.write("Goodbye World".getBytes());
            }

            sftpChannel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }
}
