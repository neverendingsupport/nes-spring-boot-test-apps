# NES for Spring - Maven Example

## Before
The sample `pom.xml` file shows a typical setup in an application generated using the ["Spring Initializr"](https://start.spring.io). 

Note the following `<parent>` and `dependency` sections.
```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.7.18</version>
  <relativePath />
</parent>
```
And corresponding dependencies section.

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <!-- ...others omitted for brevity... -->
</dependencies>
```

First, check the version of a Spring dependency. This example shows the dependency tree for the `spring-webmvc`. 

```shell
./mvnw dependency:tree -Dincludes=org.springframework:spring-webmvc
```

<details>

<summary>Example</summary>

```shell
➜ ./mvnw dependency:tree -Dincludes=org.springframework:spring-webmvc
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------------< com.example:demo >--------------------------
[INFO] Building NES Spring Boot Demo 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- dependency:3.3.0:tree (default-cli) @ demo ---
[INFO] com.example:demo:jar:0.0.1-SNAPSHOT
[INFO] \- org.springframework.boot:spring-boot-starter-web:jar:2.7.18:compile
[INFO]    \- org.springframework:spring-webmvc:jar:5.3.31:compile
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

</details>


## After: Using HeroDevs, NES for Spring

To use the NES version of Spring Boot, update the following. 

_For a complete example of the changes, see [here](https://github.com/neverendingsupport/nes-spring-boot-test-apps/compare/main...maven-sample-with-nes)._

1. Set up the NES registry as a Maven repository source
In your `${user.home}/.m2/settings.xml` or `${maven.home}/conf/settings.xml`.

```xml
<settings>
  <servers>
    <server>
      <id>herodevs-nes-registry</id>
      <username>your-username</username>
      <password>your-token</password>
    </server>
  </servers>
</settings>
```

Add to your Maven POM.

:bulb: _Note, the `<id>` tag must match the `server/id` in your global `settings.xml`._

```xml
<repositories>
  <repository>
    <id>herodevs-nes-registry</id>
    <url>https://registry.nes.herodevs.com/maven</url>
  </repository>
</repositories>
<pluginRepositories>
  <pluginRepository>
    <id>herodevs-nes-registry</id>
    <url>https://registry.nes.herodevs.com/maven</url>
  </pluginRepository>
</pluginRepositories>
```


2. Set the `groupId` and `version` of `spring-boot-starter-parent`
```xml
<parent>
  <groupId>com.herodevs.nes.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.7.18-spring-boot-2.7.19-rc19</version>
  <relativePath />
</parent>
```
2. Change the `groupId` of the Spring Boot dependencies
```xml
<dependencies>
  <dependency>
    <groupId>com.herodevs.nes.springframework.boot</groupId>
    <artifactId>spring-boot</artifactId>
  </dependency>
  <dependency>
    <groupId>com.herodevs.nes.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  <dependency>
    <groupId>com.herodevs.nes.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <!-- ...others omitted for brevity... -->
</dependencies>
```

3. Test New Dependencies

```shell
./mvnw dependency:tree -Dincludes=org.springframework:spring-webmvc
```
<details>

<summary>Example</summary>

```shell
➜  maven-example git:(maven-readme) ./mvnw dependency:tree -Dincludes=org.springframework:spring-webmvc
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------------< com.example:demo >--------------------------
[INFO] Building NES Spring Boot Demo 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- dependency:3.3.0:tree (default-cli) @ demo ---
[INFO] com.example:demo:jar:0.0.1-SNAPSHOT
[INFO] \- com.herodevs.nes.springframework.boot:spring-boot-starter-web:jar:2.7.18-spring-boot-2.7.19-rc19:compile
[INFO]    \- org.springframework:spring-webmvc:jar:5.3.31:compile
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

</details>
