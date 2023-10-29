# Blogging platform Api
This is a blogging platform that allows users to create, read, update, and delete posts, and users can comment on a post and search for posts, and follow other users.
Users can create multiple posts and can comment on multiple posts.

## Framework and Language Used:

Framework: Spring Boot
Language: Java

## Features
* Create posts: Users can create new blog posts with content.
* Read posts: Users can view all blog posts and read the content.
* Update posts: Users can edit and update their own blog posts.
* Delete posts: Users can delete their own blog posts.
* Comments: Users can leave comments on blog posts.
* Search: Users can search for blog posts.
* Follow: Users can follow other users and see their posts in a feed.

## Data Flow:

### Controller:

Handles incoming HTTP requests related to blog post creation, editing, deletion, user authentication, and other interactions.
Directs the requests to the appropriate Service methods.

### Services:

Contains business logic for managing user authentication, processing blog post data, and handling user interactions.
Orchestrates the flow of data between the Controller and the Repository layer.

### Repository:

Interacts with the MySQL database using Spring Data JPA for data access.
Performs CRUD operations (Create, Read, Update, Delete) on blog posts, user profiles, and other relevant data.

## Database Design:

Utilizes MySQL database for data storage.
Employs appropriate tables and relationships to store and manage blog posts, user information, comments, and other related data.

## Data Structure Used in the Project:

Utilizes various data structures provided by Java and Spring frameworks, such as Lists, Maps, and Sets, for managing and organizing data within the application.

## Endpoints
* User Controller has -
  * POST - /add-user
  * GET - /get-all-users
  * PUT - /update-post/:id
  * DELETE - /delete-post/:id
  * GET - /search-post
  * GET - /read-comment
* Follow Controller has -
  * POST - /follow-user
  * GET - /search-followers
* Post Controller has -
  * POST - /add-post
* Comment Controller has -
  * POST - /add-comment


## Project Summary:
The blogging platform developed using Java and Spring Boot, with MySQL as the database, follows a clear architectural pattern. The Controller layer handles incoming HTTP requests and directs them to the appropriate Service methods, which encapsulate the application's business logic. The Repository layer facilitates communication with the MySQL database, allowing for efficient storage and retrieval of blog posts, user data, and related information. The project utilizes standard data structures provided by the Java and Spring frameworks for efficient data management and processing.



