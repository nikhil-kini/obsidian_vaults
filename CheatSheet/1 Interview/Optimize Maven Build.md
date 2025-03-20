https://javanexus.com/blog/optimizing-maven-build-performance

- **Use Maven Wrapper** - To execute Maven builds without having Maven installed on the system.

- **Parallel Builds** - Maven supports parallel builds, allowing multiple modules to be built concurrently, thus reducing overall build times.
```xml
<build>
    <modules>
        <module>module1</module>
        <module>module2</module>
        <!-- Other modules -->
    </modules>
</build>
```

- **Profile based Configuration** - Maven allows the use of profiles to customize build configurations for different environments. This can be leveraged to optimize build performance by specifying different settings for development, testing, and production builds.
```xml
<profiles>
    <profile>
        <id>development</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <skipMain>true</skipMain>
                    </configuration>
                </plugin>
                <!-- Other plugins to exclude -->
            </plugins>
        </build>
    </profile>
</profiles>
```

- **Dependency Management**
	- **Dependency Scope** - Specify the appropriate dependency scope to prevent unnecessary **dependencies from being included in the build**. For example, if a dependency is only required for **compilation and not at runtime**, it should be marked with a `provided` scope:

```xml
<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
    <scope>provided</scope>
</dependency>
```

- **Dependency Exclusion** - Exclude transitive dependencies that are not needed for the project to reduce unnecessary bloat in the build:
```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>example-artifact</artifactId>
    <version>1.0.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.unwanted</groupId>
            <artifactId>unwanted-dependency</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

- I**ncremental Builds** - which allow it to recompile only the necessary modules and classes that have changed since the last build. This can significantly reduce build times, especially for large projects with many source files.
To enable incremental builds, ensure that the `<sourceDirectory>` and `<testSourceDirectory>` elements are properly configured within the project's `pom.xml`:
```xml
<build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <testSourceDirectory>src/test/java</testSourceDirectory>
</build>
```

Additionally, you can configure the `maven-compiler-plugin` to enable incremental compilation:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <useIncrementalCompilation>true</useIncrementalCompilation>
            </configuration>
        </plugin>
    </plugins>
```


- **Externalizing Configuration** -Externalizing configuration settings, such as plugin configurations and properties, can improve build performance by allowing for quicker iterative changes without the need for full project recompilation.
One way to achieve this is **by using external property files or environment variables to supply configurable values,** reducing the need to modify and rebuild the project for minor configuration tweaks.

