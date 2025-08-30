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

Swagger UI :- ( Postman API Tested )
<img width="1317" height="860" alt="1" src="https://github.com/user-attachments/assets/cf886842-fa2c-46d7-8aa8-9692dbffbe25" />
<img width="1270" height="255" alt="2" src="https://github.com/user-attachments/assets/4dff7916-c2ff-4fc0-aac8-870b7e1a274f" />
<img width="1283" height="310" alt="3" src="https://github.com/user-attachments/assets/5b98b7ba-c0b6-4b6a-b98b-dabc242b178e" />
<img width="1299" height="611" alt="4" src="https://github.com/user-attachments/assets/5c99a56c-00e8-4883-8247-250071009d25" />
<img width="908" height="783" alt="5" src="https://github.com/user-attachments/assets/9280964c-4670-4118-8f79-fa4a1ccc4d7c" />
<img width="802" height="818" alt="Screenshot 2025-08-18 000347" src="https://github.com/user-attachments/assets/a484f625-f98d-4920-9d93-151e09b2c459" />




🙋‍♂️ Author
Tejas Gajanan Amzare
📧 tejasamzare@gmail.com
🔗 LinkedIn -https://www.linkedin.com/in/tejas-amzare/



