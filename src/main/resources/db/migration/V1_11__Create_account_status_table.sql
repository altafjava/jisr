CREATE TABLE account_status (
    id BIGSERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    is_disabled BOOLEAN DEFAULT FALSE,
    disabled_reason TEXT,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT NOT NULL DEFAULT 0
);
