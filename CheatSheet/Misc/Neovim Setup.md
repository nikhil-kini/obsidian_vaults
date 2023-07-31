
*Install NeoVim from the source has the apt package manager installs Old version*

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