package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.Warehouse;
import com.example.stock_management_system.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public String saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return "Warehouse saved successfully";
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(UUID id) {
        return warehouseRepository.findById(id);
    }

    public String updateWarehouse(UUID id, Warehouse warehouse) {
        Optional<Warehouse> existing = warehouseRepository.findById(id);
        if (existing.isEmpty()) {
            return "The warehouse with that id does not exist";
        }
        Warehouse w = existing.get();
        w.setWarehouseNumber(warehouse.getWarehouseNumber());
        w.setName(warehouse.getName());
        warehouseRepository.save(w);
        return "Warehouse updated successfully";
    }

    public String deleteWarehouse(UUID id) {
        if (!warehouseRepository.existsById(id)) {
            return "The warehouse with that id does not exist";
        }
        warehouseRepository.deleteById(id);
        return "Warehouse deleted successfully";
    }
}
