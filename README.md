# 🎉 EventHub

EventHub is a backend REST API built with **Spring Boot** for managing events, venues, and bookings. The application supports **JWT-based authentication**, role-based authorization, event management by organizers, and ticket booking by users.

> 🚧 This project is currently under active development.

---

## 🚀 Features

### Authentication
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization (USER, ORGANIZER, ADMIN)

### Event Management
- Create Events
- Update Events
- Delete Events
- View Events
- Event Categories
- Event Status Management

### Venue Management
- Create Venue
- Manage Venue Details
- Associate Events with Venues

### Booking System
- Book Tickets
- Cancel Booking
- Booking History
- Automatic Seat Management
- Booking Reference Generation

### Validation & Error Handling
- Bean Validation
- Global Exception Handling
- Custom Exceptions
- Proper HTTP Status Codes

---

## 🛠 Tech Stack

- Java 21+ (or your version)
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- JWT
- Lombok
- Maven

---

## 📂 Project Structure

```
src
│
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── filter
├── repository
├── security
├── service
└── util
```

---

## 🗄 Database Design

### User
- id
- name
- email
- password
- phoneNumber
- role
- createdAt
- enabled

### Venue
- id
- name
- address
- city
- state
- country
- capacity

### Event
- id
- title
- description
- eventDateTime
- price
- capacity
- availableCapacity
- category
- status
- organizer
- venue

### Booking
- id
- bookingReference
- bookingDate
- ticketCount
- totalPrice
- bookingStatus
- user
- event

---

## 🔗 Entity Relationships

```
User (Organizer)
    1 -------- * Event

User
    1 -------- * Booking

Venue
    1 -------- * Event

Event
    1 -------- * Booking
```

---

## 🔐 Authentication

The application uses **JWT (JSON Web Token)** authentication.

Public Endpoints

```
POST /auth/register
POST /auth/login
```

All other endpoints require a valid JWT token.

---

## 📌 Planned Features

- Swagger Documentation
- Pagination & Sorting
- Event Search
- JPQL Queries
- Unit Testing (Mockito)
- Controller Testing (MockMvc)
- Docker Compose
- Logging
- API Documentation
- GitHub Actions (CI/CD)

---

## 📖 Learning Objectives

This project is being built to practice and demonstrate:

- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate Relationships
- REST API Design
- DTO Pattern
- Exception Handling
- Bean Validation
- Repository Pattern
- Service Layer Design
- Unit & Integration Testing

---

## 👨‍💻 Author

**Goureesh N Doddamani**

Built as part of my backend development journey to strengthen my understanding of Spring Boot and develop a production-style REST API.
