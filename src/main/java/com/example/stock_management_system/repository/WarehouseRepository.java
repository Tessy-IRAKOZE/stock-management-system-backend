package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    @Query("SELECT w FROM Warehouse w WHERE NOT EXISTS (SELECT s FROM StoreKeeper s WHERE s.warehouse = w) ORDER BY w.warehouseNumber")
    List<Warehouse> findFreeWarehouses();
}
