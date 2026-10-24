package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.repository.StockOrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockOrderService {
    private final StockOrderRepository stockOrderRepository;

    public StockOrderService(StockOrderRepository stockOrderRepository) {
        this.stockOrderRepository = stockOrderRepository;
    }

    public String saveStockOrder(StockOrder stockOrder) {
        stockOrderRepository.save(stockOrder);
        return "Stock order saved successfully";
    }

    public List<StockOrder> getAllStockOrders() {
        return stockOrderRepository.findAll();
    }

    public Optional<StockOrder> getStockOrderById(UUID id) {
        return stockOrderRepository.findById(id);
    }

    public String updateStockOrder(UUID id, StockOrder stockOrder) {
        Optional<StockOrder> existing = stockOrderRepository.findById(id);
        if (existing.isEmpty()) {
            return "The stock order with that id does not exist";
        }
        StockOrder s = existing.get();
        s.setOrderDate(stockOrder.getOrderDate());
        s.setStatus(stockOrder.getStatus());
        s.setReference(stockOrder.getReference());
        s.setTotalAmount(stockOrder.getTotalAmount());
        s.setSupplier(stockOrder.getSupplier());
        s.setStoreKeeper(stockOrder.getStoreKeeper());
        stockOrderRepository.save(s);
        return "Stock order updated successfully";
    }

    public String deleteStockOrder(UUID id) {
        if (!stockOrderRepository.existsById(id)) {
            return "The stock order with that id does not exist";
        }
        stockOrderRepository.deleteById(id);
        return "Stock order deleted successfully";
    }
}
