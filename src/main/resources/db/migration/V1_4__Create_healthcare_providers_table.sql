CREATE TABLE healthcare_providers (
	id BIGSERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    gender VARCHAR(10),
    date_of_birth DATE,
    specialization VARCHAR(100),
    qualification VARCHAR(100),
    experience INT,
    no_of_medical_licenses INT,
	personal_photo_url VARCHAR(500),
    cv_url VARCHAR(500),
    status VARCHAR(20),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
