# Dynamic Portfolio Backend

This is the backend API for a Dynamic Portfolio Platform. It is built using Spring Boot and MySQL. The backend provides APIs for managing projects, activities, feedback moderation, contact messages, and secured admin authentication using JWT.

## Features

* Admin login with JWT authentication
* BCrypt password encryption
* Project CRUD APIs
* Activity CRUD APIs
* Featured projects and featured activities
* Project-specific feedback submission
* Activity-specific feedback submission
* Admin feedback moderation
* Contact form message storage
* Admin contact message management
* Protected admin APIs using Spring Security and JWT

## Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* JWT
* Lombok
* Maven

## Main Modules

### Authentication

The backend supports admin login. After successful login, a JWT token is generated and returned to the frontend.

### Projects

Admin can add, update, delete, and manage projects. Public users can view projects.

### Activities

Admin can add, update, delete, and manage activities. Public users can view activities.

### Feedback

Users can submit feedback for specific projects and activities. Feedback is not shown publicly until approved by the admin.

### Contact Messages

Users can send contact messages through the portfolio contact form. Admin can view, mark as read, mark as replied, or delete messages.

## Security

The backend uses Spring Security with JWT.

Public APIs are open for portfolio visitors, such as viewing projects, activities, approved feedback, and submitting contact messages.

Admin APIs are protected and require a valid JWT token.

Protected actions include:

* Add project
* Update project
* Delete project
* Add activity
* Update activity
* Delete activity
* Approve feedback
* Reject feedback
* Delete feedback
* View contact messages
* Mark messages as read
* Mark messages as replied
* Delete contact messages

## API Overview

### Auth APIs

```txt
POST /auth/login
```

### Project APIs

```txt
GET /projects
GET /projects/{id}
GET /projects/featured
POST /projects
PUT /projects/{id}
DELETE /projects/{id}
```

### Activity APIs

```txt
GET /activities
GET /activities/{id}
GET /activities/featured
POST /activities
PUT /activities/{id}
DELETE /activities/{id}
```

### Feedback APIs

```txt
POST /projects/{projectId}/feedback
GET /projects/{projectId}/feedback/approved

POST /activities/{activityId}/feedback
GET /activities/{activityId}/feedback/approved

GET /admin/feedback
PUT /admin/feedback/{id}/approve
PUT /admin/feedback/{id}/reject
DELETE /admin/feedback/{id}
```

### Contact APIs

```txt
POST /contact
GET /admin/messages
PUT /admin/messages/{id}/read
PUT /admin/messages/{id}/replied
DELETE /admin/messages/{id}
```

## How to Run

### 1. Clone the repository

```bash
git clone <backend-repository-url>
cd <backend-folder>
```

### 2. Configure MySQL

Create a MySQL database:

```sql
CREATE DATABASE portfolio_db;
```

Update the database configuration in `application.properties`.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the backend

```bash
mvn spring-boot:run
```

The backend will run on:

```txt
http://localhost:8080
```

## Default Admin Login

```txt
Username: admin
Password: admin123
```

The password is stored in encrypted form using BCrypt.

## Frontend Connection

The frontend connects to this backend using Axios.

Frontend development URL:

```txt
http://localhost:5173
```

Backend API URL:

```txt
http://localhost:8080
```

## Future Improvements

* Move JWT secret to environment variables
* Add token expiration handling in frontend
* Add image upload for projects and activities
* Add deployment configuration
* Add role-based admin management
