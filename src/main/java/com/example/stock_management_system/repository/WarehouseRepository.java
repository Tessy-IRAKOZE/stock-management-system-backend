package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
}
