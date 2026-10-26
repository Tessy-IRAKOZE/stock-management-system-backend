package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, UUID> {
    @Query("SELECT s FROM Section s WHERE s.storeKeepers IS EMPTY")
    List<Section> findUnusedSections();
}
