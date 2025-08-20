package com.chekhovska.lawyermvp.controller;

import com.chekhovska.lawyermvp.dto.ContentRequest;
import com.chekhovska.lawyermvp.dto.ContentResponse;
import com.chekhovska.lawyermvp.model.Contents;
import com.chekhovska.lawyermvp.service.ContentsService;
import com.chekhovska.lawyermvp.service.SectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/api/content")
public class ContentController {

    private final ContentsService contentsService;
    private final SectionService sectionService;

    @Autowired
    public ContentController(ContentsService contentsService, SectionService sectionService) {
        this.contentsService = contentsService;
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContentResponse>> getAll() { //@PathVariable("user_id") long userId
        log.info("Get all content");
        return ResponseEntity.status(HttpStatus.OK).body(contentsService.getAll().stream()
                .map(ContentResponse::fromContent)
                .collect(Collectors.toList()));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ContentResponse>> getAllByType(@PathVariable String type){
        log.info("Get content by type: {}", type);
        return ResponseEntity.status(HttpStatus.OK).body(contentsService.getAllBySectionType(type).stream()
                .map(ContentResponse::fromContent)
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{contentId}")
    public ResponseEntity<ContentResponse> readById(@PathVariable Long contentId){
        log.info("Get content by id: {}", contentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ContentResponse(contentsService.readById(contentId)));
    }

    @PostMapping()
    public ResponseEntity<ContentResponse> create(@Validated @RequestBody ContentRequest contentRequest) {
        Contents content = contentRequest.toContents(sectionService.findByType(contentRequest.getType()));
        log.info("Provided content: {}", contentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ContentResponse(contentsService.create(content)));
    }

    @PutMapping("/update/{contentId}")
    public ResponseEntity<ContentResponse> update(@PathVariable Long contentId, @Validated @RequestBody ContentRequest contentRequest) {
        Contents content = contentRequest.toContents(sectionService.findByType(contentRequest.getType()));
        content.setId(contentId);
        log.info("Update content: {}", contentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ContentResponse(contentsService.update(content)));
    }

    @DeleteMapping("/id/{contentId}")
    public ResponseEntity<Object> delete(@PathVariable Long contentId) {
        log.info("Deleting content by id: {}", contentId);
        contentsService.delete(contentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
