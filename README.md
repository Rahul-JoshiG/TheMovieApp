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



![image](https://github.com/user-attachments/assets/d428819a-b632-48fb-ac4b-cc2f8f8fcf27)                                  ![image](https://github.com/user- attachments/assets/05d1b5ba-c712-48c7-aae2-81660497cedc)





![image](https://github.com/user-attachments/assets/28b641cc-d13f-42cf-99ff-97d1b965cd52)                                  ![image](https://github.com/user-attachments/assets/ece16adc-1b45-4e11-aeaf-63c47659e49d)




