CREATE TABLE patient_profiles (
	id BIGSERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    relationship VARCHAR(100),
    cancer_type VARCHAR(100),
    treatment_type VARCHAR(100),
    medicines TEXT,
    chemo_history TEXT,
    central_catheter_info BOOLEAN,
    height DECIMAL(5, 2),
    weight DECIMAL(5, 2),
    bmi DECIMAL(5, 2),
    date_of_birth DATE,
    gender VARCHAR(10),
    region VARCHAR(100),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INT DEFAULT 0
);