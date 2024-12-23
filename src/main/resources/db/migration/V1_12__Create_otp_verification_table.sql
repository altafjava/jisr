CREATE TABLE otp_verification (
    id BIGSERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    otp_code VARCHAR(10) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    is_verified BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
