# 📋 ToDo API

A simple RESTful API for managing tasks — built with Spring Boot, PostgreSQL, and JPA. Serves as a basic CRUD application template.

---

## 🚀 Features

- 📌 Create, read, update, and delete tasks (CRUD)
- 🔗 REST API with a standard structure
- 🗃️ PostgreSQL integration via Spring Data JPA
- 📁 Ready for local deployment
- 📖 Swagger UI available for exploring the API

---

## 🛠️ Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven
- MapStruct
- OpenAPI (Swagger)
- JUnit 5 + Mockito
- Lombok

---

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/com/example/todo_api/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── ToDoApiApplication.java
│   └── resources/
│       ├── application.properties
└── test/
    ├── java/com/example/todo_api/
        └── controller/
```

---

## 📦 Setup and Run

### 1. Clone the repository

```bash
git clone https://github.com/Eduardik1915/todo-api.git
cd todo-api
```

### 2. Configure the database

Create a PostgreSQL database (locally or via cloud) and update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The app will be available at `http://localhost:8080`

To explore the API via Swagger UI, go to: `http://localhost:8080/swagger-ui.html`

---

## 📌 API Examples

### 🔹 Get all tasks

```http
GET /api/todos
```

### 🔹 Create a task

```http
POST /api/todos
Content-Type: application/json

{
  "title": "Write README",
  "completed": false
}
```

### 🔹 Update a task

```http
PUT /api/todos/{id}
Content-Type: application/json

{
  "title": "README completed!",
  "completed": true
}
```

### 🔹 Delete a task

```http
DELETE /api/todos/{id}
```

---

## 🧪 Tests

The project contains both **unit** and **integration** tests:

- Controller tests using `@WebMvcTest`
- Service layer tests using `@ExtendWith(MockitoExtension.class)`
- More test coverage coming soon!

To run tests, use the following command:

```bash
./mvnw test
```

---

## 🌍 Deployment

Cloud hosting like Railway or any other hosting can be connected later. Basic config is ready.

---

## 📌 Roadmap

- [ ] Add pagination and sorting
- [ ] Add filtering (e.g., by completed status)
- [ ] Add entity user
- [ ] Add logging
- [ ] Implement authentication
- [ ] Dockerize the app

---

## 👨‍💻 Author

- Eduardik1915  
- [GitHub](https://github.com/Eduardik1915/todo-api)

---

## 📝 License

This project is open source under the MIT license.