-- Create a table to store profile field weights
CREATE TABLE profile_field_weights (
    id BIGSERIAL PRIMARY KEY,
    entity_name VARCHAR(100) NOT NULL, -- 'User' or 'PatientProfile'
    field_name VARCHAR(100) NOT NULL,
    weight INT NOT NULL CHECK (weight > 0 AND weight <= 100),
    UNIQUE(entity_name, field_name) -- Prevent duplicate entries
);

-- Populate the profile field weights
INSERT INTO profile_field_weights (entity_name, field_name, weight)
VALUES
-- User Entity Fields (Total: 30%)
('User', 'username', 4),
('User', 'first_name', 4),
('User', 'father_name', 4),
('User', 'last_name', 4),
('User', 'email', 7),
('User', 'phone_number', 7),

-- PatientProfile Entity Fields (Total: 70%)
('PatientProfile', 'gender', 6),
('PatientProfile', 'date_of_birth', 6),
('PatientProfile', 'height', 6),
('PatientProfile', 'weight', 6),
('PatientProfile', 'relationship', 6),
('PatientProfile', 'cancer_type', 6),
('PatientProfile', 'treatment_type', 6),
('PatientProfile', 'medicines_and_doses', 6),
('PatientProfile', 'chemotherapy_history', 6),
('PatientProfile', 'has_central_catheter', 5),
('PatientProfile', 'central_catheter_type', 5),
('PatientProfile', 'healthcare_region', 6),

-- HealthcareProvider Entity Fields (Total: 70%)
('HealthcareProvider', 'gender', 6),
('HealthcareProvider', 'date_of_birth', 6),
('HealthcareProvider', 'specialization', 10),
('HealthcareProvider', 'qualification', 10),
('HealthcareProvider', 'experience', 10),
('HealthcareProvider', 'no_of_medical_licenses', 8),
('HealthcareProvider', 'personal_photo_url', 10),
('HealthcareProvider', 'cv_url', 10);