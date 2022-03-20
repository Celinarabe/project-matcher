
<p align="center">
<img src="https://user-images.githubusercontent.com/41392379/159157587-e83ee173-96e8-4535-9c46-c56b5cfa52fc.png" width=70%>
 </p>


## Project Matcher

Project Matcher is an app which helps developers find projects to contribute to.
The app contains categories of interest and fetches live Github data of popular repositories
that would like help!


## Features

- 10 Topics of Interest
- Live Github Data from Github's GraphQL API
- GitHub OAuth Authorization Flow via Firebase Auth
- Animated Transitions
- Android Jetpack Navigation

 ## Architectural Requirements
 - 2 Activities:
   - The MainActivity is responsible for the main flow of the application. It houses the Navigation Controller which allows users to navigate across different topics and repositories within the app.
   - The AuthActivity is responsible for the Github Authentication flow before the user is able to use the app.
 - 1 Fragment: 
   - The RepoListFragment displays the results from the GitHub API
 - Project Matcher utilizes the MVP architectural pattern to fetch GitHub data and display the results to the user.
 - The GitHub GraphQL API is used to fetch the repository data.
 - 5 Material Design UI Components
    1. The top app bar is visible throughout the main flow of the application
    2. A button is used in the AuthActivity to allow the user to initiate through the authorization flow.
    3. Cards are used to display the lists of topics and repositories.
    4. An overflow menu is used to display the log out functionality.
    5. A progress indicator is used to provide user feedback while the app is fetching data from the GitHub endpoint.
- SharedPreferences is used to store the user's GitHub Auth token with private context mode.

## MVP Architecture

Project Matcher utilizes the MVP Architecture pattern.
- The model draws from two external sources: The Github API and the Apollo generated models. The model also contains custom classes which convert the Apollo GraphQL classes into parcelables, which our fragments can then use to pass data between each other.
- The presenter fetches the repository data from the repository. 
- The view defines how the repository data should look on the user's screen.
- The MainContract class is an interface that defines the relationship between the model and presenter.
- The base classes (BaseView and BasePresenter) are also interfaces which define each component's expected behavior. These interfaces help clearly define and decouple different parts of the app.


 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156765-5e903cec-4743-4884-ae09-17b8a7396dee.png" wisth="100%">
 </p>
 


## Additional Design Decisions

### Fragment MainView Implementation
I decided to implement the BaseView interface in the RepoList Fragment, rather than in the MainActivity. This allowed me to more easily share data between fragments within the app. Because the RepoList fragment is receiving the data, it is able to share this data to the RepoDetails fragment via a Bundle. This removed the need for a database.

### FirebaseAuthProvider
I decided to create a parent activity which both activities inherit from. This activity is responsible for initializing the Firebase auth provider, defining the requested scopes, and creating intents to start the appropriate activity based on login status. Both activities needed access to this functionality which led to the decision to create FirebaseAuthProvider.


## Screenshots

### Welcome/Log In Screen  
 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156071-54aa1635-100e-4be6-a867-1556564c3169.jpg" height="400">
 </p>


### List of Topics  
I opted to add the call to action, "Select a Topic," on the App Bar. I wanted users to understand where to start rather than just seeing a list of items.
 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156196-984cb865-6b54-41e1-8fb2-afb5ac06ea80.jpg" height="400">
 </p>



### Repositories under a selected Topic
In order to reduce clutter on this page, I decided aginst placing a view button on each repository item. I noticed on poular apps such as Google Maps or Yelp,
users knew to click on the item to view more details.


 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156277-fd183d82-5540-4c4a-bc41-d066ffc360e0.jpg" height="400">
 </p>


### Repository Details 
Once the user clicks on a Repository, they are taken to a Repository Details page where they can see more details about the issues.

 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156323-8168b9f7-925b-4212-9769-3f02832a835d.jpg" height="400">
 </p>





