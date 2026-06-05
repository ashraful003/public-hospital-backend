CREATE TABLE report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bill_id VARCHAR(100),
    center_name VARCHAR(255),
    center_address VARCHAR(255),
    patient_id VARCHAR(100),
    patient_name VARCHAR(255),
    patient_age VARCHAR(50),
    patient_weight VARCHAR(50),
    doctor_name VARCHAR(255),
    lab_no VARCHAR(100),
    sample_date VARCHAR(100),
    review_date VARCHAR(100),
    report_date VARCHAR(100),
    test_status VARCHAR(50),
    tests TEXT
);