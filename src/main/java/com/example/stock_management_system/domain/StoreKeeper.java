package com.example.stock_management_system.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "store_keeper")
public class StoreKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = true)
    private Warehouse warehouse;
    @ManyToMany
    @JoinTable(name = "store_keeper_section",
            joinColumns = @JoinColumn(name = "store_keeper_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections = new ArrayList<>();

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Warehouse getWarehouse() {
        return warehouse;
    }
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    public List<Section> getSections() {
        return sections;
    }
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
