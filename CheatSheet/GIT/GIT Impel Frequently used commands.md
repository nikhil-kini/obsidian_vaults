# GIT flow

PROD workspace -> To your personal workflow

| Scenario                                    | Fork Workflow          | Branch-in-Repo Workflow |
| ------------------------------------------- | ---------------------- | ----------------------- |
| Open-source                                 | ✅ Best                 | ❌ Not used              |
| Company/private repo                        | ❌ Unnecessary overhead | ✅ Standard              |
| Need strong repo isolation                  | ✅ Yes                  | ❌ No                    |
| Multiple devs collaborating on same feature | ❌ Difficult            | ✅ Ideal                 |
| Want simple PR workflow                     | ❌ More complex         | ✅ Simple                |

**We use Fork workflow**

To Check if the parent of the fork exists
```git
git remote -v
```

To add the parent to local, for tracking parent
```git
git remote add upstream https://github.com/ORIGINAL_OWNER/REPO.git
```

To fetch changes from upstream
```git
git fetch upstream
```

Merge upstream changes into your current local branch
```git
git checkout main // or the branck u want to pull changes
git pull --rebase upstream main // or the branck u want to pull changes
```

To push your changes
```git
git push upstream main // or the branck u want to push changes
```
