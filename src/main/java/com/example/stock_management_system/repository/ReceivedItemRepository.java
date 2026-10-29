package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.ReceivedItem;
import com.example.stock_management_system.domain.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ReceivedItemRepository extends JpaRepository<ReceivedItem, UUID> {
    @Query("SELECT d FROM ReceivedItem d WHERE d.stockOrder.supplier.tinNumber = :tinNumber ORDER BY d.receivedDate DESC")
    List<ReceivedItem> findReceivedItemsOfSupplier(@Param("tinNumber") String tinNumber);
    boolean existsByStockOrder(StockOrder stockOrder);
}
