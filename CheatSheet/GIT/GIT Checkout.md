## Move back in commit
To move to previous commit
```shell
git checkout commit_id
```

```sh
git status
error : Detached Head error 
```

To move head back to the latest Commit of the branch
```sh
git switch branchName
git switch -             # shorthand for the move to latest commit in the same branch
# or 
git checkout branchName
```

To work on the old Commit, create a new branch and work.
```sh
git switch -c newBranch

git status
```

## Discard Changes
#gitImp 

To Discard changes since last commit (`git restore` does the same thing)
```sh
git checkout HEAD fileName1 fileName2

git checkout -- fileName1 fileName2
```

```sh
git restore fileName fileName2
```


```sh
git reset --hard HEAD    # Discards local changes
git clean -n             # Get info on untracked files
git clean -nd            # Get info on untracked directories
git clean -f             # delete info on untracked files
git clean -fd            # delete info on untracked directories
```

To Discard changes since **mentioned** commit
```sh
git restore --source HEAD~2 fileName fileName2
```

To Remove file from the staging, i.e. file that is already staged.
```sh
git restore --staged fileName fileName2
```



