# NES for Spring - Gradle Example

## Before

The sample `build.gradle` file shows a typical setup in an application generated using the ["Spring Initializr"](https://start.spring.io).

```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.22.RELEASE")
  }
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

_For a complete example of the changes,
see [here](https://github.com/neverendingsupport/nes-spring-boot-test-apps/compare/main-spring-boot-1.5...gradle-sample-nes-spring-boot-1.5)._

1. Set up the NES registry as a Gradle repository source in your global Gradle properties file
   e.g. `~/.gradle/gradle.properties`

```properties
herodevs_nes_registry_url=https://registry.nes.herodevs.com/maven
herodevs_nes_registry_user=your-username
```

Add the NES repository to the `repositories` in `build.gradle`.

```groovy
buildscript {
  repositories {
    maven {
      url = uri("${herodevs_nes_registry_url}")
      credentials {
        username = "${herodevs_nes_registry_user}"
      }
    }
  }
  //...
}

repositories {
  maven {
    url = uri("${herodevs_nes_registry_url}")
    credentials {
      username = "${herodevs_nes_registry_user}"
    }
  }
  mavenCentral()
}
```

2. Update the version of the `org.springframework.boot` Gradle plugin.

```groovy
dependencies {
  classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.22-spring-boot-1.5.24-trial")
}
```

. Test New Dependencies
```shell
./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web
```

If the setup was successful, the original `org.springframework:spring-web` dependency is replaced by the new version in NES for Spring Framework managed by NES for Spring Boot.

```shell
org.springframework:spring-web:4.3.30-spring-framework-4.3.32-trial
+--- org.springframework:spring-webmvc:4.3.30-spring-framework-4.3.32-trial
|    \--- org.springframework.boot:spring-boot-starter-web:1.5.22-spring-boot-1.5.24-trial
|         +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
|         \--- org.springframework.boot:spring-boot-starter-thymeleaf:1.5.22-spring-boot-1.5.24-trial
|              \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-thymeleaf)
\--- org.springframework.boot:spring-boot-starter-web:1.5.22-spring-boot-1.5.24-trial (*)
```

<details>

<summary>Full example</summary>

```shell
➜   ./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web
org.springframework:spring-web:4.3.30-spring-framework-4.3.32-trial (selected by rule)
   variant "runtime" [
      org.gradle.status              = release (not requested)
      org.gradle.usage               = java-runtime
      org.gradle.libraryelements     = jar
      org.gradle.category            = library

      Requested attributes not found in the selected variant:
         org.gradle.dependency.bundling = external
         org.gradle.jvm.version         = 8
   ]

org.springframework:spring-web:4.3.30-spring-framework-4.3.32-trial
+--- org.springframework:spring-webmvc:4.3.30-spring-framework-4.3.32-trial
|    \--- org.springframework.boot:spring-boot-starter-web:1.5.22-spring-boot-1.5.24-trial
|         +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
|         \--- org.springframework.boot:spring-boot-starter-thymeleaf:1.5.22-spring-boot-1.5.24-trial
|              \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-thymeleaf)
\--- org.springframework.boot:spring-boot-starter-web:1.5.22-spring-boot-1.5.24-trial (*)

org.springframework:spring-webmvc:4.3.30-spring-framework-4.3.32-trial (selected by rule)
   variant "runtime" [
      org.gradle.status              = release (not requested)
      org.gradle.usage               = java-runtime
      org.gradle.libraryelements     = jar
      org.gradle.category            = library

      Requested attributes not found in the selected variant:
         org.gradle.dependency.bundling = external
         org.gradle.jvm.version         = 8
   ]

org.springframework:spring-webmvc:4.3.30-spring-framework-4.3.32-trial
\--- org.springframework.boot:spring-boot-starter-web:1.5.22-spring-boot-1.5.24-trial
     +--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
     \--- org.springframework.boot:spring-boot-starter-thymeleaf:1.5.22-spring-boot-1.5.24-trial
          \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-thymeleaf)

(*) - dependencies omitted (listed previously)

A web-based, searchable dependency report is available by adding the --scan option.
```

</details>