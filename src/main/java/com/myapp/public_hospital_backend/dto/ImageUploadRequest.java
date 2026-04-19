package com.myapp.public_hospital_backend.dto;

public class ImageUploadRequest {
    private String email;
    private String imageBase64;

    public ImageUploadRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
