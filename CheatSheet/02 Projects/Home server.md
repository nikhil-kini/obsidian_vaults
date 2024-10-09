
1. Add username to sudoers file
```sh
su
```

```sh
echo 'username ALL=(ALL) ALL' >> /etc/sudoers
```

```sh
exit
```
exit `su`

2. Auto-mount the drive
- get `UUID` of the drive
```sh
sudo blkid
```
-  create a mount-point
```sh
sudo mkdir /mnt/drive-name-example-sdb1
```
- auto-mount drive
```sh
sudo nano /etc/fstab
```

```txt
# share drive auto-mount
UUID=    <mnt-point> <file-system> auto rw,user,uid=1000,dmask=0000,fmask=0000,nosuid,nodev,nofail,x-gvfs-show 0 0

UUID=e12kv-... /mnt/sdb1  auto rw,user,uid=1000,dmask=0000,fmask=0000,nosuid,nodev,nofail,x-gvfs-show 0 0
```

```sh
reboot
```

3. Install `curl`
```sh
sudo apt install curl
```

4. Install docker , refer docker install section
5. Configure `properties.env` file to your requirements
6. docker-compose up inside the `home-docker-compose` folder pulled form git

```sh
docker network create proxy-network
docker network create db-network
```

```sh
docker compose --env-file properties.env -f ./core/compose.yml up -d
```
```sh
docker compose --env-file properties.env -f ./ops/compose.yml up -d
```
```sh
docker compose --env-file properties.env -f ./apps/compose.yml up -d
```