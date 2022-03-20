
<p align="center">
<img src="https://user-images.githubusercontent.com/41392379/159155933-73a896e8-757d-4a32-95ff-600626b18b57.png" width=80%>
 </p>


## Project Matcher

Project Matcher is an app which helps developers find projects to contribute to.
The app contains categories of interest and fetches live Github data of popular repositories
that would like help!



## Features

- 10 Topics of Interest
- Live Github Data from Github's GraphQL API
- GitHub OAuth Authorization Flow



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




## MVP Architecture

Project Matcher utilizes the MVP Architecture pattern.
- The model draws from two external sources: The Github API and the Apollo generated models. The model also contains custom classes which convert the Apollo GraphQL classes into parcelables, which our fragments can then use to pass data between one another.
- The presenter fetches the repository data from the repository. Then, it converts that data 
- The view defines how the repository data should look on the user's screen.
- The MainContract class is an interface that defines the relationship between the model and presenter.
- The base classes (BaseView and BasePresenter) are also interfaces which define each component's expected behavior. Interfaces help clearly define and decouple different parts of the app.



 <p>
<img src="https://user-images.githubusercontent.com/41392379/159156765-5e903cec-4743-4884-ae09-17b8a7396dee.png" height="400">
 </p>


## Design Decisions

I decided to implement the BaseView interface in the RepoList Fragment, rather than in the MainActivity. This allowed me to more easily send data between fragments within the MainActivity. Because the RepoList fragment was receiving the data, it could also pass it to the RepoDetails fragment via a Bundle. This also removed the need for a database.

