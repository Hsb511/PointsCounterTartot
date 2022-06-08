# PointsCounterTartot

This is an android application that computes the scores for different card games.
For the first version the only available game is french tarot. 

# What it looks like

The **Home screen** where you access all of your games and create new ones

| light mode in english | dark mode in french |
|-------------------|-------------------|
| ![home light mode en](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/home_light_en.jpg?raw=true) | ![home dark mode fr](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/home_dark_fr.jpg?raw=true) |

The **French tarot screen** where you follow the current scores of your players and of each round

| light mode in english | dark mode in french |
|-------------------|-------------------|
| ![tarot light mode en](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/scores_light_en.jpg?raw=true) | ![tarot dark mode fr](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/scores_dark_fr.jpg?raw=true) |

The **Form tarot screen** allows you to create a new game

| light mode in english | dark mode in french |
|-------------------|-------------------|
| ![form light mode en](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/form_light_en.jpg?raw=true) | ![form dark mode fr](https://github.com/Hsb511/PointsCounterTartot/screenshots/v1/form_dark_fr.jpg?raw=true) |


# Technical stack

This native Android application in full kotlin is developed according to a clean architecture with MVVM.
It targets every SDK between 24 and 32 so Android version 7 to 12.
Libraries :
- **Jetpack Compose** version 1.2.0
- **Room** version 2.4.2
- **Hilt** version 2.41
