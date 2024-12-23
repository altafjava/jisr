CREATE TABLE system_settings (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    display_name VARCHAR(100),
    value VARCHAR(100),
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    description VARCHAR(300) DEFAULT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);

INSERT INTO system_settings (name, display_name, enabled, description, created_by, updated_by) 
VALUES 
    ('patient_registration_enabled', 'Patient Registration Enabled', true, 'Controls patient registration availability', 'system_admin', 'system_admin'),
    ('healthcare_provider_registration_enabled', 'Healthcare Provider Registration Enabled', true, 'Controls healthcare provider registration availability', 'system_admin', 'system_admin'),
    ('general_registration_enabled', 'General Registration Enabled', true, 'Disables registration for both patients and healthcare providers if set to false', 'system_admin', 'system_admin'),
    ('file_upload_enabled', 'File Upload Enabled', true, 'Controls file upload availability for labs and medical reports', 'system_admin', 'system_admin');
