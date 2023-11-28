# Movies app

## Introduction
This app was created for the Mobile Applications course. Main features that were required to be implemented:
- app must be based on the Jetpack Compose,
- on the main screen should be presented a list of at least 3 movies (cards with some image presenting the movie + title),
- on the other screen should be presented the details about every movie,
- details screen should be opened after user clicks on the selected movie card from the list,
- details should include: main image, some kind of a description about the movie, grid (3x3 or 4x4) of the images from the movie and a list of actors/actresses.

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
- passes data to be displayed on each screen by calling proper function from the `MainViewModel`.

`MainScreen` defines the layout of the app's main screen. It uses a *Scaffold* and a *LazyColumn* [Jetpack componets] to achieve the functionality of the movies list with cards.

`DetailsScreen` defines the layout to present details about a movie. It uses:
- *Scaffold* with *TopAppBar* to create a top bar with an arrow allowing to come back to the main screen,
- *Column* and *verticalScroll(rememberScrollState())* to enable scrolling the screen when needed. Because of that - LazyGrid could not be used (it cannot be inside a component with enabled *verticalScroll*), so a custom *NonLazyGrid* was implemented to behave like a default one,
- *remember* function to control the *mutableStatesOf* which defines whether a specific dialog should be displayed.

`MovieDetails` is a data class used to conveniently store all the data about each movie.

`rememberWindowInfo()` is a function used to get information about the current device's screen configuration.
