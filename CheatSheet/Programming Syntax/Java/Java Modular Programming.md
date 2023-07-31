[[Java Basics]]
Modular Programming brings in the following features to the mix:

1. **Module Descriptor:** With the introduction of modules in Java, each modular Java project now needs a Module Descriptor file. This file is named as _module-info.java_ and is located in the root directory of the respective module. The Module Descriptor, as the name suggests, defines the packages that are exposed (can be exported) and the packages that are required by the module to function. For example:
```Java
module com.xyz.calculator{
    //Sample Module Descriptor (Empty)
}
```

2. **Exports and Requires:** For a Java module to export/expose packages for external use or import packages from other modules, proper declarations are needed to be done in the Module Descriptor file of the module. And these declarations are handled by the keywords _exports_ and _requires_. Exports is used to declare the packages that are meant to be exposed. Whereas, Requires is used to declare the packages (from other modules) that are needed by the respective module. For example:
```Java
module com.xyz.calculator{
    //Export and Requires Declaration:
    exports com.xyz.calculator;
    requires com.xyz.service;
}
```

3. **Circular Dependencies Not Allowed:** Java Modules do not allow to have circular dependencies between modules. In simpler words, if a module X requires another module Y then the reverse (Y requiring X) is not allowed. It is expected that the module dependency must by acyclic.
4. **Split Packages Not Allowed:** Split Packaging is referred to the phenomenon of exporting the same package(s) from two different modules. It signifies that the content of the respective package is divided into multiple modules.
5. **Multi Java Version Modules:** With the introduction of modules from Java 9, it is now possible to create JAR files for Java modules written in different versions of Java. To be simply put, we can create JAR files for modules written in Java 8, 9, 10 or 11 within the same JAR file.