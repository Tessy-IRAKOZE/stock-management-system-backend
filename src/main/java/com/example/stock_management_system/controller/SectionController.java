package com.example.stock_management_system.controller;

import com.example.stock_management_system.domain.Section;
import com.example.stock_management_system.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSection(@RequestBody Section section) {
        String message = sectionService.saveSection(section);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Section>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSectionById(@PathVariable UUID id) {
        Optional<Section> section = sectionService.getSectionById(id);
        if (section.isPresent()) {
            return new ResponseEntity<>(section.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The section with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSection(@PathVariable UUID id, @RequestBody Section section) {
        String message = sectionService.updateSection(id, section);
        if (message.equals("The section with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable UUID id) {
        String message = sectionService.deleteSection(id);
        if (message.equals("The section with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
