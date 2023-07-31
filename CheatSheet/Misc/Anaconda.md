#anaconda #conda 

### Post Installation

#### The base environment is activated by default
```sh
conda config --set auto_activate_base True
```

#### The base environment is not activated by default
```sh
conda config --set auto_activate_base False
```


### Conda Channels
conda "Channels" are the locations where conda stores the software packages. Conda searches these locations for the requested software and its stored in internet.

**To show all channels** 
```sh
conda config --show channels
```

**To add channels**
```sh
conda config --add channels name_of_the_channels
```

### Conda Environments
Environment are the places where installed tools are stored for running the program.

**To show all environments**
```sh
conda env list
```

**To create environments** 
```sh
conda create -n name_of_the_env
```

**To activate environments** 
```sh
conda activate name_of_the_env
```

**To deactivate environments** 
```sh
conda deactivate 
```


