#git 

### Branching and Merging

**Remove Dead Branch**
This command will delete all the tracking information for branches that are on your local machine that are not in the remote repository, but it does not delete your local branches.
```sh
git remote update --prune
```

**Managing branches** #gitImp
Pull = fetch + merge
```sh
git branch            # To dispaly the branches
git branch -v         # To get the verbose version of all the branches list

git branch bugfix     # Creates a new branch called bugfix from the current head (does not switch to new branch )

git checkout bugfix   # Switches to the bugfix branch (old way, does multiple function)

git switch bugfix     # Same as the above (preffered way to switch)
git switch -C bugfix  # Creates and switches to the new branch.

# Should be outside of the branch to be deleted
git branch -d bugfix  # Deletes the bugfix branch. (checks if the branch is merged or not)
git branch -D bugfix  # Deletes irrespective of the brach status. (force delete)

# Should be in the branch to be renamed
git branch -m newName # The branch will be renamed to the newName

git branch -a         # To dispaly all the branches

git branch -r         # To dispaly all the remote branches
```

**Comparing branches**
```sh
git log master..bugfix  # Lists the commits in the bugfix branch not in master
git diff master..bugfix # Shows the summary of changes
```

**Stashing**
This command will stash (store them locally) all your code changes but does not actually commit them.
Used when switching branches
#gitImp 
Most commonly used
```shell
git stash  # Stashes the changes 
git pop    # Bring backs the changes to the working directory and deletes the saved stash
```

No so commonly used
```sh
git stash push -m “New tax rules” # Creates a new stash, with message
git stash list           # Lists all the stashes
git stash show stash@{1} # Shows the given stash
git stash show 1    # shortcut for stash@{1}
git stash apply 1   # Applies the given stash changes to the working dir 
git stash drop 1    # Deletes the given stash
git stash clear     # Deletes all the stashes
```

**Merging**
* Fast-Forward merge
	*  The master-branch shouldn't have a commit, then a Fast-Forward merge is done.
	*   All the commits of the feature-branch are brought to the master-branch.
*  Merge Commit
	*   The master-branch should have a commit and **NO CONFLICT FOR AUTO MERGE**.
	*   The master-branch should have a commit and **CONFLICT FOR MANUAL MERGE**.
		* Need to resolve using a text-editor.
		* Delete the content that is not needed from the file.
		* Close the file
	* All the commits of the feature-branch are brought into master-branch **with a new Commit**.
```
<<<<<<<<<<HEAD
code 1
==============
code 2
>>>>>>>>>>>>Branch 2
```

```
code 1 + code 2 (refractored, both kept, or type whatever you want)
```

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