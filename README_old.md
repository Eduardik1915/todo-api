
# 📝 ToDo API (Spring Boot + PostgreSQL)

A simple REST API for managing tasks (todo-list), built with Spring Boot and PostgreSQL.
Ideal for portfolio projects and web development practice.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger (OpenAPI)
- Lombok

---

## 📦 Features

- 📄 Full CRUD for tasks
- ✍️ Task descriptions and completion toggle
- 🔍 Swagger UI for testing and documentation
- 🐘 PostgreSQL integration
- 🕓 Automatic timestamp generation
---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/todo_api/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── dto/
│   │       ├── entity/
│   │       └── ToDoApiApplication.java ✅
│   └── resources/
│       ├── application.properties ✅
│       └── static/
└── test/
```

---

## ⚙️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/todo-api.git
cd todo-api
```

### 2. Configure PostgreSQL connection

```properties
# src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

---

## 📬 API Endpoints

| Method | Endpoint           | Description         |
|--------|--------------------|---------------------|
| GET    | `/api/todos`       | Get all tasks       |
| GET    | `/api/todos/{id}`  | Get task by ID      |
| POST   | `/api/todos`       | Create a new task   |
| PUT    | `/api/todos/{id}`  | Update a task       |
| DELETE | `/api/todos/{id}`  | Delete a task       |

📍 Swagger UI: http://localhost:8080/swagger-ui.html

🧪 Example Request
json
Copy
Edit
POST /api/todos
{
"title": "Buy groceries",
"description": "Milk, eggs, bread"
}
json
Copy
Edit
PUT /api/todos/1
{
"title": "Buy groceries and fruits",
"completed": true
}
---

## 🧠 Possible Improvements

- 🔐 Authentication (JWT)
- 📦 DTO Layer (Data Transfer Objects)
- 🧪 Unit & Integration Testing
- ⚙️ CI/CD with GitHub Actions
- 🚀 Deploy on Render / Railway

---

## 👨‍💻 Author

> Developed as a portfolio project by **Eduards**

---

## 📜 License

MIT License
