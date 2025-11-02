https://javanexus.com/blog/optimizing-maven-build-performance

## Maven Build Life Cycle

Maven defines **three main lifecycles**:

|Lifecycle|Purpose|
|---|---|
|**clean**|Cleans up the project (removes previous build files).|
|**default (build)**|Builds, tests, and packages the project.|
|**site**|Generates documentation and reports.|

Each lifecycle consists of a **sequence of phases**, and each phase can trigger **goals** (tasks).

## Clean Lifecycle

Used to remove previous build artifacts.

|Phase|Description|
|---|---|
|**pre-clean**|Tasks before cleaning (e.g., backup).|
|**clean**|Deletes the `target/` directory (compiled files, jars, etc.).|
|**post-clean**|Tasks after cleaning (e.g., logs, reports).|

## Default (Build) Lifecycle

This is the **core** lifecycle — it handles compilation, testing, packaging, and installation.

|Phase|Description|
|---|---|
|**validate**|Checks if the project structure and configuration (POM) are valid.|
|**compile**|Compiles the source code (`src/main/java`).|
|**test**|Runs unit tests using frameworks like JUnit/TestNG.|
|**package**|Packages compiled code into a JAR/WAR.|
|**verify**|Runs checks to verify the package is valid.|
|**install**|Installs the package into the local Maven repository (`~/.m2/repository`).|
|**deploy**|Deploys the package to a remote repository (for sharing with others).|

## Site Lifecycle

Used to generate documentation and project reports.

| Phase           | Description                                     |
| --------------- | ----------------------------------------------- |
| **pre-site**    | Tasks before site generation.                   |
| **site**        | Generates project documentation (HTML reports). |
| **post-site**   | Processes the generated site.                   |
| **site-deploy** | Uploads the site to a remote server.            |

## Maven version dependency conflict
```sql
mvn dependency:tree
```
It prints the full dependency graph and highlights conflicts.

Example output:

```sql
[INFO] +- org.springframework.boot:spring-boot-starter-web:3.2.2 [INFO] |  \- org.springframework.boot:spring-boot:3.2.2 [INFO] +- org.springframework.boot:spring-boot-starter-data-jpa:3.0.6 [INFO]    \- org.springframework.boot:spring-boot:3.0.6 (omitted for conflict with 3.2.2)
```

🧩 `omitted for conflict with ...` means Maven **ignored** that version.

|Tip|Why|
|---|---|
|Use a single BOM|Keeps all framework dependencies consistent|
|Avoid hardcoding versions everywhere|Use dependencyManagement instead|
|Regularly check with `mvn dependency:tree`|Detect issues early|
|Use Enforcer plugin|Prevent accidental version drift|
|Upgrade all related libraries together|Prevent partial upgrades|

## Ways to Resolve Maven Version Conflicts

Let’s go step-by-step 👇
---

### ✅ Option 1: Use `<dependencyManagement>`

Define the correct version **centrally** so all modules inherit the same version.

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot</artifactId>
      <version>3.2.2</version>
    </dependency>
  </dependencies>
</dependencyManagement>
```

Then, in child modules:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot</artifactId>
</dependency>
```

✅ Ensures consistent version across the project.

---

### ✅ Option 2: Use `<exclusions>`

Exclude the unwanted transitive dependency.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>3.0.6</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
        </exclusion>
    </exclusions>
</dependency>

```

✅ Prevents older or conflicting version from being pulled in.

---

### ✅ Option 3: Use `<dependencyManagement>` in Parent POM (Multi-module projects)

Parent POM sets versions, children just reference dependencies without specifying version.

**Parent POM**

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.17.0</version>
    </dependency>
  </dependencies>
</dependencyManagement>

```

**Child POM**

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
</dependency>

```
---

### ✅ Option 4: Use Maven Enforcer Plugin

To **fail the build** when conflicting versions exist.

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-enforcer-plugin</artifactId>
      <version>3.4.0</version>
      <executions>
        <execution>
          <id>enforce-dependency-convergence</id>
          <goals>
            <goal>enforce</goal>
          </goals>
          <configuration>
            <rules>
              <DependencyConvergence />
            </rules>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>

```

✅ Build fails if two versions of the same dependency appear.

---

### ✅ Option 5: Align with BOM (Bill of Materials)

Frameworks like Spring Boot provide a **BOM** to handle versions automatically.

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>3.2.2</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

```

✅ Keeps all Spring dependencies in sync.





## Optimize Maven Build

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

