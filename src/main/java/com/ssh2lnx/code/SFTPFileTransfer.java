package com.ssh2lnx.code;

import com.jcraft.jsch.*;

import java.io.IOException;

import static com.ssh2lnx.code.SSHConstants.*;

/**
 * Title: Simple SSH File Transfer to Linux Server
 * Author: Jovi Van Shannon Belnas
 */

public class SFTPFileTransfer {

    public static void main(String[] args) {

        try {
            // Copying a single file
            copyFile(host, user, password, remoteFilePath, localFilePath);

        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }

    // Method to copy a single file from remote to local
    private static void copyFile(String host, String user, String password, String remoteFilePath, String localFilePath) throws JSchException, SftpException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        // Open an SFTP channel
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();

        // Copy the file
        sftpChannel.get(remoteFilePath, localFilePath);

        // Disconnect
        sftpChannel.disconnect();
        session.disconnect();

        System.out.println("File copied successfully from " + remoteFilePath + " to " + localFilePath);
    }
}