package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.StoreKeeper;
import com.example.stock_management_system.service.StoreKeeperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/store-keepers")
public class StoreKeeperController {
    private final StoreKeeperService storeKeeperService;

    public StoreKeeperController(StoreKeeperService storeKeeperService) {
        this.storeKeeperService = storeKeeperService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStoreKeeper(@RequestBody StoreKeeper storeKeeper) {
        String message = storeKeeperService.saveStoreKeeper(storeKeeper);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreKeeper>> getAllStoreKeepers() {
        return new ResponseEntity<>(storeKeeperService.getAllStoreKeepers(), HttpStatus.OK);
    }

    @GetMapping("/by-section")
    public ResponseEntity<List<StoreKeeper>> getStoreKeepersBySection(@RequestParam String name) {
        return new ResponseEntity<>(storeKeeperService.getStoreKeepersBySection(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreKeeperById(@PathVariable UUID id) {
        Optional<StoreKeeper> storeKeeper = storeKeeperService.getStoreKeeperById(id);
        if (storeKeeper.isPresent()) {
            return new ResponseEntity<>(storeKeeper.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The store keeper with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStoreKeeper(@PathVariable UUID id, @RequestBody StoreKeeper storeKeeper) {
        String message = storeKeeperService.updateStoreKeeper(id, storeKeeper);
        if (message.equals("The store keeper with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStoreKeeper(@PathVariable UUID id) {
        String message = storeKeeperService.deleteStoreKeeper(id);
        if (message.equals("The store keeper with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
