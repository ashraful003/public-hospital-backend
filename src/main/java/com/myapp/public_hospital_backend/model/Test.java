package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "test_name", nullable = false)
    private String testName;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String currency;
    @Column
    private String unit;
    @Column(name = "reference_range")
    private String range;
    @Column
    private String result;

    public Test() {
    }

    public Test(Long id,
                String name,
                String testName,
                Double price,
                String currency,
                String unit,
                String range,
                String result) {
        this.id = id;
        this.name = name;
        this.testName = testName;
        this.price = price;
        this.currency = currency;
        this.unit = unit;
        this.range = range;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}