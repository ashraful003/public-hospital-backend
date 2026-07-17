CREATE TABLE doctor_bn (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_bn_name VARCHAR(255) NOT NULL,
    doctor_bn_id VARCHAR(100) NOT NULL UNIQUE,
    doctor_bn_degree VARCHAR(255) NOT NULL,
    doctor_bn_specialist VARCHAR(255) NOT NULL,
    doctor_bn_institute VARCHAR(255) NOT NULL,
    doctor_bn_license VARCHAR(255) NOT NULL,
    doctor_bn_visiting_time VARCHAR(255) NOT NULL
);