CREATE TABLE clinical_notes (
    note_id SERIAL PRIMARY KEY,
    appointment_id INT REFERENCES appointments(id),
    provider_id INT REFERENCES healthcare_providers(id),
    notes TEXT NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
