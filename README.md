<h1 align="center">Ssh2Linux<br>
<img src="banner.gif" style="width: 50%"><br>
</h1>
  
Simple SSH Connection to Linux Server

> Linux Server Credentials :
```bash
username : lnxComputer
password : password123
ip addr  : 192.168.123.456
```

> Installation (Linux) :
```bash
sudo apt-get install openssh-server
```

**Note :**

_sudo_ in Linux stands for "superuser do." It's a command that allows users to execute commands with the security privileges of another user, usually the superuser (root).

This allows users to perform administrative tasks without logging in as the root user directly, users can execute commands or run programs that require elevated permissions.

> Configuration (Linux) :
```bash
sudo nano /etc/ssh/sshd_config
```
> Enable (un-comment #) the following (Linux) :
```
Port 22
PasswordAuthentication yes
UsePAM yes
```

> Start up the server and enable port 22 (Linux) :
```bash
sudo systemctl start ssh
sudo systemctl enable ssh
sudo ufw allow 22
sudo ufw enable
```
**Note :**

_ufw_ stands for Uncomplicated Firewall, it set up rules to allow or deny traffic based on ports, IP addresses, or protocols.

_systemctl_ is a command-line utility in Linux systems that is used to start, stop, restart, enable, disable, reload, and check the status of system services.

> Update ssh and check status (Linux) :
```bash
sudo systemctl restart ssh
sudo systemctl status ssh
```

> Command Prompt (Windows) :
```bash
ssh -p <port> <username>@<ip address>
ssh -p 22 lxnComputer@192.168.123.456
```

> Shutdown server and disable port 22 (Linux) :
```bash
sudo ufw disable
sudo ufw delete allow 22
sudo systemctl disable ssh
sudo systemctl stop ssh
```
