package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    List<Supplier> findByContactPersonIgnoreCaseOrderByCompanyNameAsc(String contactPerson);
    @Query("SELECT DISTINCT o.supplier FROM StockOrder o WHERE o.storeKeeper.id = :storeKeeperId")
    List<Supplier> findSuppliersOfStoreKeeper(@Param("storeKeeperId") UUID storeKeeperId);
}
