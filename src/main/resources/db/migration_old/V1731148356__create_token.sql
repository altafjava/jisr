CREATE TABLE IF NOT EXISTS token (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    expires_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_patient
        FOREIGN KEY(patient_id) 
            REFERENCES patient(id)
            ON DELETE CASCADE
);
