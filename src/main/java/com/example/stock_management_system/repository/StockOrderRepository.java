package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
}
