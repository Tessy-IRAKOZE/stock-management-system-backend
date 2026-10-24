package com.example.stock_management_system.service;

import com.example.stock_management_system.domain.Section;
import com.example.stock_management_system.repository.SectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public String saveSection(Section section) {
        sectionRepository.save(section);
        return "Section saved successfully";
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> getSectionById(UUID id) {
        return sectionRepository.findById(id);
    }

    public String updateSection(UUID id, Section section) {
        Optional<Section> existing = sectionRepository.findById(id);
        if (existing.isEmpty()) {
            return "The section with that id does not exist";
        }
        Section s = existing.get();
        s.setName(section.getName());
        sectionRepository.save(s);
        return "Section updated successfully";
    }

    public String deleteSection(UUID id) {
        if (!sectionRepository.existsById(id)) {
            return "The section with that id does not exist";
        }
        sectionRepository.deleteById(id);
        return "Section deleted successfully";
    }
}
