CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_no VARCHAR(20) NOT NULL,
    doctor_name VARCHAR(255) NOT NULL,
    doctor_id BIGINT NOT NULL,
    specialist VARCHAR(255) NOT NULL,
    patient_id BIGINT NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    day VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'WAITING',
    reason VARCHAR(1000),
    CONSTRAINT appointment_status_check
    CHECK (status IN ('WAITING','VISITED','CANCELLED','REJECTED'))
);