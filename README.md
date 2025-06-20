# ai-test

This is a fullstack project with a Spring Boot backend and a Next.js (React, TypeScript, Tailwind) frontend.

---

## Running Locally

### Prerequisites
- Java 17+ (for backend)
- Node.js 20+ and npm (for frontend)
- Maven (for backend)

### 1. Start the Backend

```
cd backend
mvn spring-boot:run
```
- The backend will run on [http://localhost:8082](http://localhost:8082)
- OpenAPI/Swagger UI: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- H2 Console: [http://localhost:8082/h2-console](http://localhost:8082/h2-console)

### 2. Start the Frontend

Open a new terminal:

```
cd frontend
npm install
npm run dev
```
- The frontend will run on [http://localhost:3000](http://localhost:3000)

---

## Using Docker Compose

You can run both backend and frontend using Docker Compose:

```
docker compose up --build
```
- Backend: [http://localhost:8082](http://localhost:8082)
- Frontend: [http://localhost:3000](http://localhost:3000)

To stop and remove containers:
```
docker compose down
```

---

## API Endpoints

- `POST /api/auth/signup` — Register a new user (email, username, password required)
- `POST /api/auth/signin` — Sign in (username, password required, returns JWT)

---

## Default Admin User

On first run, a default admin user is created automatically:

- **Username:** `admin`
- **Email:** `admin@test.nl`
- **Password:** `admin1234`
- **Role:** `ADMIN`

You can use this user to sign in immediately after starting the backend.

---

## User Roles

- `USER`: Default for new signups
- `ADMIN`: Only the default admin user

---

## Environment Variables

- The frontend expects the backend at `http://localhost:8082` (locally) or `http://backend:8082` (in Docker Compose)
- You can override this by setting `NEXT_PUBLIC_BACKEND_URL` in the frontend environment

---

## Project Structure

- `backend/` — Spring Boot API, JWT auth, H2, JPA, Swagger
- `frontend/` — Next.js app, signup/signin forms, JWT in localStorage
- `docker-compose.yml` — Orchestrates both services

---

## Testing

- Backend: `cd backend && mvn test`
- Frontend: E2E tests run via Playwright (see CI for details)

---

## CI/CD

- GitHub Actions build, test, and publish Allure reports for both modules
- Allure report is published to GitHub Pages

---

For more details, see the code and comments in each module.