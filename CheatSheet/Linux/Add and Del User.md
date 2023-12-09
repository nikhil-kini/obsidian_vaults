## User Add

```sh
sudo useradd <username>
```

```sh
sudo passwd <username>
```

Above commands combined, which ask additional information.
```sh
sudo adduser user_name
```

## Deleting a user using deluser

```sh
sudo deluser <username>
```

To remove a user with its home directory, run the deluser command with the –remove-home parameter.
```sh
$ sudo deluser --remove-home <username>
```

```
Looking for files to backup/remove
Removing user 'user'
Warning: group 'user' has no more members.
Done.
```

To delete all the files associated with a user, use the –remove-all-files parameter.
```sh
sudo deluser --remove-all-files <username>
```

## Deleting a sudo user with visudo

If you removed a sudo user on Debian, it is very likely that there is a remaining entry in your sudoers file.

To delete a user from the sudoers file, run visudo.
```sh
sudo visudo
```

Find the line corresponding to the user you just deleted, and remove this line.
```
<username>    ALL=(ALL:ALL) ALL
```