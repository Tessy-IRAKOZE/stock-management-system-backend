package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.Supplier;
import com.example.stock_management_system.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public String saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
        return "Supplier saved successfully";
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(UUID id) {
        return supplierRepository.findById(id);
    }

    public String updateSupplier(UUID id, Supplier supplier) {
        Optional<Supplier> existing = supplierRepository.findById(id);
        if (existing.isEmpty()) {
            return "The supplier with that id does not exist";
        }
        Supplier s = existing.get();
        s.setCompanyName(supplier.getCompanyName());
        s.setContactPerson(supplier.getContactPerson());
        s.setTinNumber(supplier.getTinNumber());
        s.setPhone(supplier.getPhone());
        s.setEmail(supplier.getEmail());
        supplierRepository.save(s);
        return "Supplier updated successfully";
    }

    public String deleteSupplier(UUID id) {
        if (!supplierRepository.existsById(id)) {
            return "The supplier with that id does not exist";
        }
        supplierRepository.deleteById(id);
        return "Supplier deleted successfully";
    }

    public List<Supplier> getSuppliersByContactPerson(String contactPerson) {
        return supplierRepository.findByContactPersonIgnoreCaseOrderByCompanyNameAsc(contactPerson);
    }

    public List<Supplier> getSuppliersOfStoreKeeper(UUID storeKeeperId) {
        return supplierRepository.findSuppliersOfStoreKeeper(storeKeeperId);
    }
}
