# TheMovieApp

TheMovieApp is an Android application that fetches and displays popular movies using the TMDb API. The app provides a user-friendly interface with various movie categories and animations for an enhanced user experience.

## Features

Movie List: Displays a list of popular movies.
Category Selector: Allows users to choose different movie categories (e.g., popular, upcoming, top-rated, now-playing).
Swipe-to-Refresh: Users can refresh the movie list by swiping down.
Animations: Smooth animations for transitions and data updates.

## Requirements
Android Studio: 4.0 or higher
Android SDK: API 26 or higher
Kotlin: 1.4 or higher
Installation

Spinner: Use the spinner at the top of the screen to select different movie categories.
Swipe-to-Refresh: Swipe down on the list to refresh the movie data.

## Code Structure

  1. MainActivity: 
    Handles the main UI and interactions, including movie fetching and UI updates.
  2. MainActivityViewModel:
      Manages data for the MainActivity and communicates with the repository.
  3. MovieRepository:
      Responsible for fetching movie data from the TMDb API.
  4. MovieAdapter: 
      Adapts the movie data for display in the RecyclerView.
  5. activity_main.xml: 
      Layout file for the main activity.
  6. Dependencies
    > Retrofit: 
        For making network requests.
    > Picasso:
        For image loading.
    > Data Binding: 
        For binding UI components to data sources.
    > SwipeRefreshLayout: 
        For implementing swipe-to-refresh functionality.
    > RecyclerView: 
        For displaying lists of movies.

## Screenshort:

![image](https://github.com/user-attachments/assets/d428819a-b632-48fb-ac4b-cc2f8f8fcf27)           ![image](https://github.com/user-attachments/assets/d3998e88-5c52-4aea-9684-70f86b9c9f38)





![image](https://github.com/user-attachments/assets/28b641cc-d13f-42cf-99ff-97d1b965cd52)               ![image](https://github.com/user-attachments/assets/ece16adc-1b45-4e11-aeaf-63c47659e49d)






## Video:


https://github.com/user-attachments/assets/ab7265bf-317c-4471-81df-4a3831e24fd9




