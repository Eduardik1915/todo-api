# ✅ ToDo API — Улучшения и доработки

Этот документ содержит список улучшений, собранных после анализа проекта.
Рекомендуется пройтись по пунктам поочерёдно для улучшения качества и архитектуры проекта.

---

## 📌 Контроллер (`TodoController`)

- [ ] Возвращать DTO вместо сущностей (`TodoResponseDto`)
- [ ] Перенести создание `Todo` из DTO в сервис (`createTodo(dto)`)
- [ ] Для `POST` использовать статус 201 Created

---

## 🧠 Сервис (`TodoService`)

- [ ] Методы должны принимать DTO, а не entity
- [ ] Добавить логгирование в `create`, `update`, `delete` (SLF4J)

---

## 🧱 Сущность (`Todo`)

- [ ] Добавить валидацию: `@NotBlank`, `@Size` для `title` и `description`
- [ ] Использовать `@PrePersist` для установки `createdAt`

---

## 📦 DTO

- [ ] Удалить `createdAt` из `TodoDto`
- [ ] Добавить `@Builder` (опционально)

---

## 🧯 Обработчик ошибок (`ExceptionController`)

- [ ] Добавить fallback для `Exception.class`, `IllegalArgumentException`, `HttpMessageNotReadableException`
- [ ] Добавить универсальный ответ `ErrorResponse` (с `status`, `message`, `errors`)

---

## 📝 README.md

- [ ] Добавить скриншот Swagger UI
- [ ] Добавить примеры JSON-запросов/ответов
- [ ] Уточнить конфигурацию Swagger (springdoc-openapi)
- [ ] Добавить раздел "How to Contribute" (опционально)

---

