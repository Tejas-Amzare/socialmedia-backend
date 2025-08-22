📌 2. Social Media Backend – README  


# 📱 Social Media Backend

## 📖 Overview
A RESTful backend for a **social media application** where users can **register, log in, create posts, like, and comment**.  
Built with **Spring Boot**, **JPA**, and **JWT authentication**.  

## 🛠 Tech Stack
- **Language:** Java 17  
- **Framework:** Spring Boot 3  
- **Database:** MySQL  
- **Authentication:** JWT (Spring Security)  
- **Documentation:** Swagger / Postman  

## ✨ Features
- 🔐 User Registration & Login with JWT  
- 📝 CRUD operations for posts  
- 👍 Like & 💬 Comment on posts  
- 👤 User profile management  

## 📂 Project Structure
socialmedia-backend/
┣ src/main/java/com/socialmedia/
┃ ┣ config/ # JWT Security config
┃ ┣ controller/ # API endpoints
┃ ┣ entity/ # Models (User, Post, Comment)
┃ ┣ repository/ # Database Repositories
┃ ┣ service/ # Business logic
┃ ┗ SocialMediaApplication.java
┣ src/main/resources/
┃ ┣ application.properties
┣ pom.xml
┗ README.md

##  API Endpoints

###  Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/users` - Get all users
- `PUT /api/auth/update/{id}` - Update user
- `DELETE /api/auth/deleted/{id}` - Delete user

###  Post
- `POST /api/posts/create` - Create post
- `GET /api/posts` - Get all posts
- `GET /api/posts/user/{userId}` - Get posts by user ID
- `PUT /api/posts/{id}` - Update post
- `DELETE /api/posts/{id}` - Delete post
- `POST /api/posts/{postId}/like?userId={userId}` - Like post
- `GET /api/posts/{postId}/likes/count` - Get like count of a post

###  Comment
- `POST /api/comments/create` - Add comment to post
- `GET /api/comments/post/{postId}` - Get comments of a post
- `PUT /api/comments/{id}` - Update comment
- `DELETE /api/comments/{id}` - Delete comment

## 🛠 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Tejas-Amzare/socialmedia-backend.git

2.Configure application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/socialmedia
spring.datasource.username=root
spring.datasource.password=yourpassword
jwt.secret=yourSecretKey

3.Run with Maven:
mvn spring-boot:run

4.📸 Screenshots

Swagger UI :-
