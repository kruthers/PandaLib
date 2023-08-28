# PandaLib

- [About](#about)
- [Included Libraries](#packaged-libraries)
- [How to use](#using-the-library)

---

# About
[^ back to top](#pandalib)

A Common Library plugin containing common libraries and functions for my plugins.

This is mainly used to help keep plugin sizes down and allow for easy cross compatibility

# Packaged Libraries
[^ back to top](#pandalib)

These are the induced libraries and their versions.

 - [Kotlin](https://kotlinlang.org/) ![Version](https://img.shields.io/badge/V-1.9.0-green.svg)
 - [Cloud](https://github.com/Incendo/cloud/tree/master/docs) ![Version](https://img.shields.io/badge/V-1.7.1-green.svg)
   - cloud-core 
   - cloud-kotlin-extensions
   - cloud-paper
   - cloud-minecraft-extras
 - [Kyori Adventure](https://docs.advntr.dev/index.html) ![Version](https://img.shields.io/badge/V-4.14.0-green.svg)
   - adventure-api
   - adventure-text-serializer-plain
   - adventure-text-minimessage
   - adventure-text-feature-pagination ![Version](https://img.shields.io/badge/V-4.0.0%20SNAPSHOT-yellow.svg)

# Using the library
[^ back to top](#pandalib)

To proven clashes with other plugins all the packaged libraries have been relocated to `com.kruthers.lib`
<br>*Note Adventure is **not** relocated to allow it to work with paper still*

To use on gradle it requires use of [Shadow](https://imperceptiblethoughts.com/shadow/)

### Gradle (Groovy) 
```groovy
dependencies {
     compileonly "<library here from its sources>"
}

//todo
```

### Gradle (Kotlin DSL)
```groovy
dependencies {
    compileonly("<library here from its sources>")
}

tasks {
    shadowJar {
        relocate("cloud","com.kruthers.lib.cloud")
        relocate("kotlin","com.kruthers.lib.kotlin")
    }
}
```
