package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    List<Supplier> findByContactPersonIgnoreCaseOrderByCompanyNameAsc(String contactPerson);
}
