CREATE TABLE healthcare_providers (
	id BIGSERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    specialization VARCHAR(100),
    qualification VARCHAR(100),
    experience INT,
    gender VARCHAR(10),
    date_of_birth DATE,
    personal_photo BYTEA,
    cv BYTEA,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
