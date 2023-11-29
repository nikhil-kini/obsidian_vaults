
1. Open a terminal
2. Use the command `cd` to navigate to the correct folder. If there is a README file with installation instructions, use that instead.
3. Extract the files with one of the commands
    - If it's **tar.gz** use `tar xvzf PACKAGENAME.tar.gz`
    - if it's a **tar.bz2** use `tar xvjf PACKAGENAME.tar.bz2`
4. `./configure`
5. `make`
6. `sudo make install` (or with [`checkinstall`](https://help.ubuntu.com/community/CheckInstall))