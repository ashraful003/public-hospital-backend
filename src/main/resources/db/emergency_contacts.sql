CREATE TABLE emergency_contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    emergency_number VARCHAR(20) NOT NULL,
    emergency_doctor_number VARCHAR(20) NOT NULL,
    emergency_doctor_whatsapp_number VARCHAR(20) NOT NULL
);