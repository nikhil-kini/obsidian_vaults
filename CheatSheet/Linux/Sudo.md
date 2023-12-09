## Install
```sh
apt update
apt install sudo
```

## Give sudo access to user
```sh
usermod -a -G sudo <user_name>
```

or

edit `visudo` file
```sh
visudo
```
add line
```
<user_name> ALL=(ALL:ALL) ALL
```

To allow user to **access sudo without password**
```
<user_name> ALL=(ALL:ALL) NOPASSWD:ALL
```

![](sudoers-syntax-585x308.png)
