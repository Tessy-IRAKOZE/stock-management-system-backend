package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.ReceivedItem;
import com.example.stock_management_system.service.ReceivedItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/received-items")
public class ReceivedItemController {
    private final ReceivedItemService receivedItemService;

    public ReceivedItemController(ReceivedItemService receivedItemService) {
        this.receivedItemService = receivedItemService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveReceivedItem(@RequestParam UUID stockOrderId, @RequestBody ReceivedItem receivedItem) {
        String message = receivedItemService.saveReceivedItem(stockOrderId, receivedItem);
        if (message.equals("The stock order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("Received items are only for RECEIVED stock orders")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @GetMapping("/supplier/{tinNumber}")
    public ResponseEntity<List<ReceivedItem>> getReceivedItemsOfSupplier(@PathVariable String tinNumber) {
        return new ResponseEntity<>(receivedItemService.getReceivedItemsOfSupplier(tinNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReceivedItem(@PathVariable UUID id) {
        String message = receivedItemService.deleteReceivedItem(id);
        if (message.equals("The received item with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
