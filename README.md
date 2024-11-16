# Jisr Healthcare Platform

## Overview

Jisr (جسر) is a healthcare platform designed to connect newly diagnosed cancer patients with healthcare professionals. It offers a comprehensive ecosystem to support patients' medical and emotional needs through a streamlined, multilingual (English and Arabic) platform.

## Features

- User roles: Patient, Caregiver, Healthcare Provider, Admin.
- JWT-based authentication and role-based access control.
- Multilingual support (English and Arabic).
- OTP-based user verification for secure registration and login.
- Appointment scheduling with healthcare providers.
- Admin-controlled settings for registration and file uploads.
- Patient and healthcare provider profile management.

## Tech Stack

- **Backend**: Spring Boot 3.3.5
- **Frontend**: (Planned) NextJs
- **Database**: PostgreSQL
- **Authentication**: JWT-based authentication & authorization
- **Messaging**: Email notifications (via SMTP) and SMS OTP for verification
- **Orchestration**: Docker, Kubernetes (future plan)
- **Cloud**: Any public cloud AWS, GCP, Azure (future plan)

## Prerequisites

- **Java**: JDK 21 or higher
- **Maven**: 3.8.5 or higher
- **Database**: PostgreSQL 17 or higher
- **Cache**: Redis
- **Postman**: For API testing
- **Git**: Version control

## Key Configuration Files

- `application.properties`: General application configuration.
- `application-secret.properties`: Contains sensitive credentials (excluded from version control).

## Security

- Authentication: Secure login via email/phone and password.
- Encryption: Passwords hashed with BCrypt.
- Validation: Strong input validation to prevent injection attacks.

## Project Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/altafjava/jisr.git
   cd jisr
   ```
2. Configure the Database and Environment
   - Install and start a PostgreSQL server.
   - Create a database named `jisr`:
     ```
     CREATE DATABASE jisr;
     ```
3. Redis Setup:

   - Redis is required for caching and session management. Follow the instructions below based on your operating system:

     1. Install Redis:
        **For Ubuntu/MacOS**:
        ```bash
        sudo apt update
        sudo apt install redis
        ```
        **For MacOS, you can use Homebrew:**
        ```
        brew install redis
        ```
     2. Start the Redis server:
        ```
        sudo service redis start
        ```
     3. Verify that Redis is running:
        ```
        redis-cli ping
        ```
        Expected response: `PONG`

     ### For Windows:

     - Redis is not natively supported on Windows, but you can use Windows Subsystem for Linux (WSL) to run Redis. Follow these steps:

     1. Install WSL:
        - Open PowerShell as Administrator and run:
          ```
          wsl --install
          ```
        - Restart your machine if prompted.
     2. Install Redis on WSL:
        - Launch the WSL terminal (Ubuntu is recommended as the default distribution).
        - Run the commands for Ubuntu mentioned above to install and start Redis.
     3. Connect to Redis:

        - Once Redis is running in WSL, it will be accessible to your application as localhost on the default port `6379`.

     4. Check Redis status:

        ```
        redis-cli ping
        ```

        Expected response: `PONG`

     5. Optional: Customize Redis configuration by editing the `redis.conf` file (e.g., change the default port or enable persistence).

4. Update Configuration:

   - Create `application-secret.properties` file inside `src/main/resources` directory and update the following settings:

     ```
     # Email configuration
     spring.mail.username=xxx@gmail.com
     spring.mail.password=<Google App Password>

     # JWT configuration
     jwt.secret=<Base64 encoded>

     # Admin default password (encrypted)
     ADMIN_PASSWORD_HASH=<Bcrypt encrypted password>
     ```

5. Install dependencies and build the project:
   ```
   mvn clean install
   ```
6. Run the application:
   ```
   mvn spring-boot:run
   ```
7. Access the application:
   - API documentation: http://localhost:8080/swagger-ui.html (if Swagger is configured).
   - Base URL: http://localhost:8080

## Notes:

- Ensure `PostgreSQL` and `Redis` servers are running before starting the application.
- Use strong values for `jwt.secret`
- Generate `ADMIN_PASSWORD_HASH` using a `bcrypt` password generator.

## Future Enhancements

- Integrate frontend using React or Angular.
- Add support for file uploads (e.g., lab reports, medical records).
- Expand appointment functionality with calendar integration.
- Introduce AI-driven recommendations for diet and treatment plans.

## Contributing

1.  Fork the repository.
2.  Create a feature branch:

    ```
    git checkout -b feature-name
    ```

3.  Commit your changes:

    ```
    git commit -m "Add feature description"
    ```

4.  Push the branch:

    ```
    git push origin feature-name
    ```

5.  Create a pull request.
