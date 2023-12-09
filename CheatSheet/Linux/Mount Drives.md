## To List all drives
```
lsblk -f
```

## Mount Drives on Linux
```sh
sudo mount <device_sda_or_sda1_etc> <dir_path>
```
ex:
```sh
sudo mount /dev/sda1 ~/mountpoint
```

## Mounting Drives Permanently using fstab

The “**fstab**” file is a very important file on your filesystem.

Fstab stores **static information about filesystems**, **mountpoints** and several options that you may want to configure.

To list permanent mounted partitions on Linux, use the “**cat**” command on the `fstab` file located in `/etc`.

```sh
cat /etc/fstab
```

The fstab contains multiple columns :

- **Filesystem** : you can either specify a UUID (for universal unique identifier), a label (if you chose a label for your disk), a network ID or a device name (which is not recommended at all);
- **Mountpoint** : the directory on the filesystem that you are going to use in order to access data stored on the disk;
- **Filesystem type** : the type of filesystem you use to [format your disk](https://devconnected.com/how-to-format-disk-partitions-on-linux/);
- **Options** : some options that you can specify in order to tune your mount (“ro” for a read-only mount or “noexec” to prevent binary execution);
- **Dump** : in order to enable to disable filesystem dumping on the system (using the dump command);
- **Pass Num** : sets the order used in order for the “fsck” utility to check your filesystem. If you are not mounting the root device, you should set this option to “2” or “0” as “1” is reserved for the root device.

## Add Drive Partition to the fstab file

In order to add a drive to the `fstab` file, you first need to get the UUID of your partition.

**To get the UUID of a partition on Linux, use “`blkid`” with the name of the partition you want to mount.**

```sh
blkid /dev/sda1
```

```
/dev/sda1: UUID="0935df16-40b0-4850-9d47-47cd2daf6e59" TYPE="ext4" PARTUUID="7125fcc698a-01"
```

Now that you have the UUID for your drive partition, you can add it to the `fstab` file.

Open the `/etc/fstab` file and add one line for your new drive partition.

```sh
sudo nano /etc/fstab
```
```
# <file system>              <mount point>              <type>  <options>   <dump>  <pass>
UUID=0935df16-40b0-48      /home/user/mountpoint      ext4    defaults    0       0       
```

## Unmounting drives on Linux using umount

**Note** : the “**umount**” command should not be misspelled for “**unmount**” as there are no “unmount” commands on Linux.
```sh
sudo umount <device|directory>
```
ex:
```
sudo umount /dev/sdc1
```