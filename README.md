# Social Media Backend API

This is a backend-only Spring Boot project for a basic social media application. It allows users to register, log in, create posts, like posts, comment on posts, and perform CRUD operations on users, posts, likes, and comments.![Banner](socialmedia-backend-banner.png)


## 🔧 Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman (for testing APIs)

## ✅ Features
- User registration and login (with validation and password encryption)
- Post creation, updating, fetching, and deletion
- Like and unlike functionality (per user, per post)
- Comment system (create, update, delete, fetch by post)
- Count total likes per post
- Proper exception handling and validation
- RESTful API design

## 📦 API Endpoints

### 🔐 Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/users` - Get all users
- `PUT /api/auth/update/{id}` - Update user
- `DELETE /api/auth/deleted/{id}` - Delete user

### 📝 Post
- `POST /api/posts/create` - Create post
- `GET /api/posts` - Get all posts
- `GET /api/posts/user/{userId}` - Get posts by user ID
- `PUT /api/posts/{id}` - Update post
- `DELETE /api/posts/{id}` - Delete post
- `POST /api/posts/{postId}/like?userId={userId}` - Like post
- `GET /api/posts/{postId}/likes/count` - Get like count of a post

### 💬 Comment
- `POST /api/comments/create` - Add comment to post
- `GET /api/comments/post/{postId}` - Get comments of a post
- `PUT /api/comments/{id}` - Update comment
- `DELETE /api/comments/{id}` - Delete comment

## 🛠️ How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Tejas-Amzare/socialmedia-backend.git
