CREATE TABLE dose_time (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dose_time VARCHAR(255) NOT NULL,
    national_id VARCHAR(255) NOT NULL,
    UNIQUE KEY unique_dose_national (dose_time, national_id)
);