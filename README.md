<h1 align="center">Ssh2Linux<br>
<img src="banner.gif" style="width: 50%"><br>
</h1>
  
Simple SSH Connection to Linux Server

> Linux Server Credentials :
```bash
username : lnxComputer
password : password123
inet (IP Address) : 192.168.123.789
```

> Installation :
```bash
sudo apt-get install openssh-server
```

> Configuration :
```bash
sudo nano /etc/ssh/sshd_config
```
> Enable (un-comment #) the following :
```
Port 22
PasswordAuthentication yes
UsePAM yes
```

> Start up the server and enable port 22 :
```bash
sudo systemctl start ssh
sudo systemctl enable ssh
sudo ufw allow 22
sudo ufw enable
```

> Update ssh and check status :
```bash
sudo systemctl restart ssh
sudo systemctl status ssh
```

> Shutdown server and disable port 22 :
```bash
sudo ufw disable
sudo ufw delete allow 22
sudo systemctl disable ssh
sudo systemctl stop ssh
```
