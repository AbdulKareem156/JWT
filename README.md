# Employee Management System

A Spring Boot REST API for managing employees with JWT authentication and role-based authorization.

## Features

- ✅ User registration and login
- ✅ Password encryption using BCrypt
- ✅ JWT token generation and validation
- ✅ Role-based access control (ADMIN and USER)
- ✅ Refresh token mechanism
- ✅ Global exception handling
- ✅ DTO pattern implementation
- ✅ MongoDB integration
- ✅ Log4j2 logging
- ✅ Standardized API response format

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (running on localhost:27017)

## Setup Instructions

1. **Install MongoDB**
   - Make sure MongoDB is installed and running on `localhost:27017`
   - The application will create a database named `employee_management` automatically

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   The API will be available at `http://localhost:8080`

## API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

#### Refresh Token
```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "your-refresh-token-here"
}
```

### Employee Endpoints

#### Get All Employees (ADMIN & USER)
```http
GET /api/employees
Authorization: Bearer {access_token}
```

#### Get Employee by ID (ADMIN & USER)
```http
GET /api/employees/{id}
Authorization: Bearer {access_token}
```

#### Create Employee (ADMIN only)
```http
POST /api/employees
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "department": "Engineering",
  "position": "Software Engineer",
  "salary": 75000.0
}
```

#### Update Employee (ADMIN only)
```http
PUT /api/employees/{id}
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "department": "Engineering",
  "position": "Senior Software Engineer",
  "salary": 85000.0
}
```

#### Delete Employee (ADMIN only)
```http
DELETE /api/employees/{id}
Authorization: Bearer {access_token}
```

## Role-Based Access Control

- **ADMIN**: Can perform all CRUD operations on employees
- **USER**: Can only view employees (GET operations)

## Response Format

All API responses follow a standardized format:

### Success Response
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2026-02-21T10:30:00"
}
```

### Error Response
```json
{
  "success": false,
  "message": "Error message here",
  "data": null,
  "timestamp": "2026-02-21T10:30:00"
}
```

## Authentication Flow

1. **Register** or **Login** to get access token and refresh token
2. Include the access token in the `Authorization` header: `Bearer {access_token}`
3. When the access token expires, use the refresh token to get a new access token
4. Access tokens expire in 24 hours, refresh tokens expire in 7 days

## Configuration

Edit `src/main/resources/application.properties` to configure:

- MongoDB connection settings
- JWT secret key and expiration times
- Server port

## Logging

Logs are written to:
- Console output
- `logs/employee-management.log` file

Log4j2 configuration is in `src/main/resources/log4j2.xml`

## Project Structure

```
src/main/java/com/employee/
├── config/          # Configuration classes (SecurityConfig)
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── exception/      # Custom exceptions and global handler
├── model/          # Entity models (User, Employee, Role)
├── repository/     # MongoDB repositories
├── security/       # JWT and security components
└── service/        # Business logic services
```

## Security Features

- Passwords are encrypted using BCrypt
- JWT tokens are signed with HMAC SHA-256
- Role-based authorization using Spring Security
- Refresh token mechanism for secure token renewal

## Testing

You can test the API using tools like:
- Postman
- cURL
- Any REST client

### Example cURL Commands

**Register:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"password123"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'
```

**Get Employees:**
```bash
curl -X GET http://localhost:8080/api/employees \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN"
```

## Notes

- Default users are created with `USER` role. To create an ADMIN user, you'll need to manually update the role in MongoDB or add an admin creation endpoint.
- Make sure to change the JWT secret key in production (`jwt.secret` in application.properties)
- MongoDB indexes are created automatically for unique fields (username, email)
