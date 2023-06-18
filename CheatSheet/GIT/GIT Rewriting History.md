#git 

### Rewriting History

**Destroy Local Changes**
One can wipe out all changes on your local branch to exactly what is in the remote branch.
```sh
git reset --hard origin/main
```

**Undoing commits**
```sh
git reset --soft HEAD^    # Removes the last commit, keeps changed staged
git reset --mixed HEAD^   # Unstages the changes as well
git reset --hard HEAD^    # Discards local changes
```

**Reverting commits**
The revert command simply allows us to undo any commit on the current branch.
```sh
git revert 72856ea                # Reverts the given commit
git revert HEAD~3..               # Reverts the last three commits
git revert --no-commit HEAD~3..
```

**Recovering lost commits**
This command lets you easily see the recent commits, pulls, resets, pushes, etc on your local machine.
```sh
git reflog                 # Shows the history of HEAD
git reflog show bugfix     # Shows the history of bugfix pointer
```

**Amending the last commit**
```sh
git commit --amend
```

**Interactive rebasing**
```sh
git rebase -i HEAD~5
```