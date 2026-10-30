package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.domain.StoreKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
    List<StockOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<StockOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    boolean existsByStoreKeeperAndOrderDateAndStatusNot(StoreKeeper storeKeeper, LocalDate orderDate, OrderStatus status);
    @Query("SELECT s.status, COUNT(s) FROM StockOrder s GROUP BY s.status")
    List<Object[]> countStockOrdersByStatus();
    @Modifying
    @Query("UPDATE StockOrder s SET s.status = :cancelled WHERE s.storeKeeper.id = :storeKeeperId AND s.orderDate = :date AND s.status <> :received")
    int cancelDay(@Param("storeKeeperId") UUID storeKeeperId, @Param("date") LocalDate date, @Param("cancelled") OrderStatus cancelled, @Param("received") OrderStatus received);
    long countByOrderDate(LocalDate orderDate);
    long countByStatus(OrderStatus status);
}
