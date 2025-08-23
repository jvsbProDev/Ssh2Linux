package com.ssh2lnx.code;

// Main class demonstrating usage
public class SSHMain {
    public static void main(String[] args) {
        SSHConnection sshConnection = new SSHConnectionImpl(
                SSHConstants.HOST, SSHConstants.USER, SSHConstants.PASSWORD
        );

        SSHCommandExecutor commandExecutor = new SSHCommandExecutor(sshConnection);
        SFTPFileTransfer fileTransfer = new SFTPFileTransfer(sshConnection);

        try {
            System.out.println("Running command: uname");
            String commandOutput = commandExecutor.executeCommand("uname");
            System.out.println("Output: " + commandOutput);

            System.out.println("Transferring file...");
            fileTransfer.transferFile(SSHConstants.REMOTE_FILE_PATH, SSHConstants.LOCAL_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}