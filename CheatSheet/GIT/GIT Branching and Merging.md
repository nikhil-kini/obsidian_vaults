
### Branching and Merging

**Remove Dead Branch**
This command will delete all the tracking information for branches that are on your local machine that are not in the remote repository, but it does not delete your local branches.
```sh
git remote update --prune
```

**Managing branches**
```sh
git branch bugfix     # Creates a new branch called bugfix
git checkout bugfix   # Switches to the bugfix branch
git switch bugfix     # Same as the above
git switch -C bugfix  # Creates and switches
git branch -d bugfix  # Deletes the bugfix branch
```

**Comparing branches**
```sh
git log master..bugfix  # Lists the commits in the bugfix branch not in master
git diff master..bugfix # Shows the summary of changes
```

**Stashing**
This command will stash (store them locally) all your code changes but does not actually commit them.
```sh
git stash push -m “New tax rules” # Creates a new stash
git stash list           # Lists all the stashes
git stash show stash@{1} # Shows the given stash
git stash show 1    # shortcut for stash@{1}
git stash apply 1   # Applies the given stash to the working dir
git stash drop 1    # Deletes the given stash
git stash clear     # Deletes all the stashes
```

**Merging**
```sh
git merge bugfix           # Merges the bugfix branch into the current branch
git merge --no-ff bugfix   # Creates a merge commit even if FF is possible
git merge --squash bugfix  # Performs a squash merge
git merge --abort          # Aborts the merge
```

**Viewing the merged branches**
```sh
git branch --merged       # Shows the merged branches
git branch --no-merged    # Shows the unmerged branches
```

**Rebasing**
```sh
git rebase master          # Changes the base of the current branch
```

**Cherry picking**
```sh
git cherry-pick dad47ed    # Applies the given commit on the current branch
```