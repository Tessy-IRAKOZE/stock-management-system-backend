package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.StoreKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StoreKeeperRepository extends JpaRepository<StoreKeeper, UUID> {
}
