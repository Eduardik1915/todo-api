# 📝 Todo API

A simple RESTful Todo API built with **Spring Boot 3.4.4** and **PostgreSQL**.

This project is ideal for understanding the structure of a clean, modular Spring Boot application, and can be used as part of a developer's portfolio.

---

## 🚀 Features

- ✅ Create, Read, Update, Delete (CRUD) operations for todos
- 📦 DTO and Mapper layer (MapStruct-ready)
- 🧪 Unit & Integration tests using JUnit 5, Mockito
- 📄 OpenAPI / Swagger documentation (`/swagger-ui/index.html`)
- 📁 Environment variable support via `dotenv-java`
- 🐘 PostgreSQL integration via Spring Data JPA
- 💡 Clear separation of concerns: Controller, Service, Repository
- 📚 Easily extendable for more features

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.4.4
- PostgreSQL
- JPA/Hibernate
- dotenv-java
- JUnit 5 + Mockito
- Springdoc OpenAPI (Swagger UI)
- Lombok
- MapStruct

---

## 📂 Project Structure

```bash
todo-api/
├── controller/         # REST controllers
├── dto/                # Data Transfer Objects
├── entity/             # JPA entities
├── mapper/             # Mapper interfaces
├── repository/         # Spring Data repositories
├── service/            # Business logic
├── exception/          # Custom exception handlers
├── test/               # Unit and integration tests
└── application.yaml    # Main Spring Boot config
```

---

## 🛠️ Running Locally

Make sure you have PostgreSQL running.

1. Clone the repository:
```bash
git clone https://github.com/your-username/todo-api.git
cd todo-api
```

2. Create `.env` file:
```env
DB_URL=jdbc:postgresql://localhost:5431/todos
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Visit Swagger UI:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🧪 Tests

The project contains both **unit** and **integration** tests:

- Controller tests with `@WebMvcTest`
- Service layer tests with `@ExtendWith(MockitoExtension.class)`
- More test coverage coming soon!

---

## 📌 Roadmap

- [ ] Add pagination and sorting
- [ ] Add filtering (e.g., by completed status)
- [ ] Add entity user
- 🔐 Implement authentication
- [ ] Dockerize the app

---

## 🧑‍💻 Author

Made by Eduards – [@your_github](https://github.com/your_github)  
_You can replace this with your actual name and link._

---

## 📃 License

This project is open-source and available under the MIT License.
