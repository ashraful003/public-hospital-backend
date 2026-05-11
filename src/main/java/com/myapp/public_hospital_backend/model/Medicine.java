package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "medicines",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "medicine_name",
                                "power",
                                "pharmaceutical_name"
                        }
                )
        }
)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medicine_name", nullable = false)
    private String medicineName;
    @Column(nullable = false)
    private String power;
    @Column(name = "name", nullable = false)
    private String name;
    private Double unitPrice;
    private Double totalPrice;
    @Column(length = 20000)
    private String indications;
    @Column(length = 20000)
    private String pharmacology;
    @Column(length = 20000)
    private String dosage;
    @Column(length = 20000)
    private String interaction;
    @Column(length = 20000)
    private String contraindications;
    @Column(length = 20000)
    private String sideEffects;
    @Column(length = 20000)
    private String pregnancyLactation;
    @Column(length = 20000)
    private String precautionsWarnings;
    @Column(length = 20000)
    private String specialPopulations;
    @Column(length = 20000)
    private String overdoseEffects;
    @Column(length = 20000)
    private String reconstitution;
    @Column(length = 20000)
    private String storageConditions;
    @Column(length = 20000)
    private String chemicalStructure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getPregnancyLactation() {
        return pregnancyLactation;
    }

    public void setPregnancyLactation(String pregnancyLactation) {
        this.pregnancyLactation = pregnancyLactation;
    }

    public String getPrecautionsWarnings() {
        return precautionsWarnings;
    }

    public void setPrecautionsWarnings(String precautionsWarnings) {
        this.precautionsWarnings = precautionsWarnings;
    }

    public String getSpecialPopulations() {
        return specialPopulations;
    }

    public void setSpecialPopulations(String specialPopulations) {
        this.specialPopulations = specialPopulations;
    }

    public String getOverdoseEffects() {
        return overdoseEffects;
    }

    public void setOverdoseEffects(String overdoseEffects) {
        this.overdoseEffects = overdoseEffects;
    }

    public String getReconstitution() {
        return reconstitution;
    }

    public void setReconstitution(String reconstitution) {
        this.reconstitution = reconstitution;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public String getChemicalStructure() {
        return chemicalStructure;
    }

    public void setChemicalStructure(String chemicalStructure) {
        this.chemicalStructure = chemicalStructure;
    }
}