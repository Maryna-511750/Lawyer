package com.chekhovska.lawyerclient.controller;

import com.chekhovska.lawyerclient.dto.ContentDto;
import com.chekhovska.lawyerclient.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentsController {

    private final ContentsService contentsService;

    @GetMapping("/get")
    public ResponseEntity<ContentDto> getByType(@RequestParam String type) { //@RequestParam @NotBlank
        return ResponseEntity.status(HttpStatus.OK).body(contentsService.getContentsByType(type));
    }

    @PostMapping(path="/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> create(
            //@RequestPart ContentDto contentDto
    ) {
        ContentDto contentDto = new ContentDto("title", "description", "image", "CASES");
        contentsService.createContent(contentDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}