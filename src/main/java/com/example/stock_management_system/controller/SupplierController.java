package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.Supplier;
import com.example.stock_management_system.service.StoreKeeperService;
import com.example.stock_management_system.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private final StoreKeeperService storeKeeperService;

    public SupplierController(SupplierService supplierService, StoreKeeperService storeKeeperService) {
        this.supplierService = supplierService;
        this.storeKeeperService = storeKeeperService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSupplier(@RequestBody Supplier supplier) {
        String message = supplierService.saveSupplier(supplier);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/by-contact-person")
    public ResponseEntity<List<Supplier>> getSuppliersByContactPerson(@RequestParam String contactPerson) {
        return new ResponseEntity<>(supplierService.getSuppliersByContactPerson(contactPerson), HttpStatus.OK);
    }

    @GetMapping("/of-store-keeper/{storeKeeperId}")
    public ResponseEntity<?> getSuppliersOfStoreKeeper(@PathVariable UUID storeKeeperId) {
        if (storeKeeperService.getStoreKeeperById(storeKeeperId).isEmpty()) {
            return new ResponseEntity<>("The store keeper with that id does not exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierService.getSuppliersOfStoreKeeper(storeKeeperId), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable UUID id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The supplier with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable UUID id, @RequestBody Supplier supplier) {
        String message = supplierService.updateSupplier(id, supplier);
        if (message.equals("The supplier with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable UUID id) {
        String message = supplierService.deleteSupplier(id);
        if (message.equals("The supplier with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
