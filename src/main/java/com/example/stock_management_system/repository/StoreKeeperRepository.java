package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.StoreKeeper;
import com.example.stock_management_system.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface StoreKeeperRepository extends JpaRepository<StoreKeeper, UUID> {
    @Query("SELECT s FROM StoreKeeper s JOIN s.sections c WHERE LOWER(c.name) = LOWER(:name)")
    List<StoreKeeper> findStoreKeepersBySection(@Param("name") String name);
    boolean existsByWarehouse(Warehouse warehouse);
}
