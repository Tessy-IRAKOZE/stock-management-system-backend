package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.Section;
import com.example.stock_management_system.domain.StoreKeeper;
import com.example.stock_management_system.domain.Warehouse;
import com.example.stock_management_system.repository.SectionRepository;
import com.example.stock_management_system.repository.StoreKeeperRepository;
import com.example.stock_management_system.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreKeeperService {
    private final StoreKeeperRepository storeKeeperRepository;
    private final SectionRepository sectionRepository;
    private final WarehouseRepository warehouseRepository;

    public StoreKeeperService(StoreKeeperRepository storeKeeperRepository, SectionRepository sectionRepository, WarehouseRepository warehouseRepository) {
        this.storeKeeperRepository = storeKeeperRepository;
        this.sectionRepository = sectionRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public String saveStoreKeeper(StoreKeeper storeKeeper) {
        storeKeeperRepository.save(storeKeeper);
        return "Store keeper saved successfully";
    }

    public List<StoreKeeper> getAllStoreKeepers() {
        return storeKeeperRepository.findAll();
    }

    public Optional<StoreKeeper> getStoreKeeperById(UUID id) {
        return storeKeeperRepository.findById(id);
    }

    public String updateStoreKeeper(UUID id, StoreKeeper storeKeeper) {
        Optional<StoreKeeper> existing = storeKeeperRepository.findById(id);
        if (existing.isEmpty()) {
            return "The store keeper with that id does not exist";
        }
        StoreKeeper s = existing.get();
        s.setFirstName(storeKeeper.getFirstName());
        s.setLastName(storeKeeper.getLastName());
        s.setPhone(storeKeeper.getPhone());
        s.setWarehouse(storeKeeper.getWarehouse());
        s.setSections(storeKeeper.getSections());
        storeKeeperRepository.save(s);
        return "Store keeper updated successfully";
    }

    public String deleteStoreKeeper(UUID id) {
        if (!storeKeeperRepository.existsById(id)) {
            return "The store keeper with that id does not exist";
        }
        storeKeeperRepository.deleteById(id);
        return "Store keeper deleted successfully";
    }

    public List<StoreKeeper> getStoreKeepersBySection(String name) {
        return storeKeeperRepository.findStoreKeepersBySection(name);
    }

    public String addSectionToStoreKeeper(UUID storeKeeperId, UUID sectionId) {
        Optional<StoreKeeper> existingStoreKeeper = storeKeeperRepository.findById(storeKeeperId);
        if (existingStoreKeeper.isEmpty()) {
            return "The store keeper with that id does not exist";
        }
        Optional<Section> existingSection = sectionRepository.findById(sectionId);
        if (existingSection.isEmpty()) {
            return "That section does not exist";
        }
        StoreKeeper storeKeeper = existingStoreKeeper.get();
        Section section = existingSection.get();
        if (storeKeeper.getSections().contains(section)) {
            return "The store keeper already has that section";
        }
        storeKeeper.getSections().add(section);
        storeKeeperRepository.save(storeKeeper);
        return "Section added to the store keeper";
    }

    public String changeWarehouse(UUID storeKeeperId, int warehouseNumber) {
        Optional<StoreKeeper> existingStoreKeeper = storeKeeperRepository.findById(storeKeeperId);
        if (existingStoreKeeper.isEmpty()) {
            return "The store keeper with that id does not exist";
        }
        Optional<Warehouse> existingWarehouse = warehouseRepository.findByWarehouseNumber(warehouseNumber);
        if (existingWarehouse.isEmpty()) {
            return "The warehouse with that warehouseNumber does not exist";
        }
        Warehouse warehouse = existingWarehouse.get();
        if (storeKeeperRepository.existsByWarehouse(warehouse)) {
            return "That warehouse is taken by another store keeper";
        }
        StoreKeeper storeKeeper = existingStoreKeeper.get();
        storeKeeper.setWarehouse(warehouse);
        storeKeeperRepository.save(storeKeeper);
        return "Warehouse changed successfully";
    }
}
