# 📱 Social Media Backend: RESTful Platform for Posts, Likes & Comments

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/Tejas-Amzare/socialmedia-backend)
[![License](https://img.shields.io/badge/license-Apache_2.0-blue)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-blueviolet)](https://spring.io/projects/spring-boot)

---

## 📖 Overview
**Social Media Backend** is a RESTful backend for a social media application where users can:  
- Register & log in securely  
- Create posts  
- Like and comment on posts  
- Manage their profile  

It is built with **Spring Boot 3, JPA, MySQL**, and **JWT-based authentication**, providing a modern backend architecture for social platforms.

---

## 📌 Table of Contents
- [Tech Stack](#-tech-stack)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Architecture](#-architecture)
- [Setup & Installation](#-setup--installation)
- [Usage](#-usage)
- [Visuals](#-visuals)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## 🛠 Tech Stack
- **Language:** Java 17  
- **Framework:** Spring Boot 3  
- **Database:** MySQL  
- **Authentication:** JWT (Spring Security)  
- **Documentation:** Swagger / Postman  

---

## ✨ Features
- 🔐 User Registration & Login with JWT  
- 📝 CRUD operations for posts  
- 👍 Like & 💬 Comment on posts  
- 👤 User profile management  

---

## 📂 Project Structure
socialmedia-backend/
┣ src/main/java/com/socialmedia/
┃ ┣ config/ # JWT Security configuration
┃ ┣ controller/ # API endpoints (Auth, Posts, Comments)
┃ ┣ entity/ # Models (User, Post, Comment)
┃ ┣ repository/ # JPA Repositories
┃ ┣ service/ # Business logic
┃ ┗ SocialMediaApplication.java
┣ src/main/resources/
┃ ┗ application.properties
┣ pom.xml
┗ README.md


---

## 🔑 API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST   | /api/auth/register         | Register new user | ❌ |
| POST   | /api/auth/login            | Login & get JWT | ❌ |
| GET    | /api/auth/users            | List all users | ✅ |
| PUT    | /api/auth/update/{id}      | Update user | ✅ |
| DELETE | /api/auth/deleted/{id}     | Delete user | ✅ |

### Posts
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST   | /api/posts/create            | Create new post | ✅ |
| GET    | /api/posts                   | Get all posts | ✅ |
| GET    | /api/posts/user/{userId}     | Get posts by user ID | ✅ |
| PUT    | /api/posts/{id}              | Update post by ID | ✅ |
| DELETE | /api/posts/{id}              | Delete post by ID | ✅ |
| POST   | /api/posts/{postId}/like?userId={userId} | Like a post | ✅ |
| GET    | /api/posts/{postId}/likes/count        | Get post like count | ✅ |

### Comments
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST   | /api/comments/create         | Add comment to post | ✅ |
| GET    | /api/comments/post/{postId}  | Get comments of a post | ✅ |
| PUT    | /api/comments/{id}           | Update comment | ✅ |
| DELETE | /api/comments/{id}           | Delete comment | ✅ |

---

## 🏗 Architecture Overview
┌──────────────┐
│ Clients │ <-- Web / Postman
└──────┬───────┘
│
┌──────▼────────┐
│ Controllers │ (@RestController)
└──────┬────────┘
│
┌──────▼────────┐
│ Services │ (@Service)
└──────┬────────┘
│
┌──────▼────────┐
│ Repositories │ (@Repository)
└──────┬────────┘
│
┌──────▼────────┐
│ MySQL / DB │
└───────────────┘



---

## ⚙️ Setup & Installation
### Prerequisites
- Java 17  
- Maven 3+  
- MySQL installed and running  

### Steps
1. Clone the repository:

git clone https://github.com/Tejas-Amzare/socialmedia-backend.git
cd socialmedia-backend
Configure application.properties:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/socialmedia
spring.datasource.username=root
spring.datasource.password=yourpassword
jwt.secret=yourSecretKey
Run the application:


mvn spring-boot:run
Access Swagger UI:


http://localhost:8080/swagger-ui/index.html
💻 Usage Examples
Register a User

POST /api/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "Password123"
}
Create a Post (Authorized)

POST /api/posts/create
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "title": "My First Post",
  "content": "Hello, world!"
}
Like a Post

POST /api/posts/1/like?userId=2
Authorization: Bearer <JWT_TOKEN>
Add a Comment

POST /api/comments/create
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "postId": 1,
  "userId": 2,
  "content": "Great post!"
}


📸 Visuals
Swagger UI

<img width="1317" height="860" alt="1" src="https://github.com/user-attachments/assets/7a9e5dd4-5d65-45cc-a38f-acc618f04cc8" />

Sample Postman Requests

<img width="1270" height="255" alt="2" src="https://github.com/user-attachments/assets/d4897f64-0030-48f2-b59d-3f6abf55516f" />
<img width="1283" height="310" alt="3" src="https://github.com/user-attachments/assets/71d447c2-1c6f-4cc5-961f-e63da5d26096" />
<img width="1299" height="611" alt="4" src="https://github.com/user-attachments/assets/bfe26353-f695-42cd-8855-f6485b120ff9" />
<img width="908" height="783" alt="5" src="https://github.com/user-attachments/assets/197d3614-2a06-4a4b-b7f5-13f82fbbd36a" />


🤝 Contributing
Contributions are welcome! Please:

Fork the repository

Create a feature branch (git checkout -b feature-name)

Commit your changes (git commit -m "Add feature")

Push to your branch (git push origin feature-name)

Open a Pull Request

📝 License
This project is licensed under the Apache 2.0 License - see LICENSE file for details.

📫 Contact :-


Tejas Gajanan Amzare


📧 Email: tejasamzare@gmail.com


🔗 LinkedIn: https://www.linkedin.com/in/tejas-amzare

