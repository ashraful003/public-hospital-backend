CREATE TABLE hospital_admissions (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    patient_age INTEGER,
    patient_weight DOUBLE PRECISION,
    patient_address VARCHAR(500),
    doctor_id BIGINT,
    doctor_name VARCHAR(255),
    seat_no VARCHAR(50) NOT NULL,
    admission_type VARCHAR(50) NOT NULL,
    diagnosis TEXT,
    remarks TEXT,
    admission_date TIMESTAMP NOT NULL,
    expected_discharge_date DATE,
    discharge_date TIMESTAMP,
    status VARCHAR(30) NOT NULL,
    admited_by_id BIGINT,
    admited_by_name VARCHAR(255),
    discharged_by_id BIGINT,
    discharged_by_name VARCHAR(255)
);
CREATE INDEX idx_patient_id
ON hospital_admissions(patient_id);
CREATE INDEX idx_doctor_id
ON hospital_admissions(doctor_id);
CREATE INDEX idx_seat_no
ON hospital_admissions(seat_no);
CREATE INDEX idx_status
ON hospital_admissions(status);