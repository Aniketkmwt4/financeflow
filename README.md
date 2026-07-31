# 💰 FinanceFlow

FinanceFlow is a secure REST API for personal finance management built with Spring Boot. It allows users to register, log in using JWT authentication, and securely access protected resources with role-based authorization.

## 🚀 Features

- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization (USER / ADMIN)
- BCrypt Password Encryption
- Global Exception Handling
- Request Validation
- RESTful API Architecture

## 🛠️ Tech Stack

- Java 25
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Maven
- Lombok

## 📁 Project Structure

```
src
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
└── util
```

## 🔐 Authentication

The project uses JWT (JSON Web Token) for authentication.

### Public Endpoints

- `POST /api/users/register`
- `POST /api/auth/login`

### Protected Endpoints

- `GET /api/test` → Accessible by USER
- `GET /api/admin/test` → Accessible by ADMIN

Include the JWT token in the request header:

```
Authorization: Bearer <your-jwt-token>
```

## ⚙️ Getting Started

### Clone the repository

```bash
git clone https://github.com/Aniketkmwt4/financeflow.git
```

### Navigate to the project

```bash
cd financeflow
```

### Configure the database

Update the following properties in `application.properties`:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
```

### Run the project

```bash
./mvnw spring-boot:run
```

or run the `FinanceflowApplication` class from IntelliJ IDEA.

## 📌 Current Status

✅ JWT Authentication

✅ Role-Based Authorization

✅ User Registration & Login

🔄 Income Module (Coming Next)

## 📈 Future Roadmap

- Income Management
- Expense Management
- Categories
- Dashboard & Analytics
- Monthly Reports
- Pagination & Filtering
- API Documentation (Swagger/OpenAPI)

## 👨‍💻 Author

**Aniket **

GitHub: https://github.com/Aniketkmwt4