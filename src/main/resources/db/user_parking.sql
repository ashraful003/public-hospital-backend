CREATE TABLE user_parking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
    patient_id VARCHAR(50) NOT NULL,
    patient_name VARCHAR(100),
    mobile_no VARCHAR(20),
    vehicle_no VARCHAR(50) NOT NULL,
    vehicle_type VARCHAR(30),
    parking_id BIGINT NOT NULL,
    floor VARCHAR(50),
    parking_no VARCHAR(50),
    entry_time DATETIME NOT NULL,
    exit_time DATETIME,
    parking_fee DOUBLE,
    total_hours BIGINT,
    total_amount DOUBLE,
    is_active BOOLEAN DEFAULT TRUE,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    CONSTRAINT fk_user_parking_parking
        FOREIGN KEY (parking_id)
        REFERENCES parking(id)
);