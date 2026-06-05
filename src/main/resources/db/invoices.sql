CREATE TABLE invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bill_id BIGINT,
    prescription_id BIGINT,
    patient_id VARCHAR(50),
    patient_name VARCHAR(100),
    patient_age VARCHAR(20),
    accountant_name VARCHAR(100),
    accountant_id VARCHAR(50),
    selected_tests TEXT,
    total_bill DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2) DEFAULT 0,
    total_after_discount DECIMAL(10,2) NOT NULL,
    paid_amount DECIMAL(10,2) DEFAULT 0,
    due_amount DECIMAL(10,2) DEFAULT 0,
    status VARCHAR(20),
    payment_datetime DATETIME,
    created_at DATETIME
);