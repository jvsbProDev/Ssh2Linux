<h1 align="center">Ssh2Linux<br>
<img src="banner.gif" style="width: 50%"><br>
</h1>

This Java project provides SSH functionalities, including command execution and SFTP file transfer,</br>
using the **JSch (Java Secure Channel)** library.

```bash
.
└───src
    └───main
        └───java
            └───com
                └───ssh2lnx
                    └───code
                            SFTPFileTransfer.java
                            SSHCommandExecutor.java
                            SSHConnection.java
                            SSHConnectionImpl.java
                            SSHConstants.java
                            SSHMain.java
```

#### Summary
+ `SSHMain`: Main class to execute SSH commands and transfer files.
+ `SSHConstants`: Stores credentials and file paths.
+ `SSHConnection`: Interface for managing SSH connections.
+ `SSHConnectionImpl`: Implements SSH connection logic.
+ `SSHCommandExecutor`: Executes remote commands over SSH.
+ `SFTPFileTransfer`: Handles file transfers via SFTP.

---

**Simple SSH Connection to Linux Server**

> Linux Server Credentials :
```bash
username : lnxComputer
password : password123
ip addr  : 192.168.123.456
```

Installation
> Terminal (Linux) :
```bash
sudo apt-get install openssh-server
```

**Note :**

`sudo` in Linux stands for "superuser do." It's a command that allows users to execute commands with the security privileges of another user, usually the superuser (`root`).

This allows users to perform administrative tasks without logging in as the root user directly, users can execute commands or run programs that require elevated permissions.

Configuration
> Terminal (Linux) :
```bash
sudo nano /etc/ssh/sshd_config
```
Enable (un-comment #) the following
> Terminal (Linux) :
```
Port 22
PasswordAuthentication yes
UsePAM yes
```

Start up the server and enable port 22
> Terminal (Linux) :
```bash
sudo systemctl start ssh
sudo systemctl enable ssh
sudo ufw allow 22
sudo ufw enable
```
**Note :**

`ufw` stands for _Uncomplicated Firewall_, it set up rules to allow or deny traffic based on ports, IP addresses, or protocols.

`systemctl` is a command-line utility in Linux systems that is used to start, stop, restart, enable, disable, reload, and check the status of system services.

Update ssh and check status
> Terminal (Linux) :
```bash
sudo systemctl restart ssh
sudo systemctl status ssh
```

use `SSH` (Secure Shell) command to connect to a remote computer
> Command Prompt (Windows) :
```bash
ssh -p <port> <username>@<ip address>
ssh -p 22 lxnComputer@192.168.123.456
```

Shutdown server and disable port 22
> Terminal (Linux) :
```bash
sudo ufw disable
sudo ufw delete allow 22
sudo systemctl disable ssh
sudo systemctl stop ssh
```

**Note :**

To copy a file from a Linux server to a Windows PC using command-line tools,</br>
you can utilize `scp` (Secure Copy Protocol)
> Command Prompt (Windows) :
```bash
scp <username>@<ip address>:/home/lxnComputer/directoryPath/sample.txt "C:\\Users\\winComputer\\directoryPath\\sample.txt"
scp lnxComputer@192.168.123.456:/home/lxnComputer/Desktop/sample.txt "C:\\Users\\winComputer\\Desktop\\sample.txt"
```

To copy a directory with files from a Linux server to a Windows PC using command-line tools like scp,</br>
you can utilize the `-r` option to recursively copy directories
> Command Prompt (Windows) :
```bash
scp -r <username>@<ip address>:/home/lxnComputer/directoryPath/lxnDir "C:\\Users\\winComputer\\directoryPath\\winDir"
scp -r lnxComputer@192.168.123.456:/home/lxnComputer/Documents/tutsh4x "C:\\Users\\winComputer\\Desktop\\tutsh4x"
```

---

Single Responsibility Principle (SRP) :

+ Moved constants to `SSHConstants` for a single source of truth and reusability.
+ Created separate classes for SSH connection, command execution, and file transfer.

Open/Closed Principle (OCP) :

+ Interfaces (`SSHConnection`) allow new implementations without modifying existing code.

Liskov Substitution Principle (LSP) :

+ The `SSHConnectionImpl` class adheres to the `SSHConnection` interface, ensuring it can be substituted.

Interface Segregation Principle (ISP) :

+ Smaller, focused interfaces like `SSHConnection` ensure no class is forced to implement unused methods.

Dependency Inversion Principle (DIP) :

+ High-level modules (`SSHCommandExecutor` and `SFTPFileTransfer`) depend on abstractions (`SSHConnection`), not concrete classes.

---

#### 1. `SSHMain.java` (Main Class)

**Purpose:** This is the entry point of the application.</br>
It initializes the SSH connection, executes a command, and transfers a file.

+ Declares the package `com.ssh2lnx.code`, ensuring the class is part of this namespace.
```java
package com.ssh2lnx.code;
```

+ Defines the main class `SSHMain`.
```java
public class SSHMain {
```

+ Main method where execution starts.
```java
public static void main(String[] args) {
```

+ Creates an SSH connection using `SSHConnectionImpl`, passing host, username, and password from `SSHConstants`.
```java
    SSHConnection sshConnection = new SSHConnectionImpl(
            SSHConstants.HOST, SSHConstants.USER, SSHConstants.PASSWORD
    );
```

+ Creates instances of `SSHCommandExecutor` (for executing SSH commands) and `SFTPFileTransfer` (for file transfer).
```java
    SSHCommandExecutor commandExecutor = new SSHCommandExecutor(sshConnection);
    SFTPFileTransfer fileTransfer = new SFTPFileTransfer(sshConnection);
```

+ Runs the `uname` command (which outputs system info), then prints the result.
```java
    try {
        System.out.println("Running command: uname");
        String commandOutput = commandExecutor.executeCommand("uname");
        System.out.println("Output: " + commandOutput);
```

+ Transfers a file from the remote machine to the local machine.
```java
        System.out.println("Transferring file...");
        fileTransfer.transferFile(SSHConstants.REMOTE_FILE_PATH, SSHConstants.LOCAL_FILE_PATH);
```

+ Catches and prints any exceptions.
```java
    } catch (Exception e) {
        e.printStackTrace();
    }
```

---

#### 2. `SSHConstants.java` (Constant Values)

**Purpose:** Stores constant values such as SSH credentials and file paths.

+ Declares the package.
```java
package com.ssh2lnx.code;
```

+ Defines a class `SSHConstants`.
```java
public class SSHConstants {
```

+ Stores host, username, and password as constants.
```java
    public static final String HOST = "192.168.123.456";
    public static final String USER = "lnxComputer";
    public static final String PASSWORD = "password123";
```

+ Stores file paths for SFTP transfer.
```java
    public static final String REMOTE_FILE_PATH = "/home/lnxComputer/Desktop/sample.txt";
    public static final String LOCAL_FILE_PATH = "C:\\Users\\winComputer\\Desktop\\sample.txt";
```

---

#### 3. `SSHConnection.java` (Interface)

**Purpose:** Defines the contract for establishing an SSH connection.

+ Declares the package.
```java
package com.ssh2lnx.code;
```

+ Imports necessary classes for SSH connection handling.
```java
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
```

+ Defines an interface `SSHConnection`.
```java
interface SSHConnection {
```

+ Method to establish and return an SSH session.
```java
    Session connectSession() throws JSchException;
```

+ Method to disconnect an SSH session.
```java
    void disconnectSession(Session session);
```

---

#### 4. `SSHConnectionImpl.java` (Implementation of SSH Connection)

**Purpose:** Implements `SSHConnection` to establish SSH sessions using JSch.

+ Imports required JSch classes and Java utilities.
```java
package com.ssh2lnx.code;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;
```

+ Implements the `SSHConnection` interface.
```java
class SSHConnectionImpl implements SSHConnection {
```
+ Stores SSH credentials.
```java
    private final String host;
    private final String user;
    private final String password;
```

+ Constructor initializes connection parameters.
```java
    public SSHConnectionImpl(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }
```

+ Implements the `connectSession()` method.
```java
    @Override
    public Session connectSession() throws JSchException {
```

+ Creates a JSch session and sets login credentials.
```java
    JSch jsch = new JSch();
    Session session = jsch.getSession(user, host, 22);
    session.setPassword(password);
```

+ Disables host key checking (for easier but less secure connections).
```java
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    session.setConfig(config);
```

+ Establishes the SSH connection and returns the session.
```java
    session.connect();
    return session;
```

+ Disconnects the SSH session.
```java
    @Override
    public void disconnectSession(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
```

---

#### 5. SSHCommandExecutor.java (Execute SSH Commands)

**Purpose:** Allows running shell commands remotely via SSH.

+ Imports JSch classes for executing commands over SSH.
```java
package com.ssh2lnx.code;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
```

+ Stores a reference to `SSHConnection`.
```java
class SSHCommandExecutor {
    private final SSHConnection sshConnection;
```

+ Constructor initializes the SSH connection.
```java
    public SSHCommandExecutor(SSHConnection sshConnection) {
        this.sshConnection = sshConnection;
    }
```

+ Executes a shell command on the remote server.
```java
    public String executeCommand(String command) throws JSchException, IOException {
```

+ Opens an "exec" channel and sets the command.
```java
    Session session = sshConnection.connectSession();
    Channel channel = session.openChannel("exec");
    ((ChannelExec) channel).setCommand(command);
```

+ Retrieves command output and connects the channel.
```java
    InputStream inputStream = channel.getInputStream();
    channel.connect();
```

+ Reads command output from the stream.
```java
    StringBuilder output = new StringBuilder();
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        output.append(new String(buffer, 0, bytesRead));
    }
```

+ Disconnects the SSH session and returns the output.
```java
        channel.disconnect();
        sshConnection.disconnectSession(session);
        return output.toString();
    }
```

---

#### 6. `SFTPFileTransfer.java` (File Transfer via SFTP)

**Purpose:** Handles secure file transfers via SFTP.

+ Imports JSch classes for SFTP handling.
```java
package com.ssh2lnx.code;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
```

+ Stores a reference to `SSHConnection`.
```java
class SFTPFileTransfer {
    private final SSHConnection sshConnection;
```

+ Constructor initializes the SSH connection.
```java
    public SFTPFileTransfer(SSHConnection sshConnection) {
        this.sshConnection = sshConnection;
    }
```

+ Establishes an SFTP channel.
```java
    public void transferFile(String remotePath, String localPath) throws JSchException, SftpException {
        Session session = sshConnection.connectSession();
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
```

+ Downloads a file from the remote system.
```java
        sftpChannel.get(remotePath, localPath);
```

+ Closes the SFTP session and prints a success message.
```java
        sftpChannel.disconnect();
        sshConnection.disconnectSession(session);
        System.out.println("File transferred successfully from " + remotePath + " to " + localPath);
    }
```