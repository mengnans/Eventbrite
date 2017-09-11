package com.eventbrite.domain;
public class Certificate {
    private int id;
    private String name;
    private String email;
    private String certificateAddress;

    public Certificate(int id, String name, String email, String certificateAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.certificateAddress = certificateAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificateAddress() {
        return certificateAddress;
    }

    public void setCertificateAddress(String certificateAddress) {
        this.certificateAddress = certificateAddress;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", certificateAddress='" + certificateAddress + '\'' +
                '}';
    }
}
