package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.domain.ReceivedItem;
import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.repository.ReceivedItemRepository;
import com.example.stock_management_system.repository.StockOrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceivedItemService {
    private final ReceivedItemRepository receivedItemRepository;
    private final StockOrderRepository stockOrderRepository;

    public ReceivedItemService(ReceivedItemRepository receivedItemRepository, StockOrderRepository stockOrderRepository) {
        this.receivedItemRepository = receivedItemRepository;
        this.stockOrderRepository = stockOrderRepository;
    }

    public String saveReceivedItem(UUID stockOrderId, ReceivedItem receivedItem) {
        Optional<StockOrder> existing = stockOrderRepository.findById(stockOrderId);
        if (existing.isEmpty()) {
            return "The stock order with that id does not exist";
        }
        StockOrder stockOrder = existing.get();
        if (stockOrder.getStatus() != OrderStatus.RECEIVED) {
            return "Received items are only for RECEIVED stock orders";
        }
        receivedItem.setReceivedDate(LocalDate.now());
        receivedItem.setStockOrder(stockOrder);
        receivedItemRepository.save(receivedItem);
        return "Received item saved successfully";
    }

    public List<ReceivedItem> getReceivedItemsOfSupplier(String tinNumber) {
        return receivedItemRepository.findReceivedItemsOfSupplier(tinNumber);
    }

    public String deleteReceivedItem(UUID id) {
        if (!receivedItemRepository.existsById(id)) {
            return "The received item with that id does not exist";
        }
        receivedItemRepository.deleteById(id);
        return "Received item deleted successfully";
    }
}
