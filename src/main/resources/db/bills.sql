CREATE TABLE bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prescription_id BIGINT NOT NULL,
    doctor_name VARCHAR(255),
    patient_id VARCHAR(100),
    patient_name VARCHAR(255),
    patient_age VARCHAR(50),
    patient_weight VARCHAR(50),
    selected_tests TEXT,
    total_bill DECIMAL(10,2),
    discount_amount DECIMAL(10,2),
    total_pay DECIMAL(10,2),
    refund_amount DECIMAL(10,2),
    total_due DECIMAL(10,2),
    accountant_name VARCHAR(255),
    accountant_id VARCHAR(100),
    status VARCHAR(30),
    test_status VARCHAR(30),
    payment_datetime DATETIME,
    created_date DATETIME,
    CONSTRAINT fk_bill_prescription
        FOREIGN KEY (prescription_id)
        REFERENCES prescriptions(id)
        ON DELETE CASCADE
);