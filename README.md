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
   - cloud-annotations
 - [Kyori Adventure](https://docs.advntr.dev/index.html) ![Version](https://img.shields.io/badge/V-4.14.0-green.svg)
   - adventure-api
   - adventure-text-serializer-plain
   - adventure-text-serializer-legacy
   - adventure-text-minimessage
   - adventure-platform-bukkit ![Version](https://img.shields.io/badge/V-4.3.0-yellow.svg)
   - adventure-text-feature-pagination ![Version](https://img.shields.io/badge/V-4.0.0%20SNAPSHOT-yellow.svg)

# Using the library
[^ back to top](#pandalib)

To use this library just install the plugin in your plugins folder. Add `PandaLib` as a dependency and make sure when 
including the libraries you set them to be `compileonly` (gradle) or `provided` (maven). This way it won't build
the library into you plugin.
