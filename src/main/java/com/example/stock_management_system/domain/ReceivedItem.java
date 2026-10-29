package com.example.stock_management_system.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "received_item")
public class ReceivedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String productName;
    private String remarks;
    private int quantity;
    private LocalDate receivedDate;
    @ManyToOne
    @JoinColumn(name = "stock_order_id")
    private StockOrder stockOrder;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public LocalDate getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }
    public StockOrder getStockOrder() {
        return stockOrder;
    }
    public void setStockOrder(StockOrder stockOrder) {
        this.stockOrder = stockOrder;
    }
}
