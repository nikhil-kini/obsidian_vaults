#git 

### Creating Snapshots  

HEAD -> Point to the current working branch. (It's a branch pointer.)

**Initializing a repository**
```sh
git init
```

**Staging files**
```sh
git add file1.js # Stages a single file  
git add file1.js file2.js # Stages multiple files  
git add *.js # Stages with a pattern  
git add . # Stages the current directory and all its content 
```

**To view staged files**
```sh
git ls-files 
```

**Viewing the status**
```sh
git status # Full status  
git status -s # Short status
```

**Committing the staged files**
```sh
git commit -m “Message” # Commits with a one-line message  
git commit              # Opens the default editor to type a long message
```

**Skipping the staging area**  
```sh
git commit -am “Message”
```

**Removing files**  
```sh
git rm file1.js # Removes from working directory and staging area  
git rm --cached file1.js # Removes from staging area only
```

**Renaming or moving files**  
```sh
git mv file1.js file1.txt
```

**Viewing the staged/unstaged changes**  #gitImp 
```sh
git diff             # Compares Staging Area and Woring Directory  
git diff HEAD        # Compares Last Commit to the Working directory (inclueds staged and unstaged changes)
git diff --staged    # Shows Only staged changes  
git diff --cached    # Same as the above 


# Order Matters for assigning the diff notation
# A - first file/branch/commit mentioned in the command
# B - second file/branch/commit mentioned in the command
git diff fileName fileName2  # Show diff only for the files metioned.

git diff branch1..branch2 # Show diff between the branch1 and branch2
git diff branch1 branch2 # Show diff between the branch1 and branch2

git diff commit1..commit2 # Show diff between the branch1 and branch2
```


```diff
diff --git a/filea.extension b/fileb.extension
index d28nd309d..b3nu834uj 111111
--- a/filea.extension
+++ b/fileb.extension
@@ -1,6 +1,6 @@
-oldLine
+newLine
```
**Line 1** – It's two versions of the same file. Git named it **A** for the **1st version** and **B** for the **2nd version**.
- A – Old version of file
- B – New version of file

**Line 2** – Meta data about the file.

**Line 3** – Git assigned a minus sign (-) to the **A** version of the file and a plus sign (+) to the **B** version of the file.

*If **A** path to file is null its a new file that was added.*
- The line starting with the (-) symbol came from the A version
- The line starting with the (+) symbol came from the B version

**Line 5** – Every chunk starts with a **Chunk header**. The chunk header will be identified by **@@** (at the start and end). Then, there are two sets of numbers. Can you see the **-1** and **+1**?
- **-1** means from the **A version** file, extracting one line starting at line 1.
- **+1** means from the **B version** file, extracting one line starting at line 1.

If the sets look like **-3,4** **+3,2,** then:
- **-3,4** means from **A version** file, extracting four lines starting from line 3.
- **+3,2** means from **B version** file, extracting two lines starting from line 3.

**Viewing the history** #gitImp   refer [[GIT Browsing History]]
```sh
git log # Full history  
git log --oneline # Summary  
git log --reverse # Lists the commits from the oldest to the newest  
```

**Viewing a commit**  
```sh
git show 921a2ff # Shows the given commit  
git show HEAD # Shows the last commit  
git show HEAD~2 # Two steps before the last commit  
git show HEAD:file.js # Shows the version of file.js stored in the last commit  
```

**Unstaging files (undoing git add)**  
```sh
git restore --staged file.js # Copies the last version of file.js from repo to index  
```

**Discarding local changes**  
```sh
git restore file.js # Copies file.js from index to working directory  
git restore file1.js file2.js # Restores multiple files in working directory  
git restore . # Discards all local changes (except untracked files)  
git clean -fd # Removes all untracked files
```

**Restoring an earlier version of a file**  
```sh
git restore --source = HEAD~2 file.js
```

