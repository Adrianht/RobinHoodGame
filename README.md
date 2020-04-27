# RobinYoHood

This is a repository for an Android game developed with libGDX and Firebase Realtime Database.
The game was developed as the project delivery of the course TDT4240 Software Architecture at 
The Norwegian University of Science and Technology(NTNU). The course and project emphasized the use of
known architectural tools and patterns to build software. More thorough documentation was 
submitted in addition to the repository.

## Relevant repository structure (android/src/com/robinhood/game)

```
├── assetManagers
│   ├── AudioManager.java
│   └── GameAssetManager.java
├── controller
│   ├── Controller.java
│   └── FBConnector.java
├── model
│   ├── ArrowEntityPool.java
│   ├── BodyFactory.java
│   ├── Components.java
│   ├── Entity.java
│   ├── Model.java
│   └── Systems.java
├── view
│   ├── interfaceObjects
│   │    ├── Archer.java
│   │    ├── Arrow.java
│   │    ├── DragIndicator.java
│   │    ├── EnergyBar.java
│   │    └── HealthBar.java
│   ├── GameOverView.java
│   ├── GameView.java
│   ├── InstructionsView.java
│   ├── LoadingView.java
│   ├── MenuView.java
│   ├── SettingsView.java
│   └── View.java
├── AndroidLauncher.java
└── RobinHood.java
```

* Game assets can be found in android/assets/

## Getting Started

These instructions will get you a copy of the project up and running on 
your local machine for development and testing purposes.

### Prerequisites

* [Android Studio](https://developer.android.com/studio) or other IDE suitable for Android development. 
Note that the rest of this description will be based on the use of Android Studio as IDE.
* [Emulator](https://developer.android.com/studio/run/emulator) or Android device.

### Installing

1. Clone or download this repository to your computer. For example, by running the following command
(NB: this will require that you have [git](https://git-scm.com/) installed):
```
git clone https://github.com/Adrianht/RobinHoodGame.git
```

2. Open the project folder in Android Studio.
3. Run the project with your preinstalled emulator or on your Android device.

## Built With

* [Android Studio](https://developer.android.com/studio) - IDE
* [libGDX](https://libgdx.badlogicgames.com/) - Game-development framework
* [Box2D](https://box2d.org/) - 2-dimensional physics simulator
* [Firebase Realtime Database](https://firebase.google.com/docs/database) - The database system connecting players
* [Gradle](https://maven.apache.org/) - Build and automation system

## Authors

* **Nina Andal Aarvik** - *RobinYoReport* - [ninaandal](https://github.com/ninaandal)
* **Lars Gjelstad** - *RobinYoBox2D* - [lkgjelst](https://github.com/lkgjelst)
* **Ola Hermann Opheim** - *RobinYoEntity* - [olahop](https://github.com/olahop)
* **Eivind Rydningen** - *RobinYoMusic* - [eivz0r](https://github.com/eivz0r)
* **Charlotte Söderström** - *RobinYoTrello* - [charlottesoderstrom](https://github.com/charlottesoderstrom)
* **Adrian Thompson** - *RobinYoFirebase* - [Adrianht](https://github.com/Adrianht)

## Acknowledgments

Sources of inspiration:
* [LibGDX and Box2D](https://www.gamedevelopment.blog/full-libgdx-game-tutorial-project-setup/)
* [Entity-Component-System](https://github.com/erikhazzard/RectangleEater/blob/master/scripts/game.js)
* [Layout and Skins](https://github.com/czyzby/gdx-skins)
