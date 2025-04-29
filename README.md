# **Ecommerce Project**

---

## **Project Description**

This project contains the backend mechanics of an online store, developed as a REST web service using Spring Boot.  
It provides CRUD operations for users, product groups, products, carts, and orders.

---

## **Demo**

The application is under development and not publicly deployed.  
You can test it locally using **Postman**.

---

## **Table of Contents**

- [Project Description](#project-description)
- [Demo](#demo)
- [Technologies & Dependencies](#technologies--dependencies)
- [Installation](#installation)
- [Running the Project](#running-the-project)
- [Testing](#testing)
- [Configuration - DB credentials](#configuration---db-credentials)
- [API Endpoints](#api-endpoints)
- [Project Management Tools](project-management-tools)
- [Contributors](#contributors)
- [Usage & Future Development](#usage--future-development)
- [Troubleshooting](#troubleshooting)
- [Notes](#notes)

---

## **Technologies & Dependencies**

- Java 17+
- Spring Boot 3.2.2
- Spring Data JPA
- Lombok
- MySQL Connector
- Gradle 7.x
- JUnit 5
- H2 Database
- MySQL Database
- Postman
- Git

---

## **Installation**

1. Clone the repository:
   ```bash
   git clone https://github.com/piotr-grosicki/project-jdp-2503-01.git
   ```
2. Build the project:
   ```bash
   ./gradlew build
   ```

---

## **Running the Project**

Start the application locally:
```bash
./gradlew bootRun
```
The API will be accessible at `http://localhost:8080`.

---

## **Testing**

The project uses **JUnit 5**. Run tests with:
```bash
./gradlew test
```

---

## **Configuration - DB credentials**

Edit `src/main/resources/application.properties` for database settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kodilla_project?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=kodilla_user
spring.datasource.password=kodilla_password

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Ensure the database `kodilla_project` exists in MySQL.

---

## **API Endpoints**

### **Users**

| Method | Endpoint          | Description            |
|--------|-------------------|------------------------|
| GET    | `/v1/users`       | Return all users       |
| GET    | `/v1/users/{id}`  | Return user by ID      |
| POST   | `/v1/users`       | Create new user        |
| PUT    | `/v1/users/{id}`  | Update existing user   |
| DELETE | `/v1/users/{id}`  | Delete user by ID      |

**Example request body for creating user:**
```json
{
  "username": "alice_smith",
  "password": "P@ssw0rd!",
  "email": "alice@example.com",
  "isBlocked": false
}
```

---

### **Product Groups**

| Method | Endpoint          | Description                |
|--------|-------------------|----------------------------|
| GET    | `/v1/groups`      | Return all product groups  |
| GET    | `/v1/groups/{id}` | Return group by ID         |
| POST   | `/v1/groups`      | Create new group           |
| PUT    | `/v1/groups/{id}` | Update existing group      |
| DELETE | `/v1/groups/{id}` | Delete group by ID         |

**Example request body for creating group:**
```json
{
  "name": "Accessories",
  "description": "Phone and computer accessories"
}
```

---

### **Products**

| Method | Endpoint             | Description            |
|--------|----------------------|------------------------|
| GET    | `/v1/products`       | Return all products    |
| GET    | `/v1/products/{id}`  | Return product by ID   |
| POST   | `/v1/products`       | Create new product     |
| PUT    | `/v1/products/{id}`  | Update existing product|
| DELETE | `/v1/products/{id}`  | Delete product by ID   |

**Example request body for creating product:**
```json
{
  "name": "Wireless Mouse",
  "price": 29.99,
  "description": "Gaming wireless mouse",
  "groupId": 2
}
```

---

### **Carts**

| Method | Endpoint            | Description          |
|--------|---------------------|----------------------|
| GET    | `/v1/carts`         | Return all carts     |
| GET    | `/v1/carts/{id}`    | Return cart by ID    |
| POST   | `/v1/carts`         | Create new cart      |
| PUT    | `/v1/carts/{id}`    | Update existing cart |
| DELETE | `/v1/carts/{id}`    | Delete cart by ID    |

**Example request body for creating cart:**
```json
{
  "userId": 1,
  "productIds": [3, 5, 7]
}
```

---

### **Orders**

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| GET    | `/v1/orders`         | Return all orders    |
| GET    | `/v1/orders/{id}`    | Return order by ID   |
| POST   | `/v1/orders`         | Create new order     |
| PUT    | `/v1/orders/{id}`    | Update existing order|
| DELETE | `/v1/orders/{id}`    | Delete order by ID   |

**Example request body for creating order:**
```json
{
  "orderDate": "2025-04-28",
  "status": "NEW",
  "userId": 1,
  "cartId": 4
}
```

---

## **Project Management Tools**

The team used:

- Jira – for issue tracking and sprint planning.
- Kanban board – for task visualization and progress tracking.

---

## **Contributors**

- Piotr Grosicki – [GitHub](https://github.com/piotr-grosicki)
- Robert Litwiński – [GitHub](https://github.com/robdev97)
- Robert Jakubowski – [GitHub](https://github.com/Robercikj)
- Piotr Więdłocha – [GitHub](https://github.com/Widlakolak)

---

## **Usage & Future Development**

- Implement product searching and filtering.  
- Add payment integration (e.g., PayU, Stripe).  
- Introduce authentication and authorization (JWT).  
- Real-time cart updates.  
- Swagger/OpenAPI documentation.

---

## **Troubleshooting**

- Ensure MySQL Server is running and database `kodilla_project` exists.  
- Verify `application.properties` credentials.  
- For build issues, run:  
  ```bash
  ./gradlew clean build
  ```
- If port 8080 is in use, stop the conflicting process or change server port.

---

# **Notes**

This README will evolve as the project matures.
