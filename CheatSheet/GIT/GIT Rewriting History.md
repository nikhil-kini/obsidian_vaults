#git 

### Rewriting History

**Destroy Local Changes**
One can wipe out all changes on your local branch to exactly what is in the remote branch.
```sh
git reset --hard origin/main
```

**Undoing commits** (doesn't keep the undid commit)
```sh
git reset --soft HEAD    # Removes the last commit, keeps changed staged,  HEAD or Commit HASH
git reset --mixed HEAD   # Unstages the changes as well
git reset --hard HEAD    # Discards local changes
```

**Reverting commits**
The revert command simply allows us to undo any commit on the current branch **with a new commit keeps the old commits has well**.
```sh
git revert 72856ea                # Reverts the given commit
git revert HEAD~3..               # Reverts the last three commits
git revert --no-commit HEAD~3..
```

** Use *reset* if the code is not already pushed to the git repo, if the code is pushed further change needs to be done in *revert* to avoid conflict**


**Recovering lost commits** ( on local machine )
This command lets you easily see the recent commits, pulls, resets, pushes, etc on your local machine.
```sh
git reflog                 # Shows the history of HEAD
git reflog show bugfix     # Shows the history of bugfix pointer
```

**Amending the last commit** #gitImp
Used to add file to the very last commit.
	1. Previous commit (missed a file).
	2. Stage the file.
	3. Use the below command, will open editor for commit msg change, save and quit editor for the save.
```sh
git commit --amend
```

**Interactive rebasing**
```sh
git rebase -i HEAD~5
```