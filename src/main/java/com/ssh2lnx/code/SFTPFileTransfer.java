package com.ssh2lnx.code;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

// Utility class for file transfer
class SFTPFileTransfer {
    private final SSHConnection sshConnection;

    public SFTPFileTransfer(SSHConnection sshConnection) {
        this.sshConnection = sshConnection;
    }

    public void transferFile(String remotePath, String localPath) throws JSchException, SftpException {
        Session session = sshConnection.connectSession();
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();

        sftpChannel.get(remotePath, localPath);

        sftpChannel.disconnect();
        sshConnection.disconnectSession(session);
        System.out.println("File transferred successfully from " + remotePath + " to " + localPath);
    }
}
