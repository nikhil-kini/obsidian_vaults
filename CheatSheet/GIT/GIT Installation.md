#git 

[[GIT Creating Snapshots]]
[[GIT Browsing History]]
[[GIT Branching and Merging]]
[[GIT Collaboration]]
[[GIT Rewriting History]]

### Git Config

System -> All users at system level.
Global -> All repository at current user level.
Local -> The current repository.


### Initial Setup after Installation

**Set Git User**
```bash
git config --global user.name "Nikhil Kini"
```

**Set Git Email**
```bash
git config --global user.email nikhilrocklee@gmail.com
```

**Set Editor (optional)**
```bash
git config --global core.editor "gedit --wait"
```

**To open the Config file in default editor**
```bash
git config --global -e
```

**End of line handling**
Very important for cross-platform OS work.
* Windows uses `\r` and `\n` to represent end of line
* Linux and mac uses only `\n`

To configure edit `core.autocrlf` file
* For Windows
```sh
git config --global core.autocrlf true  
```
* For Linux/Mac
```sh
git config --global core.autocrlf input
```

**Set external visual diff tools (Optional)**
```sh
git config --global diff.tool kompare
```

```sh
git config --global difftool.kompare.cmd "kompare $LOCAL $REMOTE" 
```

**Initial Git Push**

* Create Repository in GitHub.
* Run following command.
```sh
echo "# obsidian_vaults" >> README.md   # file with dummy text
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:nikhil-kini/obsidian_vaults.git
git push -u origin main
```




