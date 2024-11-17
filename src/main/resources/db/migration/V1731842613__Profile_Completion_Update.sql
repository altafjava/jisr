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
-- User Entity Fields (Total: 35%)
('User', 'username', 5),
('User', 'first_name', 5),
('User', 'last_name', 5),
('User', 'email', 10),
('User', 'phone_number', 10),

-- PatientProfile Entity Fields (Total: 65%)
('PatientProfile', 'relationship', 5),
('PatientProfile', 'cancer_type', 5),
('PatientProfile', 'treatment_type', 10),
('PatientProfile', 'medicines', 5),
('PatientProfile', 'chemo_history', 5),
('PatientProfile', 'central_catheter_info', 5),
('PatientProfile', 'height', 5),
('PatientProfile', 'weight', 5),
('PatientProfile', 'bmi', 5),
('PatientProfile', 'date_of_birth', 5),
('PatientProfile', 'gender', 5),
('PatientProfile', 'region', 5);
