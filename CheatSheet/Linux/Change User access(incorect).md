

First check who owns the directory

```bash
ls -la /usr/local/lib/node_modules
```

it is denying access because the node_module folder is owned by root

```bash
drwxr-xr-x   3 root    wheel  102 Jun 24 23:24 node_modules
```

so this needs to be changed by changing root to your user but first run command below to check your current user [How do I get the name of the active user via the command line in OS X?](https://stackoverflow.com/questions/1104972/how-do-i-get-the-name-of-the-active-user-via-the-command-line-in-os-x)

`id -un` OR `whoami`

Then change owner

```bash
sudo chown -R [owner]:[owner] /usr/local/lib/node_modules
```

OR

```bash
sudo chown -R ownerName: /usr/local/lib/node_modules
```

OR

```bash
sudo chown -R $USER /usr/local/lib/node_modules
```