
*Install NeoVim from the source has the apt package manager installs Old version*

#### Config path
```
~/.config/nvim
```

*The Vim file explorer uses Nerd fonts you need to install it to get file [icons
link](https://bytexd.com/how-to-install-nerd-fonts-on-linux/)* 

#### To-install fonts:

**Copy the downloaded file to path**

```sh
mkdir ~/.local/share/fonts
```

```sh
cp FiraMono.zip ~/.local/share/fonts/
```

**Unzip the file the font i used**

```sh
cd ~/.local/share/fonts
```

```sh
unzip FiraMono.zip 
```

```sh
rm FiraMono.zip
```

*Now cache it to the fonts by using*

```sh
fc-cache -fv
```

You need to install the ripgrep for Telescope to [work link]( https://github.com/BurntSushi/ripgrep)

```sh
sudo apt install ripgrep
```

*Need to install the **nodejs** for treesitter lsp for js and ts*

#### Keyboard Shortcuts

| Keys | Action |
|-----|------|
| space_bar | \<leader> Key (L')|
| Ctrl J, K, L, H | navigate windows |
| L' nh | clears the search highlight|
| L' + | increment the number |
|L' - | decrement the number |
|L' sv | split window vertically
|L' sh | split window horizontally
|L' se | make split window equal width
| L' sx | close current split window
| L' sm | to maximize the split
|L' to | open new tab
|L' tx | close current tab
| L' tn | go to next tab
| L' tp  | go to previous tab
| ys \<motion> \<char> | to enclose with \<char>, `ys w '` for single quote enclosure
| ds \<motion> \<char> | to de-enclose with \<char>
| cs \<motion> \<char> | to change enclose with \<char>
| y w | to copy word
| gr w | to replace the existing word with copied word
| gc \<motion> | to comment and un-comment, `gcc` for a line comment, `gc9j` for 9 line below comment
| L' e | to open project files (nvim tree)




**Nvim  tree**

| Keys | Action |
|-----|------|
| a | to add file or folder inside the selected folder


**Telescope**

| Keys | Action |
|-----|------|
| Ctrl  k | move to previous selection |
| Ctrl j | move to next selection |
| Ctrl q | send item to quick fix list |
| L' ff | find_files in current directory |
| L' fs | live grep, search string in current directory|
|L' fc | grep string, search word below cursor in the current file|
| L' fb | buffers, show us our active buffers
|L' fh | Help docs

**Auto-complete Suggestion**

| Keys | Action |
|-----|------|
| Ctrl k | previous suggestion
| Ctrl j | next suggestion
| Ctrl b | scroll docs -4 lines
| Ctrl f | scroll docs 4 lines
| Ctrl Space | show completion suggestion
| Ctrl e | close completion window

**LSP**

| Keys | Action |
|-----|------|
| gf | lsp finder
| gD | buffer declaration
| gd | peek definition
| gi | buffer implementation
| L' rn | rename
| L' d | show line diagnostic
| L' d | show cursor diagnostic
| \[d | diagnostic jump previous
| ]d | diagnostic jump next
| K | hover docs
| L' o | ls outline toggle
| L' rf | rename typescript file
| L' ca | for Error Code completion


to do 
* remove insert jk short in keymap.conf
* config arrows in nvim-tree.lua
* add angular to lsp mason.lua and also add config for lspconfig.lua
*  remove slevete from treesitter.lua