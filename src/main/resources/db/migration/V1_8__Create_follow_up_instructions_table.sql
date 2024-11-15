CREATE TABLE follow_up_instructions (
    id BIGSERIAL PRIMARY KEY,
    appointment_id INT REFERENCES appointments(id),
    provider_id INT REFERENCES healthcare_providers(id),
    instructions TEXT NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
