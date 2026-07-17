CREATE TABLE IF NOT EXISTS inpatient_bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    admission_id BIGINT NOT NULL UNIQUE,
    patient_id BIGINT NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    doctor_id BIGINT,
    doctor_name VARCHAR(255),
    seat_no VARCHAR(50),
    seat_type VARCHAR(50),
    total_days INT,
    bed_charge DOUBLE NOT NULL DEFAULT 0,
    doctor_charge DOUBLE NOT NULL DEFAULT 0,
    operation_charge DOUBLE NOT NULL DEFAULT 0,
    medicine_charge DOUBLE NOT NULL DEFAULT 0,
    pathology_charge DOUBLE NOT NULL DEFAULT 0,
    radiology_charge DOUBLE NOT NULL DEFAULT 0,
    nursing_charge DOUBLE NOT NULL DEFAULT 0,
    oxygen_charge DOUBLE NOT NULL DEFAULT 0,
    other_charge DOUBLE NOT NULL DEFAULT 0,
    discount DOUBLE NOT NULL DEFAULT 0,
    vat DOUBLE NOT NULL DEFAULT 0,
    subtotal DOUBLE NOT NULL DEFAULT 0,
    grand_total DOUBLE NOT NULL DEFAULT 0,
    paid_amount DOUBLE NOT NULL DEFAULT 0,
    due_amount DOUBLE NOT NULL DEFAULT 0,
    payment_status VARCHAR(20) NOT NULL DEFAULT 'UNPAID',
    payment_method VARCHAR(30),
    paid_by_id BIGINT,
    paid_by_name VARCHAR(255),
    payment_date DATETIME,
    bill_status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    bill_date DATETIME,
    created_by_id BIGINT,
    created_by_name VARCHAR(255),
    CONSTRAINT fk_inpatient_bills_admission
    FOREIGN KEY (admission_id) REFERENCES hospital_admissions(id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_inpatient_bills_patient_id ON inpatient_bills (patient_id);
CREATE INDEX idx_inpatient_bills_payment_status ON inpatient_bills (payment_status);
CREATE INDEX idx_inpatient_bills_bill_status ON inpatient_bills (bill_status);
CREATE INDEX idx_inpatient_bills_due_amount ON inpatient_bills (due_amount);
CREATE INDEX idx_inpatient_bills_paid_by_id ON inpatient_bills (paid_by_id);