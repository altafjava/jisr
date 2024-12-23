CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    display_name VARCHAR(50),
    version INT NOT NULL DEFAULT 0
);

INSERT INTO roles (name, display_name) VALUES
('ADMIN', 'Admin'),
('PATIENT', 'Patient'),
('CAREGIVER', 'Caregiver'),
('HEALTHCARE_PROVIDER', 'Healthcare Provider');