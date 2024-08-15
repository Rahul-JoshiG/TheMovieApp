TheMovieApp

TheMovieApp is an Android application that fetches and displays popular movies using the TMDb API. The app provides a user-friendly interface with various movie categories and animations for an enhanced user experience.

Features

Movie List: Displays a list of popular movies.
Category Selector: Allows users to choose different movie categories (e.g., popular, upcoming, top-rated, now-playing).
Swipe-to-Refresh: Users can refresh the movie list by swiping down.
Animations: Smooth animations for transitions and data updates.

Requirements
Android Studio: 4.0 or higher
Android SDK: API 26 or higher
Kotlin: 1.4 or higher
Installation

Spinner: Use the spinner at the top of the screen to select different movie categories.
Swipe-to-Refresh: Swipe down on the list to refresh the movie data.

Code Structure

  MainActivity: 
      Handles the main UI and interactions, including movie fetching and UI updates.
  MainActivityViewModel:
      Manages data for the MainActivity and communicates with the repository.
  MovieRepository:
      Responsible for fetching movie data from the TMDb API.
  MovieAdapter: 
      Adapts the movie data for display in the RecyclerView.
  activity_main.xml: 
      Layout file for the main activity.

  Dependencies
    Retrofit: 
        For making network requests.
    Picasso:
        For image loading.
    Data Binding: 
        For binding UI components to data sources.
    SwipeRefreshLayout: 
        For implementing swipe-to-refresh functionality.
    RecyclerView: 
        For displaying lists of movies.


Screenshort:

![image](https://github.com/user-attachments/assets/ff429b1f-b404-4e9e-b036-9e85849d98b8)                         ![image](https://github.com/user-attachments/assets/ac93f6ca-148b-463c-bba0-0671735b278a)


![image](https://github.com/user-attachments/assets/eff1fcaa-fc39-46f7-8dc1-e2cf9caf7aa4)                         ![image](https://github.com/user-attachments/assets/3e2a5d65-d713-4dc9-9213-3c8abd0af62d)




