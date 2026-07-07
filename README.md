# Task - Microservices

## Overview

The Task Management System is a microservices-based backend application developed using Spring Boot and Spring Cloud. The project demonstrates how multiple independent services work together using centralized configuration, service discovery, API Gateway routing, and JWT-based authentication.

Each microservice has a single responsibility, making the application modular, scalable, and easy to maintain.

---

# Features

* Microservices Architecture
* Spring Boot 3
* Spring Cloud Config Server
* Netflix Eureka Service Discovery
* Spring Cloud Gateway
* JWT Authentication
* BCrypt Password Encryption
* RESTful APIs
* MySQL Database
* Maven Build Tool
* Stateless Authentication
* Service-to-Service Communication through Gateway

---

# Technologies Used
 
 * Java 17              - Programming Language       
 * Spring Boot          - Microservice Development   
 * Spring Cloud         - Microservice Components    
 * Spring Cloud Gateway - API Gateway                
 * Netflix Eureka       - Service Discovery          
 * Spring Cloud Config  - Centralized Configuration  
 * Spring Security      - Authentication & Security  
 * JWT                  - Token-based Authentication 
 * Spring Data JPA      - Database Access            
 * Hibernate            - ORM Framework              
 * MySQL                - Database                   
*  Maven                - Dependency Management      

---

# Project Structure

```text
Task-Management-System
│
├── config-server
│   ├── Stores configuration files for all microservices
│   └── Runs on Port 8888
│
├── eureka-server
│   ├── Registers all microservices
│   ├── Provides service discovery
│   └── Runs on Port 8761
│
├── api-gateway
│   ├── Routes incoming requests
│   ├── Validates JWT tokens
│   ├── Allows public APIs
│   └── Runs on Port 8001
│
├── authentication-service
│   ├── User Registration
│   ├── User Login
│   ├── Password Encryption
│   ├── JWT Token Generation
│   └── Runs on Port 8003
│
└── task-service
    ├── Task Management APIs
    ├── Protected APIs
    └── Runs on Port 8002
```

---

# Microservices Description

## 1. Config Server

The Config Server centralizes configuration for all microservices. Instead of maintaining separate configuration files inside every service, all configurations are managed from one place.

### Responsibilities

* Centralized configuration
* Environment management
* Simplified maintenance
* Configuration sharing across services

---

## 2. Eureka Server

The Eureka Server acts as the Service Registry.

Whenever a microservice starts, it registers itself with Eureka. Other services can discover it using the service name instead of a fixed IP address or port.

### Responsibilities

* Service Registration
* Service Discovery
* Health Monitoring

---

## 3. API Gateway

The API Gateway is the single entry point for all client requests.

Instead of directly calling individual microservices, clients send every request to the Gateway.

### Responsibilities

* Request Routing
* JWT Validation
* Security
* Load Balancing using Eureka
* Forwarding Requests


## 4. Authentication Service

The Authentication Service is responsible for user authentication.

### Features

* User Registration
* User Login
* Password Encryption using BCrypt
* JWT Token Generation
* User Validation

## 5. Task Service

The Task Service manages task-related operations.

Currently, all task APIs are protected by JWT authentication through the API Gateway.

Example operations include:

* Create Task
* View Tasks
* Update Task
* Delete Task

---

# Authentication Flow

### Step 1

The user registers using:

```http
POST /auth/signup
```

---

### Step 2

The user logs in using:

```http
POST /auth/login
```

---

### Step 3

The Authentication Service verifies the username and password.

If valid:

* Generates a JWT
* Returns the JWT to the client

---

### Step 4

The client sends the JWT with every protected request.

Example:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

### Step 5

The API Gateway:

* Reads the Authorization header
* Extracts the JWT
* Validates the signature
* Checks token expiration
* Forwards the request to the appropriate microservice if the token is valid

---

# Running the Project

Start the services in the following order:

1. Config Server
2. Eureka Server
3. API Gateway
4. Authentication Service
5. Task Service

Once all services are running:

* Register a user
* Login to receive a JWT
* Use the JWT to access protected Task APIs

---

# API Summary

## Authentication

| Method | Endpoint       | Description                        |
| ------ | -------------- | ---------------------------------- |
| POST   | `/auth/signup` | Register a new user                |
| POST   | `/auth/login`  | Authenticate user and generate JWT |


## User

| Method | Endpoint       | Description                        |
| ------ | -------------- | ---------------------------------- |
| GET    | `/user/**`     | Retrieve the user by id            |
| PUT    |`/user/**`      | Update the user by id              |
| DELETE |`/user/**`      | Delete the user by id              |

## Task

| Method | Endpoint    | Description               |
| ------ | ----------- | ------------------------- |
| GET    | `/tasks/**` | Retrieve all tasks        |
| GET    | `/tasks/**` | Retrieve task information |
| POST   | `/tasks/**` | Create a task             |
| PUT    | `/tasks/**` | Update a task             |
| DELETE | `/tasks/**` | Delete a task             |

> **Note:** Task endpoints require a valid JWT in the `Authorization` header.

---

# Configuration

Configuration for all microservices is managed using the Config Server.

Each microservice fetches its configuration during startup, ensuring centralized and consistent configuration management.

---

# Security

The project uses JWT (JSON Web Token) for stateless authentication.

Security features include:

* BCrypt password hashing
* JWT generation after successful login
* JWT validation at the API Gateway
* Stateless authentication
* Protected endpoints accessible only with a valid token

---
# Author

**Manjula G**

Java Full Stack Developer

This project was developed as a learning project to understand enterprise-level microservices architecture using Spring Boot, Spring Cloud, and JWT-based authentication.
