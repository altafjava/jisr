CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('patient');
INSERT INTO roles (name) VALUES ('caregiver');
INSERT INTO roles (name) VALUES ('healthcare_provider');

CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    relationship VARCHAR(50),
    role VARCHAR(50) NOT NULL,
    first_login BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE patient_health_details (
    id BIGSERIAL PRIMARY KEY,
    cancer_type VARCHAR(100),
    cancer_treatment VARCHAR(100),
    medicines_and_doses TEXT,
    chemotherapy_history INTEGER,
    has_central_catheter BOOLEAN,
    catheter_type VARCHAR(100),
    height INTEGER,
    weight INTEGER,
    bmi DOUBLE PRECISION,
    age INTEGER,
    date_of_birth DATE,
    gender VARCHAR(10),
    healthcare_region VARCHAR(100),
    patient_id BIGINT NOT NULL,  -- Foreign key reference to Patient
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);