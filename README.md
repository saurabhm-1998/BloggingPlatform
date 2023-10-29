# Blogging platform Api
This is a blogging platform that allows users to create, read, update, and delete posts, and users can comment on a post and search for posts, and follow other users.
Users can create multiple posts and can comment on multiple posts.

## Requirments
* IntelliJIDEA
* Server port: 8080 
* Java version: 17
* Database: MySql Database


## Tech Stack used - 
* Spring Boot 
* Java

## Features
* Create posts: Users can create new blog posts with content.
* Read posts: Users can view all blog posts and read the content.
* Update posts: Users can edit and update their own blog posts.
* Delete posts: Users can delete their own blog posts.
* Comments: Users can leave comments on blog posts.
* Search: Users can search for blog posts.
* Follow: Users can follow other users and see their posts in a feed.

## Getting Started -
* Clone the repository and open with IDE.
* Check also here - http://3.27.92.230:8080/swagger-ui/index.html#/

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


