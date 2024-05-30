# NES Spring Boot - Gradle Example

## Before

The sample `build.gradle` file shows a typical setup in an application generated using the ["Spring Initializr"](https://start.spring.io).

```groovy
plugins {
  id 'java'
  id 'org.springframework.boot' version '2.7.18'
  id 'io.spring.dependency-management' version '1.0.15.RELEASE'
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
org.springframework:spring-web:5.3.31
+--- org.springframework:spring-webmvc:5.3.31
|    \--- org.springframework.boot:spring-boot-starter-web:2.7.18
|         \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)

```

<details>

<summary>Full example</summary>

```shell
➜ ./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web

org.springframework:spring-web:5.3.31 (selected by rule)
  Variant runtimeElements:
    | Attribute Name                      | Provided     | Requested    |
    |-------------------------------------|--------------|--------------|
    | org.gradle.status                   | release      |              |
    | org.jetbrains.kotlin.localToProject | public       |              |
    | org.jetbrains.kotlin.platform.type  | jvm          |              |
    | org.gradle.category                 | library      | library      |
    | org.gradle.dependency.bundling      | external     | external     |
    | org.gradle.jvm.version              | 8            | 11           |
    | org.gradle.libraryelements          | jar          | jar          |
    | org.gradle.usage                    | java-runtime | java-runtime |
    | org.gradle.jvm.environment          |              | standard-jvm |

org.springframework:spring-web:5.3.31
+--- org.springframework:spring-webmvc:5.3.31
|    \--- org.springframework.boot:spring-boot-starter-web:2.7.18
|         \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)
+--- org.springframework.boot:spring-boot-starter-json:2.7.18
|    \--- org.springframework.boot:spring-boot-starter-web:2.7.18 (*)
\--- org.springframework.boot:spring-boot-starter-web:2.7.18 (*)

org.springframework:spring-webmvc:5.3.31 (selected by rule)
  Variant runtimeElements:
    | Attribute Name                      | Provided     | Requested    |
    |-------------------------------------|--------------|--------------|
    | org.gradle.status                   | release      |              |
    | org.jetbrains.kotlin.localToProject | public       |              |
    | org.jetbrains.kotlin.platform.type  | jvm          |              |
    | org.gradle.category                 | library      | library      |
    | org.gradle.dependency.bundling      | external     | external     |
    | org.gradle.jvm.version              | 8            | 11           |
    | org.gradle.libraryelements          | jar          | jar          |
    | org.gradle.usage                    | java-runtime | java-runtime |
    | org.gradle.jvm.environment          |              | standard-jvm |

org.springframework:spring-webmvc:5.3.31
\--- org.springframework.boot:spring-boot-starter-web:2.7.18
     \--- runtimeClasspath (requested org.springframework.boot:spring-boot-starter-web)

(*) - Indicates repeated occurrences of a transitive dependency subtree. Gradle expands transitive dependency subtrees only once per project; repeat occurrences only display the root of the subtree, followed by this annotation.

A web-based, searchable dependency report is available by adding the --scan option.
```

</details>

## After: Using HeroDevs, Spring Boot NES

To use the NES version of Spring Boot, update the following.

_For a complete example of the changes,
see [here](https://github.com/neverendingsupport/nes-spring-boot-test-apps/compare/main...gradle-sample-with-nes)._

1. Set up the NES registry as a Gradle repository source in your global Gradle properties file
   e.g. `~/.gradle/gradle.properties`

```properties
herodevs_nes_registry_url=https://maven.pkg.github.com/neverendingsupport/spring-boot
herodevs_nes_registry_user=your-username
herodevs_nes_registry_token=your-token
```

Add the NES repository to the maven repos. This is done in two files.

* `settings.gradle`
  * Update the `pluginManagement` and `dependencyResolutionManagement` sections
* `build.gradle`
  * Add NES repository to `repositories` section

```groovy
// settings.gradle
pluginManagement {
  repositories {
    // ... 
    maven {
      url = uri(providers.gradleProperty("herodevs_nes_registry_url").get())
      credentials {
        username = providers.gradleProperty("herodevs_nes_registry_user").get()
        password = providers.gradleProperty("herodevs_nes_registry_token").get()
      }
    }
  }
}

dependencyResolutionManagement {
  repositories {
    // ... 
    maven {
      url = uri(providers.gradleProperty("herodevs_nes_registry_url").get())
      credentials {
        username = providers.gradleProperty("herodevs_nes_registry_user").get()
        password = providers.gradleProperty("herodevs_nes_registry_token").get()
      }
    }
  }
}

```

```groovy
// build.gradle
repositories {
  // ...
  maven {
    url = uri(providers.gradleProperty("herodevs_nes_registry_url").get())
    credentials {
      username = providers.gradleProperty("herodevs_nes_registry_user").get()
      password = providers.gradleProperty("herodevs_nes_registry_token").get()
    }
  }
}
```

2. Update the coordinates of the `org.springframework.boot` related dependencies 

```groovy
ext["spring-boot.version"] = "2.7.18-spring-boot-2.7.19-rc19"

dependencies {
  implementation "com.herodevs.nes.springframework.boot:spring-boot-starter-thymeleaf:${project.ext["spring-boot.version"]}"
  implementation "com.herodevs.nes.springframework.boot:spring-boot-starter-web:${project.ext["spring-boot.version"]}"
  developmentOnly "com.herodevs.nes.springframework.boot:spring-boot-devtools:${project.ext["spring-boot.version"]}"
  // ... others omitted for brevity
}
```

2. Test New Dependencies
```shell
./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web
```

Note the new parent for the `org.springframework:spring-web` dependency.

```shell
org.springframework:spring-web:5.3.31
+--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19 (*)
\--- org.springframework:spring-webmvc:5.3.31
     \--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19 (*)
```

<details>

<summary>Full example</summary>

```shell
➜ ./gradlew -q dependencyInsight --configuration runtimeClasspath --dependency spring-web

org.springframework:spring-web:5.3.31 (selected by rule)
  Variant runtime:
    | Attribute Name                 | Provided     | Requested    |
    |--------------------------------|--------------|--------------|
    | org.gradle.status              | release      |              |
    | org.gradle.category            | library      | library      |
    | org.gradle.libraryelements     | jar          | jar          |
    | org.gradle.usage               | java-runtime | java-runtime |
    | org.gradle.dependency.bundling |              | external     |
    | org.gradle.jvm.environment     |              | standard-jvm |
    | org.gradle.jvm.version         |              | 11           |

org.springframework:spring-web:5.3.31
+--- com.herodevs.nes.springframework.boot:spring-boot-starter-json:2.7.18-spring-boot-2.7.19-rc19
|    \--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19
|         \--- runtimeClasspath
+--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19 (*)
\--- org.springframework:spring-webmvc:5.3.31
     \--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19 (*)

org.springframework:spring-webmvc:5.3.31 (selected by rule)
  Variant runtime:
    | Attribute Name                 | Provided     | Requested    |
    |--------------------------------|--------------|--------------|
    | org.gradle.status              | release      |              |
    | org.gradle.category            | library      | library      |
    | org.gradle.libraryelements     | jar          | jar          |
    | org.gradle.usage               | java-runtime | java-runtime |
    | org.gradle.dependency.bundling |              | external     |
    | org.gradle.jvm.environment     |              | standard-jvm |
    | org.gradle.jvm.version         |              | 11           |

org.springframework:spring-webmvc:5.3.31
\--- com.herodevs.nes.springframework.boot:spring-boot-starter-web:2.7.18-spring-boot-2.7.19-rc19
     \--- runtimeClasspath

(*) - Indicates repeated occurrences of a transitive dependency subtree. Gradle expands transitive dependency subtrees only once per project; repeat occurrences only display the root of the subtree, followed by this annotation.

A web-based, searchable dependency report is available by adding the --scan option.
```

</details>
