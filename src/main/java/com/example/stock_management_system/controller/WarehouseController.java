package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.Warehouse;
import com.example.stock_management_system.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveWarehouse(@RequestBody Warehouse warehouse) {
        String message = warehouseService.saveWarehouse(warehouse);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Warehouse>> getFreeWarehouses() {
        return new ResponseEntity<>(warehouseService.getFreeWarehouses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable UUID id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        if (warehouse.isPresent()) {
            return new ResponseEntity<>(warehouse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The warehouse with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWarehouse(@PathVariable UUID id, @RequestBody Warehouse warehouse) {
        String message = warehouseService.updateWarehouse(id, warehouse);
        if (message.equals("The warehouse with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable UUID id) {
        String message = warehouseService.deleteWarehouse(id);
        if (message.equals("The warehouse with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
