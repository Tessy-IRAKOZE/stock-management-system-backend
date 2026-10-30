package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.repository.ReceivedItemRepository;
import com.example.stock_management_system.repository.StockOrderRepository;
import com.example.stock_management_system.repository.StoreKeeperRepository;
import com.example.stock_management_system.repository.SupplierRepository;
import com.example.stock_management_system.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    private final SupplierRepository supplierRepository;
    private final StoreKeeperRepository storeKeeperRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockOrderRepository stockOrderRepository;
    private final ReceivedItemRepository receivedItemRepository;

    public DashboardService(SupplierRepository supplierRepository, StoreKeeperRepository storeKeeperRepository, WarehouseRepository warehouseRepository, StockOrderRepository stockOrderRepository, ReceivedItemRepository receivedItemRepository) {
        this.supplierRepository = supplierRepository;
        this.storeKeeperRepository = storeKeeperRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockOrderRepository = stockOrderRepository;
        this.receivedItemRepository = receivedItemRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        long freeWarehouses = warehouseRepository.findFreeWarehouses().size();
        summary.put("suppliers", supplierRepository.count());
        summary.put("storeKeepers", storeKeeperRepository.count());
        summary.put("freeWarehouses", freeWarehouses);
        summary.put("ordersToday", stockOrderRepository.countByOrderDate(LocalDate.now()));
        summary.put("placed", stockOrderRepository.countByStatus(OrderStatus.PLACED));
        summary.put("receivedItems", receivedItemRepository.count());
        return summary;
    }
}
