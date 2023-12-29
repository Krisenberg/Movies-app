# Movies app

- [Version 1](#version-1)
- [Version 2 (Version 1 + ExoPlayer with trailers)](#version-2)

> [!CAUTION]
> If you would like to build and run the application in the *release* mode then you need to add those four lines to your `local.properties` file:
> > RELEASE_STORE_FILE=\<your path to the *.jks file>  
> > RELEASE_STORE_PASSWORD=\<your password>  
> > RELEASE_KEY_ALIAS=\<your alias>  
> > RELEASE_KEY_PASSWORD=\<your password>  

# Version 2
### Description
The second version of the app was supposed to be an extended version of the application created previously (in the first version). New requirements:
- the app must allow the user to play at least a 1-minute video for at least 3 items from the list of movies,
- the provided player must supply some controls to switch between the videos, play/pause the video, and stop it properly.

## Demo
The app is working perfectly fine both in the Portrait and Landscape modes. The player allows the user to switch between trailers. If the user plays the trailer in Portrait or Landscape mode and then rotates the device to the other mode then the player resumes from the same moment that it was stopped before the app recomposition. The player pauses automatically after clicking the device's built-in *Back arrow*.  

Screenshots presented below show the basic usage of the extended application. The details screen (after choosing a specific item from the *Details* tab) looks the same as previously.

<p float='left'>
  <img src='screenshots/Screenshot_7.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_8.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_9.jpg' width='216' height='444'>
</p>
<p float='left'>
  <img src='screenshots/Screenshot_10.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_11.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_12.jpg' width='444' height='216'>
</p>

## Implementation details
In this section are described only those elements that were added or were changed in comparison to the [Version 1](#version-1).

`MainViewModel` is a *HiltViewModel* that uses a *SavedStateHandle* and an *ExoPlayer* injection to the constructor. It stores four variables via *SavedStateHandle* that are needed to persist during some configuration changes (like a screen rotation). During its initialization `MainViewModel` loads the data from a simulated database to be able to preprocess it and provide it in a convenient way for other functions.

`MoviePlayerModule` creates a *Hilt module* that provides an *ExoPlayer* dependency scoped to *ViewModels*. When a *ViewModel* annotated with *@HiltViewModel* requests an *ExoPlayer* dependency, *Hilt* uses this module to create a new *ExoPlayer*.

`MainScreen` defines the layout of the app's main screen. It uses a *TabRow* and *HorizontalPager* to create two swipeable tabs: one called *Details* with the list of the movies (to keep the functionality from the [Version 1](#version-1)) and one called *Trailers* to handle extended functionality of playing some videos related to the movies.

`TrailerScreen` defines the content of the card from the list inside the *Trailers* tab. It uses *Column* with two *Rows*. The first one holds an image and a title, the second one is by default hidden and only when the user clicks on the card then this row is expanded. It holds two buttons that allow the user to select whether the trailer should be played in the *Preview* or *Fullscreen* mode. *Preview* mode uses a *Dialog* with a *PlayerView* that stops the *ExoPlayer* in case of a *DismissRequest*.

`FullscreenTrailerScreen` defines a screen that is filled by the *ExoPlayer* concerning the current orientation. It is done by a proper scaling applied to the *PlayerView*.

`ExoPlayer` used by a `MainViewModel` creates *MediaItems* from uris provided by `ContentManager`. All those uris come from https://www.filmweb.pl/.

# Version 1

### Introduction
This app was created for the Mobile Applications course. Main features that were required to be implemented:
- the app must be based on the Jetpack Compose,
- on the main screen should be presented a list of at least 3 movies (cards with some image presenting the movie + title),
- on the other screen should be presented the details about every movie,
- details screen should be opened after the user clicks on the selected movie card from the list,
- details should include a main image, some kind of description about the movie, a grid (3x3 or 4x4) of the images from the movie, and a list of actors/actresses.

## Demo
Here are some screenshots presenting the basic usage of the app.  

<p float='left'>
  <img src='screenshots/Screenshot_1.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_2.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_3.jpg' width='216' height='444'>
</p>
<p float='left'>
  <img src='screenshots/Screenshot_4.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_5.jpg' width='216' height='444'>
  <img src='screenshots/Screenshot_6.jpg' width='216' height='444'>
</p>

## Implementation details
This application uses Jetpack Compose features and is based on the MVVM architectural pattern.

`ContentManager` is a simulated API providing convenient access to data from the database. In this app a real database is not used - all the needed resources are stored in the `res` directory (`drawable` folder or `strings.xml`).
  
`MainActivity` has a reference to the `MainViewModel` which is responsible for:
- communicating with a dummy database via simulated `ContentManager` API,
- providing processed data for the screens to be displayed.

`MainActivity`:
- defines routes for the navController to enable screen switching
- passes data to be displayed on each screen by calling a proper function from the `MainViewModel`.

`MainScreen` defines the layout of the app's main screen. It uses a *Scaffold* and a *LazyColumn* [Jetpack componets] to achieve the functionality of the movies list with cards.

`DetailsScreen` defines the layout to present details about a movie. It uses:
- *Scaffold* with *TopAppBar* to create a top bar with an arrow allowing to come back to the main screen,
- *Column* and *verticalScroll(rememberScrollState())* to enable scrolling the screen when needed. Because of that - LazyGrid could not be used (it cannot be inside a component with enabled *verticalScroll*), so a custom *NonLazyGrid* was implemented to behave like a default one,
- *remember* function to control the *mutableStatesOf* which defines whether a specific dialog should be displayed.

`MovieDetails` is a data class used to conveniently store all the data about each movie.

`rememberWindowInfo()` is a function used to get information about the current device's screen configuration.
