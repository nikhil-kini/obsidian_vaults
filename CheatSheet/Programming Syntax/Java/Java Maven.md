[[Java Basics]]
It is a Dependency management Tool.

A Maven project's pom.xml has the following sections:

1. **Project Information**: Mandatory section which contains the project co-ordinates to uniquely identify the project.
2. **Dependencies**: Optional section which refers to the libraries/artifacts/jars needed for the application.
3. **Build Plugin**: Optional section which refers to the plugin configurations needed for the application.
4. **Build Profile**: It is an optional section. This section usually has the various configuration settings using which the build can be customized for different environments - production, test, development, etc.

### POM’s Unique Co-ordinates:​​​​​​​

Maven coordinates is an address for uniquely identifying each project.

Every Maven project is uniquely identified using the following set of co-ordinates:

**groupId** :This element normally has a group name or organization name which owns the project

**artifactId** : This element gives information about the name of the application. eg: infycart

**version** : This element reveals the version of the distribution unit (jar/war/ear).
- **SNAPSHOT** is the general convention used to mention that the project is in active development phase. eg: infycart-0.0.1 - SNAPSHOT.jar
- **RELEASE** is the convention used to mention that the build of the project is the base-lined, stable version. eg: Spring Context - 4.3.14.RELEASE.jar

**packaging**: This element decides the type of the distribution unit. eg: jar/war/ear etc.

**The *settings.xml* will have the address and the required credentials for connecting to Proxy servers.**

