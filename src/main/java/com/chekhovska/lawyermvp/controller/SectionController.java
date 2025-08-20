package com.chekhovska.lawyermvp.controller;

import com.chekhovska.lawyermvp.dto.SectionRequest;
import com.chekhovska.lawyermvp.dto.SectionResponse;
import com.chekhovska.lawyermvp.model.Section;
import com.chekhovska.lawyermvp.service.SectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/api/section")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectionResponse>> getAll() {
        log.info("Get all section");
        return ResponseEntity.status(HttpStatus.OK).body(sectionService.getAll().stream()
                .map(SectionResponse::fromSection)
                .collect(Collectors.toList()));
    }
    @GetMapping("/visible")
    public ResponseEntity<List<SectionResponse>> findAllVisible() {
        log.info("Get all visible section");
        return ResponseEntity.status(HttpStatus.OK).body(sectionService.findAllByVisible().stream()
                .map(SectionResponse::fromSection)
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{sectionId}")
    public ResponseEntity<SectionResponse> readById(@PathVariable Long sectionId){
        log.info("Get section by id: {}", sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionResponse(sectionService.readById(sectionId)));
    }

    @PostMapping()
    public ResponseEntity<SectionResponse> create(@Validated @RequestBody SectionRequest sectionRequest) {
        Section section = sectionRequest.toSection();
        log.info("Provided section: {}", sectionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SectionResponse(sectionService.create(section)));
    }

    @PutMapping("/update/{sectionId}")
    public ResponseEntity<SectionResponse> update(@PathVariable Long sectionId,
                                                  @Validated @RequestBody SectionRequest sectionRequest) {
        Section section = sectionRequest.toSection();
        section.setId(sectionId);
        log.info("Update section: {}", sectionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionResponse(sectionService.update(section)));
    }

    @DeleteMapping("/id/{sectionId}")
    public ResponseEntity<Object> delete(@PathVariable Long sectionId) {
        log.info("Deleting section by id: {}", sectionId);
        sectionService.delete(sectionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
