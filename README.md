# Placement-preparation-tracker
Track interview prep progress — built with Java &amp; Spring Boot


A full-stack app for tracking placement/interview prep — log topics across DSA, OOPs, OS, CN, DBMS, and Java, track hours spent, set a study-hour goal, and watch your progress in a live dashboard.

Built with **Spring Boot** (Java) on the backend and a lightweight **vanilla HTML/CSS/JS** dashboard on the frontend, secured with **JWT authentication**.

---

## ✨ Features

- 🔐 **Auth** — sign up / log in with JWT-based stateless authentication
- 📊 **Dashboard** — total topics, completed/ongoing/pending counts, total & average hours, top time-sink topic, and a live progress ring
- 📝 **Topic CRUD** — add, edit, delete topics with category, status, and hours spent
- 🔍 **Advanced filters** — query by status + min hours, category + hour range, hours-less-than, and category sorted by hours descending
- 🎯 **Study goal tracking** — set a target hour count and track progress against it
- 🌙 Dark, distraction-free UI

---

## 🛠 Tech Stack

| Layer      | Tech |
|------------|------|
| Backend    | Java, Spring Boot, Spring Security, Spring Data JPA |
| Auth       | JWT (`jjwt`), BCrypt password hashing |
| Database   | H2 (in-memory, dev) |
| Frontend   | HTML, CSS, vanilla JavaScript (no framework) |

---

## 📁 Project Structure

```
src/main/java/com/example/demo/
├── config/
│   ├── JwtFilter.java          # validates Bearer token on each request
│   └── SecurityConfig.java     # security filter chain, permit-all routes
├── controller/
│   ├── UserController.java     # /auth/register, /auth/login
│   ├── TopicController.java    # /topics CRUD + filter endpoints
│   └── DashboardController.java# /dashboard stats, /goal
├── dto/
│   ├── UserDTO.java
│   └── TopicDTO.java
├── entity/
│   ├── User.java
│   └── Topic.java
├── repository/
│   ├── UserRepository.java
│   └── TopicRepository.java
├── service/
│   ├── UserService.java
│   ├── JwtService.java
│   └── CustomUserDetailsService.java
└── exception/
    └── UserNotFoundException.java

src/main/resources/
└── static/
    └── index.html              # the dashboard frontend
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven (or use the included `mvnw` wrapper)

### Run the backend



The API starts on **`Available at your primary URL https://placement-preparation-tracker-6.onrender.com0`**.

### Open the frontend

The dashboard lives at `src/main/resources/static/index.html` and is served automatically by Spring Boot at:

```
http://localhost:8080/index.html
```

Sign up for an account, log in, and start tracking topics.

### H2 Console (optional)

Inspect the in-memory database at:

```
http://localhost:8080/h2-console
```

---

## 🔑 Authentication

All `/topics`, `/dashboard`, and `/goal` endpoints require a valid JWT.

1. **Register**
   ```http
   POST /auth/register
   Content-Type: application/json

   { "userName": "rahul_dev", "password": "yourpassword" }
   ```

2. **Log in** — returns the JWT as a plain string
   ```http
   POST /auth/login
   Content-Type: application/json

   { "userName": "rahul_dev", "password": "yourpassword" }
   ```

3. **Use the token** on every subsequent request:
   ```http
   Authorization: Bearer <token>
   ```



---

## 📡 API Reference

### Auth
| Method | Endpoint          | Description         | Auth required |
|--------|-------------------|----------------------|----------------|
| POST   | `/auth/register`  | Create a new account | ❌ |
| POST   | `/auth/login`     | Log in, get JWT       | ❌ |

### Topics
| Method | Endpoint                                                   | Description                          |
|--------|-------------------------------------------------------------|---------------------------------------|
| GET    | `/topics`                                                    | List all topics                      |
| POST   | `/topics`                                                     | Create a topic                       |
| PUT    | `/topics/{id}`                                                | Update a topic                       |
| DELETE | `/topics/{id}`                                                | Delete a topic                       |
| GET    | `/topics/status/{status}/HoursSpent/{hours}`                  | Filter by status + min hours         |
| GET    | `/topics/category/{category}/{minHours}/{maxHours}`           | Filter by category + hour range      |
| GET    | `/topics/hours-less-than/{hours}`                             | Topics under a given hour count      |
| GET    | `/topics/category/{category}/hours-desc`                      | Category sorted by hours, descending |

### Dashboard & Goal
| Method | Endpoint      | Description                              |
|--------|---------------|-------------------------------------------|
| GET    | `/dashboard`  | Aggregate stats (totals, %, avg hours, etc.) |
| PUT    | `/goal`       | Update the target study-hour goal        |

---

## 🗺 Roadmap

- [ ] Add `id` to `TopicDTO` response for cleaner edit/delete on the frontend
- [ ] Move JWT secret to environment variable / config, not hardcoded
- [ ] Swap H2 for PostgreSQL for persistent storage
- [ ] Add CORS configuration for cross-origin frontend hosting
- [ ] Deploy (Render / Railway / Docker)

---

## 📄 License

MIT — feel free to fork and adapt for your own placement prep.
