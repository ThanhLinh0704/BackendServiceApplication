# 🧑‍💼 User Management REST API

User Management API built with Spring Boot. 
Supports CRUD operations, input validation, centralized exception handling, and unit testing. 
Authentication with JWT, role-based authorization, password encryption.

---

## 🚀 Features

- ✅ **User CRUD**: Create, read, update, delete user accounts.
- 🔁 **Exception Handling**: Unified and descriptive API responses.
- 🛡️ **Password Management**: Encrypted passwords using BCrypt.
- 🔐 **Authentication & Authorization**:
  - JWT-based login/logout.
  - Role- and permission-based access control.
- 🧪 **Unit Testing**:
  - Unit tests for services and controllers
  - Uses JUnit 5 and Mockito
  
## 🧑‍💻 Tech Stack

| Layer         | Technology                              |
|---------------|------------------------------------------|
| **Language**  | Java 17                                  |
| **Backend**   | Spring Boot, Spring Web, Spring Data JPA |
| **Security**  | Spring Security, BCrypt                  |
| **Testing**   | JUnit 5, Mockito                         |
| **Database**  | MySQL                                    |
| **Others**    | Postman                          |

## 🏗️ Getting Started
⚙️ Configure the database
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

🔨 Build và chạy ứng dụng
./mvnw spring-boot:run

📘 API Documentation with Swagger
http://localhost:8080/swagger-ui/index.html
