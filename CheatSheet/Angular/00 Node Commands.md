To verify Node version
```sh
node -v
```

Use **n** module from npm in order to upgrade node
```sh
sudo npm cache clean -f
sudo npm install -g n
sudo n stable
```

```sh
npm update -g npm
```

To get outdated packages in the project
```sh
npm outdated
```

To update the project
```sh
npm update
```

To Initialize Node
```sh
npm init
```

To install packages
```sh
npm i package
```

To install packages globally
```sh
npm i package -g # Use sudo if necessary
```
Note: 
* All the installed packages will be in the `nod_module` folder.
* All package to be installed can also be added to the `package.json` file.

To install dependencies in `package.json`
```sh
npm i
```

