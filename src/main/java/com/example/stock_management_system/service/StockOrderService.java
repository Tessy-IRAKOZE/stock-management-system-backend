package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.repository.ReceivedItemRepository;
import com.example.stock_management_system.repository.StockOrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockOrderService {
    private final StockOrderRepository stockOrderRepository;
    private final ReceivedItemRepository receivedItemRepository;

    public StockOrderService(StockOrderRepository stockOrderRepository, ReceivedItemRepository receivedItemRepository) {
        this.stockOrderRepository = stockOrderRepository;
        this.receivedItemRepository = receivedItemRepository;
    }

    public String saveStockOrder(StockOrder stockOrder) {
        if (stockOrder.getOrderDate().isBefore(LocalDate.now())) {
            return "Stock order date cannot be in the past";
        }
        if (stockOrderRepository.existsByStoreKeeperAndOrderDateAndStatusNot(stockOrder.getStoreKeeper(), stockOrder.getOrderDate(), OrderStatus.CANCELLED)) {
            return "Store keeper already receives another order on that date";
        }
        stockOrder.setStatus(OrderStatus.PLACED);
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
        s.setReference(stockOrder.getReference());
        s.setTotalAmount(stockOrder.getTotalAmount());
        s.setSupplier(stockOrder.getSupplier());
        s.setStoreKeeper(stockOrder.getStoreKeeper());
        stockOrderRepository.save(s);
        return "Stock order updated successfully";
    }

    public String deleteStockOrder(UUID id) {
        Optional<StockOrder> existing = stockOrderRepository.findById(id);
        if (existing.isEmpty()) {
            return "The stock order with that id does not exist";
        }
        if (receivedItemRepository.existsByStockOrder(existing.get())) {
            return "That stock order has received items, delete them first";
        }
        stockOrderRepository.deleteById(id);
        return "Stock order deleted successfully";
    }

    public String updateStockOrderStatus(UUID id, OrderStatus newStatus) {
        Optional<StockOrder> existing = stockOrderRepository.findById(id);
        if (existing.isEmpty()) {
            return "The stock order with that id does not exist";
        }
        StockOrder s = existing.get();
        OrderStatus current = s.getStatus();
        boolean allowed = false;
        if (current == OrderStatus.PLACED && (newStatus == OrderStatus.APPROVED || newStatus == OrderStatus.CANCELLED)) {
            allowed = true;
        }
        if (current == OrderStatus.APPROVED && (newStatus == OrderStatus.RECEIVED || newStatus == OrderStatus.CANCELLED)) {
            allowed = true;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        s.setStatus(newStatus);
        stockOrderRepository.save(s);
        return "Stock order status updated successfully";
    }

    public List<StockOrder> getStockOrdersByStatus(OrderStatus status) {
        return stockOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<StockOrder> getStockOrdersBetween(LocalDate start, LocalDate end) {
        return stockOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
}
