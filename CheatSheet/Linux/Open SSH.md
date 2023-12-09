Two types of  authentication:
- Password based
- SSH key pair (preferred)

## Installation

ssh is installed default in Linux for client(i.e. master)

On host (i.e. puppet)
```sh
sudo apt install openssh-server
```

## To Connect
```sh
shh ip_addres
 ```
 
```sh
ssh remote_username@remote_host
```

```sh
ssh -p port_number remote_host
```

```sh
ssh remote_host command_to_run
```

For X11 forwarding (will allow to access graphical apps from host). To enable edit `/etc/ssh/ssh_config`
```sh
ssh -X remote_host
```
## SSH key pair
```sh
ssh-keygen -t ed25519 -C "nikhil default"
```

t - type
ed25519 - key format
C - metadata

- Use passphrase to secure the SSH
- SSH key is stored in `.shh` of home folder
- The key name **must be unique, or else it will override the previous key**.

**To copy SSH key to connecting machine.**
```sh
ssh-copy-id -i path_to_file.pub ip_address_of_connecting
```
- The pub file will be copied to the `~/.shh` directory of the ip address, under `autherized_keys` file.

**To connect to machine with specific key**
```sh
ssh -i path_to_secret_key ip_address
```

**To cache the SSH passphrase**
```sh
eval $(ssh-agent)
```
- Need to enter the passphrase for new terminal window.

## Securing SSH Server

- Edit `/etc/ssh/ssh_config`
```sh
sudo vim /etc/ssh/ssh_config
```

- Un-comment and edit
```
PasswordAuthentication no
```

- Change port, choose any un-occupied port number between 0 and 65535
```
port port_number
```
*if firewall is installed, grant access to the port*
```sh
sudo ufw allow port_number
```

- restart `sshd`
```sh
systemctl restart sshd
```