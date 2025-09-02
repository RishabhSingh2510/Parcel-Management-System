Parcel Management System

This is a full-stack Project Management System built with:

-   Frontend: Angular
-   Backend: Spring Boot (Java, Maven)

The system provides a modern web interface for managing projects, tasks,
and related data with a REST API backend.

------------------------------------------------------------------------

Features

-   Angular-based single-page application (SPA)
-   Spring Boot REST API backend
-   Easy local development with npm and maven
-   Modular architecture for scalability

------------------------------------------------------------------------

Project Structure

    project-management-system/
    │── frontend/   → Angular application  
    │── backend/    → Spring Boot application  

------------------------------------------------------------------------

Setup Instructions

1. Clone the repository

    git clone https://github.com/your-username/project-management-system.git
    cd project-management-system

------------------------------------------------------------------------

2. Backend Setup (Spring Boot)

Navigate to the backend folder:

    cd backend/backend

Run with Maven Wrapper:

    ./mvnw spring-boot:run   # Linux / macOS
    mvnw spring-boot:run     # Windows

The backend will start on:
 http://localhost:8080

------------------------------------------------------------------------

3. Frontend Setup (Angular)

Navigate to the frontend folder:

    cd frontend/frontend

Install dependencies:

    npm install

Start the Angular dev server:

    npm start

The frontend will start on:
 http://localhost:4200

------------------------------------------------------------------------

Connecting Frontend & Backend

The Angular app communicates with the Spring Boot API.
Check and update the backend API URL in:

    frontend/src/environments/environment.ts

Default backend URL:

    http://localhost:8080

------------------------------------------------------------------------

Running Tests

Backend (JUnit)

    cd backend/backend
    ./mvnw test

Frontend (Karma/Jasmine)

    cd frontend/frontend
    npm test

------------------------------------------------------------------------

Build for Production

Backend

    cd backend/backend
    ./mvnw clean package

Generates a .jar file in target/.

Frontend

    cd frontend/frontend
    ng build --prod

Outputs static files to dist/.

------------------------------------------------------------------------

