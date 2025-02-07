<h1 align="center">Ssh2Linux<br>
<img src="banner.gif" style="width: 50%"><br>
</h1>

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
