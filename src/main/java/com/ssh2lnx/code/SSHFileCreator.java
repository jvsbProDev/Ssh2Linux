package com.ssh2lnx.code;

import com.jcraft.jsch.*;

import java.io.*;

/**
 * Title:  Simple SSH File Creator to Linux Server
 * Author: Jovi Van Shannon Belnas
 */

public class SSHFileCreator {
    private static String host = "192.168.123.456";
    private static String user = "lnxComputer";
    private static String password = "password123";

    public static void main(String[] args) {

        String remoteDirectory = "/home/lnxComputer/Desktop/";
        String fileName = "sample.txt";

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
