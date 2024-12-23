CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    full_name VARCHAR(300) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    profile_completion INT DEFAULT 0,
    is_active BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO users (username, first_name, last_name, full_name, email, phone_number, password_hash, user_type, profile_completion, is_active, created_by, updated_by, created_date, updated_date)
VALUES ('admin', 'Super', 'User', 'Super User', 'superuser@admin.com', '1234567890', '${ADMIN_PASSWORD_HASH}', 'ADMIN', 30, true, 'system_admin', 'system_admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_id)
VALUES
  ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ADMIN'));