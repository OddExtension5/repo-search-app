# Repp Search App

A simple kotlin based android app for searching GitHub repositories on phone by providing search terms like language name, library, framework etc., It will give you a list of all repositories given your search term.

And you can also search for particular user by using GitHub username and can see users repos list.

**App Status**: 1.0 (minimal version)  [In Development]

# Screenshot

![Image1](https://ibb.co/dP7Qwm7)

![image2](https://ibb.co/XtmPSSw)

# Getting Started

Instructions to setup and run this project on your system

## Prerequisites
List of things needed for running the app successfully on your system

> 1. Java 8 or above
> 2. Android Studio 4.0

And you need either a AVD or a physical android device of API 21 and above to run this project.

## Running

1. Clone this repo if you have git installed by using ``git clone <clone or download url>'
2. Open it in Adroid Studio (4.0)
3. Sync in all the gradle dependencies
4. Then, build and run the project


# External Library Used

1. ``Retorfit``: For working with my GitHUB API. It turns your HTTP API into a Kotlin Interface. For further reading [click here](https://square.github.io/retrofit/)

2. ``Picasso``: It mainly used for fetching users github avatar and displaying it on my app. A picasso is a powerful image downloading and caching library for Android. For documentation [click here](https://square.github.io/picasso/)

3. ``Gson``: Used Gson for converting my Java Objects into a JSON implementation. FOr documentation [click here](https://github.com/google/gson/blob/master/UserGuide.md)

Sync all of it in your ``build.gradle`` under dependencies:

```gradle
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'

```

**Congratualitions, now you can play with my app**
