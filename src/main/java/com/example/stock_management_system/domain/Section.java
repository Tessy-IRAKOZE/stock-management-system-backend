package com.example.stock_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "sections")
    @JsonIgnore
    private List<StoreKeeper> storeKeepers = new ArrayList<>();

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<StoreKeeper> getStoreKeepers() {
        return storeKeepers;
    }
    public void setStoreKeepers(List<StoreKeeper> storeKeepers) {
        this.storeKeepers = storeKeepers;
    }
}
