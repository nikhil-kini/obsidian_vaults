
## Installation

* Git clone 
```bash
git clone https://github.com/tmux-plugins/tpm ~/.tmux/plugins/tpm
```

#### Terminal multiplexer

###### Session Control (from the command line)

|   |   |
|---|---|
|`tmux`|Start a new session|
|`tmux new -s <session-name>`|Start a new session with the name chosen|
|`tmux ls`|List all sessions|
|`tmux attach -t <target-session>`|Re-attach a detached session|
|`tmux attach -d -t <target-session>`|Re-attach a detached session (and detach it from elsewhere)|
|`tmux kill-session -t <target-session>`|Delete session|
| `tmux detach` | To Detach a session |

###### Copy-Mode (vi)

|   |   | |
|---|---|---|
|`Ctrl` `b`, `[`|Enter copy mode| `Ctrl` `a`, `[`
|`Ctrl` `b`, `G`|Bottom of history|
|`Ctrl` `b`, `g`|Top of history|
|`Ctrl` `b`, `Enter`|Copy selection| `y`
|`Ctrl` `b`, `p`|Paste selection|
|`Ctrl` `b`, `k`|Cursor Up| `k`
|`Ctrl` `b`, `j`|Cursor Down| `j`
|`Ctrl` `b`, `h`|Cursor Left| `h`
|`Ctrl` `b`, `l`|Cursor Right| `l`
| | Scroll down | `J`
| | Scroll up | `K`
| | Visual mode | `v` `<vim motion>`
| | Exit copy mode | `Ctrl` `a`, `c`



###### Pane Control

|   |   |  |
|---|---|---|
||To refresh the Tmux config| `Ctrl` `a`, `r`
|`Ctrl` `b`, `s`|To list all session in Tmux| `Ctrl` `a`, `s`
|`Ctrl` `b`, `"`|Split pane horizontally| `Ctrl` `a`, `-`
|`Ctrl` `b`, `%`|Split pane vertically| `Ctrl` `a`, \|
|`Ctrl` `b`, `o`|Next pane|
|`Ctrl` `b`, `;`|Previous pane|
|`Ctrl` `b`, `q`|Show pane numbers|
|`Ctrl` `b`, `z`|Toggle pane zoom|
|`Ctrl` `b`, `!`|Convert pane into a window|
|`Ctrl` `b`, `x`|Kill current pane|
|`Ctrl` `b`, `Ctrl` `O`|Swap panes|
|`Ctrl` `b`, `t`|Display clock|
|`Ctrl` `b`, `q`|Transpose two letters (delete and paste)|
|`Ctrl` `b`, `{`|Move to the previous pane|
|`Ctrl` `b`, `}`|Move to the next pane|
|`Ctrl` `b`, `Space`|Toggle between pane layouts|
|`Ctrl` `b`, `↑`|Resize pane (make taller)| `Ctrl` `a`, `k`
|`Ctrl` `b`, `↓`|Resize pane (make smaller)| `Ctrl` `a`, `j`
|`Ctrl` `b`, `←`|Resize pane (make wider)| `Ctrl` `a`, `h`
|`Ctrl` `b`, `→`|Resize pane (make narrower)| `Ctrl` `a`, `l`
||maximize/min pane| `Ctrl` `a`, `m`
| `Ctrl` `b`, `I` | To install plugins | `Ctrl` `a`, `I`

###### Window Control

|   |   |  |
|---|---|---|
|`Ctrl` `b`, `c`|Create new window| `Ctrl` `a`, `c`
|`Ctrl` `b`, `d`|Detach from session| `Ctrl` `a`, `d`
|`Ctrl` `b`, `,`|Rename current window| `Ctrl` `a`, `,`
|`Ctrl` `b`, `&`|Close current window| `Ctrl` `a`, `&`
|`Ctrl` `b`, `w`|List windows| `Ctrl` `a`, `w`
|`Ctrl` `b`, `p`|Previous window| `Ctrl` `a`, `p`
|`Ctrl` `b`, `n`|Next window| `Ctrl` `a`, `n`
|`Ctrl` `b`, `<window number>`| Go to window number | `Ctrl` `a`, `<window number>`


###### Copy-Mode (Emacs)

|   |   |
|---|---|
|`Ctrl` `b`, `[`|Enter copy mode|
|`Ctrl` `b`, `M-<`|Bottom of history|
|`Ctrl` `b`, `M->`|Top of history|
|`Ctrl` `b`, `M-m`|Back to indentation|
|`Ctrl` `b`, `M-w`|Copy selection|
|`Ctrl` `b`, `M-y`|Paste selection|
|`Ctrl` `b`, `Ctrl` `g`|Clear selection|
|`Ctrl` `b`, `M-R`|Cursor to top line|
|`Ctrl` `b`, `M-r`|Cursor to middle line|
|`Ctrl` `b`, `↑`|Cursor Up|
|`Ctrl` `b`, `↓`|Cursor Down|
|`Ctrl` `b`, `←`|Cursor Left|
|`Ctrl` `b`, `→`|Cursor Right|
