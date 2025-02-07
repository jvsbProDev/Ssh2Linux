<h1 align="center">Ssh2Linux<br>
<img src="banner.gif" style="width: 50%"><br>
</h1>
  
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
