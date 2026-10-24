package com.example.stock_management_system.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String companyName;
    private String contactPerson;
    @Column(unique = true)
    private String tinNumber;
    private String phone;
    private String email;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getTinNumber() {
        return tinNumber;
    }
    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
