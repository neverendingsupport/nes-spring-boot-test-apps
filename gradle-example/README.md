# NES for Spring - Gradle Example

## Before

The sample `build.gradle` file shows a typical setup in an application generated using the ["Spring Initializr"](https://start.spring.io).

```groovy
plugins {
  id 'java'
  id 'org.springframework.boot' version '1.5.22.RELEASE'
}
```

And corresponding dependencies section.

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
  implementation 'org.springframework.boot:spring-boot-starter-web'
  developmentOnly 'org.springframework.boot:spring-boot-devtools'
  // ... others omitted for brevity
}
```

First, check the version of a Spring dependency. This example shows the dependency tree for the `spring-webmvc`.

```shell
./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web
```

Note the parent for the `org.springframework:spring-web` dependency.

```shell
oorg.springframework:spring-web:4.3.25.RELEASE
+--- org.springframework:spring-webmvc:4.3.25.RELEASE
|    \--- org.springframework.boot:spring-boot-starter-web:1.5.22.RELEASE
|         +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
```

<details>

<summary>Full example</summary>

```shell
➜ ./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web

org.springframework:spring-web:4.3.25.RELEASE (selected by rule)
   variant "runtime" [
      org.gradle.status              = release (not requested)
      org.gradle.usage               = java-runtime
      org.gradle.libraryelements     = jar
      org.gradle.category            = library

      Requested attributes not found in the selected variant:
         org.gradle.dependency.bundling = external
         org.gradle.jvm.version         = 8
   ]

org.springframework:spring-web:4.3.25.RELEASE
+--- org.springframework:spring-webmvc:4.3.25.RELEASE
|    \--- org.springframework.boot:spring-boot-starter-web:1.5.22.RELEASE
|         +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
|         \--- org.springframework.boot:spring-boot-starter-thymeleaf:1.5.22.RELEASE
|              \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-thymeleaf)
\--- org.springframework.boot:spring-boot-starter-web:1.5.22.RELEASE (*)

org.springframework:spring-webmvc:4.3.25.RELEASE (selected by rule)
   variant "runtime" [
      org.gradle.status              = release (not requested)
      org.gradle.usage               = java-runtime
      org.gradle.libraryelements     = jar
      org.gradle.category            = library

      Requested attributes not found in the selected variant:
         org.gradle.dependency.bundling = external
         org.gradle.jvm.version         = 8
   ]

org.springframework:spring-webmvc:4.3.25.RELEASE
\--- org.springframework.boot:spring-boot-starter-web:1.5.22.RELEASE
     +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
     \--- org.springframework.boot:spring-boot-starter-thymeleaf:1.5.22.RELEASE
          \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-thymeleaf)

(*) - dependencies omitted (listed previously)

A web-based, searchable dependency report is available by adding the --scan option.
```

</details>

## After: Using HeroDevs, NES for Spring

To use the NES version of Spring Boot, update the following.
***FINISH*** 