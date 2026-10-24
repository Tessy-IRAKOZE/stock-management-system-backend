package com.example.stock_management_system.repository;

import com.example.stock_management_system.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, UUID> {
}
