package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.StoreKeeper;
import com.example.stock_management_system.repository.StoreKeeperRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreKeeperService {
    private final StoreKeeperRepository storeKeeperRepository;

    public StoreKeeperService(StoreKeeperRepository storeKeeperRepository) {
        this.storeKeeperRepository = storeKeeperRepository;
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
}
