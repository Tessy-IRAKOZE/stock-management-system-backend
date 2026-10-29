package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.OrderStatus;
import com.example.stock_management_system.domain.StockOrder;
import com.example.stock_management_system.service.StockOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock-orders")
public class StockOrderController {
    private final StockOrderService stockOrderService;

    public StockOrderController(StockOrderService stockOrderService) {
        this.stockOrderService = stockOrderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStockOrder(@RequestBody StockOrder stockOrder) {
        String message = stockOrderService.saveStockOrder(stockOrder);
        if (message.equals("Stock order date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (message.equals("Store keeper already receives another order on that date")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StockOrder>> getAllStockOrders() {
        return new ResponseEntity<>(stockOrderService.getAllStockOrders(), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<StockOrder>> getStockOrdersByStatus(@RequestParam OrderStatus status) {
        return new ResponseEntity<>(stockOrderService.getStockOrdersByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<StockOrder>> getStockOrdersBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(stockOrderService.getStockOrdersBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockOrderById(@PathVariable UUID id) {
        Optional<StockOrder> stockOrder = stockOrderService.getStockOrderById(id);
        if (stockOrder.isPresent()) {
            return new ResponseEntity<>(stockOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The stock order with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStockOrder(@PathVariable UUID id, @RequestBody StockOrder stockOrder) {
        String message = stockOrderService.updateStockOrder(id, stockOrder);
        if (message.equals("The stock order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStockOrderStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
        String message = stockOrderService.updateStockOrderStatus(id, status);
        if (message.equals("The stock order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.startsWith("Cannot change status from")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStockOrder(@PathVariable UUID id) {
        String message = stockOrderService.deleteStockOrder(id);
        if (message.equals("The stock order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("That stock order has received items, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
