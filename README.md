
# Project Matcher

Project Matcher helps developers find projects to contribute to.
The app contains categories of interest and fetches live Github data of popular repositories
that would like help!




## Features

- 10 Topics of Interest
- Live Github Data from Github's GraphQL API
- GitHub OAuth Authorization Flow



## Screenshots

Welcome/Log In Screen
![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

List of Topics
On the "Home Screen"
I opted to add a call to action, "Select a Topic," on the App Bar.
I wanted users to understand where to start rather than just seeing a list of items.
![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

Repositories under a selected Topic
In order to reduce clutter on this page, I decided aginst placing a view button on each repository item. I noticed on poular apps such as Google Maps or Yelp,
users knew to click on the item to view more details.
![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

Repository details once the user clicks on a Repository

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)



## MVP Architecture

Project Matcher utilizes the MVP Architecture pattern.
The presenter fetches the repository data from the repository.
The view contains a method displayRepos() which defines how the repository data should look on the user's screen.
The model draws from two external sources: The Github API and the Apollo generated models. The model also contains custom classes which convert the Apollo GraphQL classes
into parcelables which our fragments can then use to pass from fragment to fragment.


## Design Decisions

Database Design?