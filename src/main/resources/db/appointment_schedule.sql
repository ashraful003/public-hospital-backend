CREATE TABLE appointment_schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    national_id VARCHAR(50) NOT NULL,
    day VARCHAR(20) NOT NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);