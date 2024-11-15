CREATE TABLE system_settings (
    id BIGSERIAL PRIMARY KEY,
    setting_key VARCHAR(100) NOT NULL UNIQUE,
    setting_value VARCHAR(255) NOT NULL
);

-- Insert initial settings
INSERT INTO system_settings (setting_key, setting_value) VALUES 
    ('registration_enabled', 'false'),
    ('registration_disabled_message', 'Registration is currently enabled.');