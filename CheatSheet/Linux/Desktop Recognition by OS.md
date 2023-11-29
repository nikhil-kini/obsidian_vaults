Create the Menu icon for quick access...
```sh
sudo nvim /usr/share/applications/<PackageName>.desktop
```

Enter the following content
```
[Desktop Entry]
Name=SpringSource Tool Suite
Comment=SpringSource Tool Suite
Exec=/opt/sts/sts-3.9.5.RELEASE/STS
Icon=/opt/sts/sts-3.9.5.RELEASE/icon.xpm
StartupNotify=true
Terminal=false
Type=Application
Keywords=Java,Eclipse,Spring,IDE,Development
Categories=Development;IDE;Java;
```