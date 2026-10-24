package com.example.stock_management_system.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "stock_order")
public class StockOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String reference;
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "store_keeper_id")
    private StoreKeeper storeKeeper;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public StoreKeeper getStoreKeeper() {
        return storeKeeper;
    }
    public void setStoreKeeper(StoreKeeper storeKeeper) {
        this.storeKeeper = storeKeeper;
    }
}
