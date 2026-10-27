package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.domain.StoreKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
    List<StockOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<StockOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    boolean existsByStoreKeeperAndOrderDateAndStatusNot(StoreKeeper storeKeeper, LocalDate orderDate, OrderStatus status);
}
