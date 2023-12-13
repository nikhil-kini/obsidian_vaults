Create the Menu icon for quick access...
```sh
sudo nvim /usr/share/applications/<PackageName>.desktop
```

Enter the following content
```
[Desktop Entry]
Name=SpringSource Tool Suite              # name
Comment=SpringSource Tool Suite           # comment
Exec=/opt/sts/sts-3.9.5.RELEASE/STS       # path to exeutable
Icon=/opt/sts/sts-3.9.5.RELEASE/icon.xpm  # path to icon
StartupNotify=true
Terminal=false
Type=Application
Keywords=Java,Eclipse,Spring,IDE,Development  # key words for search
Categories=Development;IDE;Java;              # Categories
```