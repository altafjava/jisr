CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,
    patient_id INT REFERENCES patient_profiles(id),
    provider_id INT REFERENCES healthcare_providers(id),
    appointment_status_id INT REFERENCES appointment_status(id),
    appointment_time TIMESTAMP NOT NULL,
    reason_for_visit TEXT,
    ms_teams_link VARCHAR(255),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
