- Make `Desktop` not writable.
```sh
chmod 550 ~/Desktop
```
- Launch multiple Frequently used apps
1. Open gedit (or any other text editor).
2. Paste the following commands:
```sh
#!/bin/bash
 
application1 & application 2 & application3
```
Replace “application1”, “application2” etc. with the name of the application that you want to launch.
3. Save the file (you can give it any name you want). You can either end the filename with a .sh extension, or just leave the extension blank. For me, I just name it “java-dev”.
4. Next, give the file an executable permission.
```sh
chmod +x java-dev
```
 5. To Launch from terminal, All the files in this folder are treated as commands rather than text file.
```sh
sudo cp blogging-apps /usr/bin/blogging-apps
```
 6. To launch from the app-launcher follow, [[Desktop Recognition by OS]]
