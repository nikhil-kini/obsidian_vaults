#git 

### Collaboration

**Connect To GitHub using ssh**
[refer this article](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)

**Cloning a repository**
```sh
git clone url
git clone shh_link
```

**Syncing with remotes**
```sh
git push remoteName branchName  # Syntax

git fetch origin master      # Fetches master from origin
git fetch origin             # Fetches all objects from origin
git fetch                    # Shortcut for “git fetch origin”
git checkout origin/food     # view the origin remote changes

git pull                     # Fetch + merge
git push origin master       # Pushes master branch to origin remote
git push                     # Shortcut for “git push origin master” pushes all branches
```

**Sharing tags**
```sh
git push origin v1.0              # Pushes tag v1.0 to origin
git push origin —delete v1.0
```

**Sharing branches**
```sh
git branch -r                 # Shows remote tracking branches
git branch -vv                # Shows local & remote tracking branches
git push -u origin bugfix     # Pushes bugfix to origin
git push -d origin bugfix     # Removes bugfix from origin
```

**Managing remotes** #gitImp 
```sh
git remote                     # Shows remote repos
git remote add upstream url    # Adds a new remote called upstream (name of the remote)
git remote rm upstream         # Removes upstream
```

**syncing the remote with your forked branch**
```sh
git fetch upstream
git switch master
git reset --hard upstream/master
```
