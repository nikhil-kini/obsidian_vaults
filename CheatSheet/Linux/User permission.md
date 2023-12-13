## Intro

On Linux systems, rights are essentially divided into two categories:

- **Ownership**: the user and the group owning the file, meaning that they either created it or they were assigned as owners of the file or directory.
- **Permissions**: given a file or a directory, permissions represent the set of actions that you are able to perform depending on who you are (the user account you are logged in) and the group that you belong to.

![](ownerships-final.png)

to check Linux permissions
```sh
ls -al
```

![](ls-output-columns.png)

### Understanding Linux File Types

- On Linux, **[everything is a file.](https://www.howtogeek.com/117939/htg-explains-what-everything-is-a-file-means-on-linux/)**
- As a consequence, **links are files, but directories are also files**
- Most of common type, **file (-)**, a **directory (d)** or a **link (l)**.
![](file-types.png)

- Permissions are divided into three categories : **user permissions, group permissions and the “other” permissions**.
- On each of those categories, you either have a **letter or a dash**.

### Understanding Linux File Permissions
#### File Permissions

- Permissions are divided into three categories : **user permissions, group permissions and the “other” permissions**.
- A dash simply means that you don’t have the permission.
- Letters can either be **r** for **read** access, **w** for **write** access and **x** for the permission to “**execute it**“.

|   |   |
|---|---|
|**Permission**|**Description**|
|**r** (or read)|The user, group or others can **read** the file, with a command such as **cat**, or **vi** (in read-only mode)|
|**w** (or write)|The user, group or others can **modify** and **save** the file with commands such as **nano** or **vi**|
|**x** (or execute)|The user, group or others can **execute** the file. This is most of the time used for **scripts**.|

#### Directory Permissions

|   |   |
|---|---|
|**Permission**|**Description**|
|**r** (or read)|The user, group or others can **list** the content of the directory (using a **ls** command for example)|
|**w** (or write)|The user, group or others can **add** or **delete** files from the directory|
|**x** (or execute)|The user, group or others can **go through the directory** for navigation.|

## Understanding the Binary Numeral System
### Linux Permissions using the binary and decimal systems

![](binary-format-1024x920.png) 
A **666** permission, or a “`r w – r w – r w -`” permission.

Similarly, **777** permission, or a ”` r w x r w x r w x`” permission.
## What is the Linux permission mask?

**The Linux permission mask is a mask that sets the permissions for newly created files.**

To know the mask,run
```sh
unmask
```

```
0022
```
focus on the last three digits of the mask : “**022**”

Every time you create a file or a directory, your system will apply the mask, consisting of applying consecutive bitwise operations to your initial set of permissions.

The only thing you have to remember is that files are created with a **666** permission, or a “r w – r w – r w -” permission.

Similarly, directories are created with a **777** permission, or a ” r w x r w x r w x” permission.

But those permissions are BEFORE applying the mask.

Here’s the resulting set of permissions when you apply the mask to them.
![](linux-mask-585x453.png)

## How to manage permissions on a Linux system?

managed by using three commands: **chmod**, **chown** and **chgrp**.

### a – Using chmod

Using **chmod** is pretty straightforward.

The chmod command **modifies the permissions** of a file using either the **decimal form** or the **symbolic form**.

#### Modifying permissions using the decimal form

```sh
chmod 421 file_directory
```

![](chmod-form.png)

|   |   |
|---|---|
|**Command**|**Resulting permissions**|
|chmod **777** file|r w x r w x r w x (**not recommended!**)|
|chmod **444** file|r – – r – – r – – (**read-only** permissions)|
|chmod **421** file|r – – – w – – – x (owner can **read**, group can **write**, others can **execute**)|
|chmod **000** file|– – – – – – – – – (**no permissions** at all)|

#### Modifying permissions using the symbolic form

![](chmod-2.png)

|   |   |
|---|---|
|**Command**|**Consequence on permissions**|
|chmod **u+rwx** file|Adding the **read, write and execute** to the **user** (or owner of the file)|
|chmod **go+r** file|Adding the **read** permission to the **group** and the **other**s **category**.|
|chmod **o+rx** file|Adding the **read** and **execute** permissions to the **others category**|
|chmod **u-r** file|Removing the **read** permission for the **owner** of the file.|

### b – Using chown

**Chown is a command that sets the owner of a file or directory.**

![](chown-format.png)

|   |   |
|---|---|
|**Command**|**Resulting permissions**|
|**chown** bob secretfile|Assigning **bob** as the owner of the **secretfile**|
|**chown** bob file1 directory1|Assigning bob as the owner of the file1 and of the directory1|
|**chown** bob:users file1|Assigning **bob** as the owner and **users** as the group for the file1|
|**chown** :users file1|Assigning users as the group for the file1|

### c – Using chgrp

**Chgrp is a command that sets the group property for a file or a directory.**

![](chgrp-syntax.png)

|   |   |
|---|---|
|**Command**|**Resulting permissions**|
|**chgrp** users file1|Assigning **users** as the group for the file1|
|**chgrp -R** users directory1|Applying the **users** group **recursively** to directory1 and children.|
|**chgrp -c** users file1|Assigning the **users** group to the file1 and giving all the **changes** done in the terminal|

## What are the suid, guid and sticky bit?

### a – Understanding the suid

Using the symbolic form, the **suid** is symbolized by the letter **s**, meaning that you would run:
![](suid.png)
**The *suid* is used to execute a command as the owner of the file (in this case root) instead of the user that issued the command in the first place (devconnected in this case).**

#### Setting the suid using chmod

the **suid** is symbolized by the letter **s** for **executable**, meaning that you would run:
```sh
chmod u+s file #(to set the suid for the user)
chmod u-s file #(to remove the suid for the user)
```

**4 to set suid**

|   |   |
|---|---|
|**Command**|**Resulting permissions**|
|**chmod** 4777 file1|Gives read, write, execute permissions to everybody – and the file1 will be executed as the owner of the file.|
|**chmod** 4444 file1|Gives read permissions to everybody, the suid will be set, but the file is not executable.|
|**chmod** u+s file1|Set the suid for the file1, the file will be executed as the owner of the file.|
|**chmod** 0777 file1|Gives read, write, execute permissions to everybody – and the suid is not set.|

*Note: There are **4 digits** in the permissions*
You can still set the suid for a file even if the **file is not executable.** As a consequence, the suid will be displayed with a capital **S** instead of a lowercase s.

![](capital-S.png)
### b - Understanding the guid

**Using the same logic, the *guid* is used to execute a file as a member of the group owning the file.**

**2 to set guid**

|   |   |
|---|---|
|**Command**|**Resulting permissions**|
|**chmod** 2777 file1|Gives read, write, and execute permissions to every user – and the file1 will be executed as a member of the group of the file.|
|**chmod** 0777 file1|Read, write, and execute permissions to every user – but the guid is not set.|
|**chmod** g+s file1|Set the guid active for the file1, the file will be executed a member of the group owning the file1|
|**chmod** 2444 file1|Read-only permissions to every user and the guid is set (with a capital S in the permission line)|

if the directory has its **guid** set but no permissions to execute the file, it will be represented with a capital **S** on the permission line. Similarly for executable is **s**.

### Understanding the sticky bit

- if a user has write and execute permissions on a folder, he will be able to add files to it, but also to delete files from it.
- I want to be able to add files to a directory, as well as modify their contents, but I don’t want other users to move or delete my files (even if they own the permissions to add files themselves).

![](linux-sticky-bit.png)

the sticky bit is represented with the letter **t**.

```sh
chmod +t directory1 # (adds the sticky bit to the directory1)
chmod -t directory1 # (removes the sticky bit from the directory1)
```

**1 to set sticky bit**

|   |   |
|---|---|
|**Command**|**Resulting Permissions**|
|**chmod** +t file1|Setting the sticky bit for the file1|
|**chmod** -t file1|Removing the sticky bit for the file1|
|**chmod** 1444 file1|Read-only permissions, along with the sticky bit for the file1|
|**chmod** 7777 file1|Full permissions : suid, guid and sticky bit set (as well as read, write and execute)|

![](sticky-bit-example.png)



### Chown Recursively

**The easiest way to use the chown recursive command is to execute “chown” with the “-R” option for recursive and specify the new owner and the folders that you want to change.**

```sh
chown -R <owner> <folder_1> <folder_2> ... <folder_n>
```

### Chown User and Group Recursively

**In order to change the user and the group owning the directories and files, you have to execute “chown” with the “-R” option and specify the user and the group separated by colons.**

``` sh
chown -R <user>:<group> <folder_1> <folder_2> ... <folder_n>
```

**IMP:** On Linux, executing commands such as **chown**, **chmod** or **rm** is definitive : **there is no going back.**